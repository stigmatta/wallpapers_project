package com.odintsov.wallpapers_project.application.usecases.GetOrderHistory;

import com.odintsov.wallpapers_project.application.mappers.OrderMapper;
import com.odintsov.wallpapers_project.application.services.SessionService;
import com.odintsov.wallpapers_project.domain.entities.Order;
import com.odintsov.wallpapers_project.domain.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class GetOrderHistoryUseCaseImpl implements GetOrderHistoryUseCase {

    private final OrderRepository orderRepository;
    private final SessionService sessionService;
    private final OrderMapper orderMapper;

    @Override
    public List<OrderHistoryResponse> execute(String authHeader) {
        UUID userId = sessionService.getUserIdByAuthHeader(authHeader);

        List<Order> orders = orderRepository.findByUserId(userId.toString());

        return orders.stream()
                .map(orderMapper::toResponse)
                .toList();
    }

}