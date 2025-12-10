package com.odintsov.wallpapers_project.application.mappers;

public interface CommonMapper<T, ListResponse, DetailedResponse> {

    /**
     * Converts an entity to a list response DTO.
     * Must be implemented by each service.
     *
     * @param entity the entity to convert
     * @return the list response DTO
     */
    ListResponse toListResponseDto(T entity);
    /**
     * Converts an entity to a detailed response DTO.
     * Must be implemented by each service.
     *
     * @param entity the entity to convert
     * @return the detailed response DTO
     */
    DetailedResponse toDetailedResponseDto(T entity);
}

