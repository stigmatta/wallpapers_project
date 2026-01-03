package com.odintsov.wallpapers_project.unitTests;

import com.odintsov.wallpapers_project.application.dtos.OrderItem.OrderItemRequest;
import com.odintsov.wallpapers_project.application.usecases.CreateOrder.*;
import com.odintsov.wallpapers_project.application.resolvers.PriceCalculatorResolver;
import com.odintsov.wallpapers_project.application.services.SessionService;
import com.odintsov.wallpapers_project.domain.entities.*;
import com.odintsov.wallpapers_project.domain.pricing.PriceCalculator;
import com.odintsov.wallpapers_project.domain.repositories.*;
import com.odintsov.wallpapers_project.application.exceptions.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateOrderUseCaseTest {

    @Mock private SessionService sessionService;
    @Mock private UserRepository userRepository;
    @Mock private ProductRepository productRepository;
    @Mock private ExtraFeatureRepository extraFeatureRepository;
    @Mock private OrderRepository orderRepository;
    @Mock private CreateOrderValidator validator;
    @Mock private PriceCalculatorResolver priceCalculatorResolver;

    @InjectMocks
    private CreateOrderUseCaseImpl createOrderUseCase;

    private UUID userId;
    private String authHeader;
    private User mockUser;

    @BeforeEach
    void setUp() {
        userId = UUID.randomUUID();
        authHeader = "Bearer token";
        mockUser = new User();
        mockUser.setId(userId.toString());
    }

    @Test
    void execute_SuccessfulOrderCreation_ReturnsResponse() {
        OrderItemRequest itemRequest = new OrderItemRequest(
                "prod-123",
                "type-wallpaper",
                2,
                new HashMap<>(),
                new ArrayList<>()
        );
        CreateOrderCommand command = new CreateOrderCommand(List.of(itemRequest));

        BaseProduct mockProduct = mock(BaseProduct.class);
        ProductType mockType = mock(ProductType.class);
        PriceCalculator mockCalculator = mock(PriceCalculator.class);

        when(sessionService.getUserIdByAuthHeader(authHeader)).thenReturn(userId);
        when(userRepository.findById(userId.toString())).thenReturn(Optional.of(mockUser));
        when(productRepository.findById(any())).thenReturn(Optional.of(mockProduct));
        when(mockProduct.getProductType()).thenReturn(mockType);
        when(mockType.getName()).thenReturn("Wallpaper");
        when(priceCalculatorResolver.resolve(mockProduct)).thenReturn(mockCalculator);
        when(mockCalculator.calculate(any(), any())).thenReturn(200.00);

        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> {
            Order order = invocation.getArgument(0);
            order.setId("order-001");
            return order;
        });

        CreateOrderResponse response = createOrderUseCase.execute(authHeader, command);

        assertNotNull(response);
        assertEquals("order-001", response.orderId());
        assertEquals(200.0, response.totalPrice());

        verify(validator).validate(command);
        verify(orderRepository).save(any(Order.class));
    }

    @Test
    void execute_UserNotFound_ThrowsException() {
        CreateOrderCommand command = new CreateOrderCommand(new ArrayList<>());
        when(sessionService.getUserIdByAuthHeader(authHeader)).thenReturn(userId);
        when(userRepository.findById(userId.toString())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () ->
                createOrderUseCase.execute(authHeader, command)
        );
    }

    @Test
    void execute_ProductNotFound_ThrowsException() {
        OrderItemRequest itemRequest = new OrderItemRequest("invalid-id", "type", 1, null, null);
        CreateOrderCommand command = new CreateOrderCommand(List.of(itemRequest));

        when(sessionService.getUserIdByAuthHeader(authHeader)).thenReturn(userId);
        when(userRepository.findById(userId.toString())).thenReturn(Optional.of(mockUser));
        when(productRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () ->
                createOrderUseCase.execute(authHeader, command)
        );
    }
}
