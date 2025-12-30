package com.odintsov.wallpapers_project.application.usecases.CreateOrder;

public interface CreateOrderUseCase {
    CreateOrderResponse execute(String authHeader, CreateOrderCommand command);
}
