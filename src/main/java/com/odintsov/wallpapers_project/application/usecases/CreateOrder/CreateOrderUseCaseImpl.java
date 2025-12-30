package com.odintsov.wallpapers_project.application.usecases.CreateOrder;

import com.odintsov.wallpapers_project.application.dtos.OrderItem.OrderItemExtraFeatureRequest;
import com.odintsov.wallpapers_project.application.dtos.OrderItem.OrderItemRequest;
import com.odintsov.wallpapers_project.application.exceptions.EntityNotFoundException;
import com.odintsov.wallpapers_project.application.resolvers.PriceCalculatorResolver;
import com.odintsov.wallpapers_project.application.services.SessionService;
import com.odintsov.wallpapers_project.domain.entities.*;
import com.odintsov.wallpapers_project.domain.pricing.PriceCalculator;
import com.odintsov.wallpapers_project.domain.repositories.ExtraFeatureRepository;
import com.odintsov.wallpapers_project.domain.repositories.OrderRepository;
import com.odintsov.wallpapers_project.domain.repositories.ProductRepository;
import com.odintsov.wallpapers_project.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateOrderUseCaseImpl implements CreateOrderUseCase {

    private final SessionService sessionService;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ExtraFeatureRepository extraFeatureRepository;
    private final OrderRepository orderRepository;
    private final CreateOrderValidator validator;
    private final PriceCalculatorResolver priceCalculatorResolver;


    @Override
    public CreateOrderResponse execute(String authHeader, CreateOrderCommand command) {
        UUID userId = sessionService.getUserIdByAuthHeader(authHeader);

        validator.validate(command);
        User user = userRepository.findById(userId.toString())
                .orElseThrow(() -> new EntityNotFoundException(userId));

        Order order = new Order();
        order.setUser(user);

        double totalOrderPrice = 0.0;
        for (OrderItemRequest itemRequest : command.items()) {
            BaseProduct product = productRepository.findById(itemRequest.productId())
                    .orElseThrow(() -> new EntityNotFoundException("Product not found"));

            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .productType(product.getProductType().getName())
                    .productId(product.getId())
                    .quantity(itemRequest.quantity())
                    .specifications(itemRequest.specifications())
                    .build();

            if (itemRequest.features() != null) {
                for (OrderItemExtraFeatureRequest featReq : itemRequest.features()) {
                    ExtraFeature feature = extraFeatureRepository.findById(featReq.featureId())
                            .orElseThrow(() -> new EntityNotFoundException("Feature not found"));

                    OrderItemExtraFeature link = OrderItemExtraFeature.builder()
                            .orderItem(orderItem)
                            .extraFeature(feature)
                            .value(featReq.value())
                            .build();

                    orderItem.getOrderItemExtraFeatures().add(link);
                }
            }

            PriceCalculator calculator = priceCalculatorResolver.resolve(product);
            double finalItemPrice = calculator.calculate(product, orderItem);

            orderItem.setPrice(finalItemPrice);
            order.getItems().add(orderItem);

            totalOrderPrice += finalItemPrice;
        }
        order.setPrice(totalOrderPrice);
        Order savedOrder = orderRepository.save(order);
        return new CreateOrderResponse(
                savedOrder.getId(),
                savedOrder.getPrice(),
                savedOrder.getCreatedAt(),
                savedOrder.getItems().size(),
                "Order created successfully"
        );
    }
}
