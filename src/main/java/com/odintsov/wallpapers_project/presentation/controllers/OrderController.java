package com.odintsov.wallpapers_project.presentation.controllers;

import com.odintsov.wallpapers_project.application.usecases.CreateOrder.CreateOrderCommand;
import com.odintsov.wallpapers_project.application.usecases.CreateOrder.CreateOrderResponse;
import com.odintsov.wallpapers_project.application.usecases.CreateOrder.CreateOrderUseCase;
import com.odintsov.wallpapers_project.application.usecases.GetOrderHistory.GetOrderHistoryUseCase;
import com.odintsov.wallpapers_project.application.usecases.GetOrderHistory.OrderHistoryResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;
    private final GetOrderHistoryUseCase getOrderHistoryUseCase;

    public OrderController(CreateOrderUseCase createOrderUseCase, GetOrderHistoryUseCase getOrderHistoryUseCase) {
        this.createOrderUseCase = createOrderUseCase;
        this.getOrderHistoryUseCase = getOrderHistoryUseCase;
    }

    @PostMapping
    public CreateOrderResponse create(
            @RequestHeader("Authorization") String authHeader,
            @Valid @RequestBody CreateOrderCommand command
    ) {
        return createOrderUseCase.execute(authHeader, command);
    }

    @GetMapping("/my-history")
    public ResponseEntity<List<OrderHistoryResponse>> getMyHistory(
            @RequestHeader("Authorization") String authHeader) {

        List<OrderHistoryResponse> history = getOrderHistoryUseCase.execute(authHeader);
        return ResponseEntity.ok(history);
    }
}


