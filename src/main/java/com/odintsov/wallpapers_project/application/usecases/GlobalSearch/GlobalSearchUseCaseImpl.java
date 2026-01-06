package com.odintsov.wallpapers_project.application.usecases.GlobalSearch;

import com.odintsov.wallpapers_project.application.mappers.ProductMapper;
import com.odintsov.wallpapers_project.domain.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GlobalSearchUseCaseImpl implements GlobalSearchUseCase {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public GlobalSearchResponse execute(String query) {
        List<SearchItemResponse> items = productRepository.findGlobal(query)
                .stream()
                .map(productMapper::toResponse)
                .toList();

        return new GlobalSearchResponse(items);
    }
}
