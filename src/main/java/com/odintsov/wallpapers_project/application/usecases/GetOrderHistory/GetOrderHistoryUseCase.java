package com.odintsov.wallpapers_project.application.usecases.GetOrderHistory;

import java.util.List;

public interface GetOrderHistoryUseCase {
    List<OrderHistoryResponse> execute(String authHeader);
}
