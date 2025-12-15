package com.odintsov.wallpapers_project.application.mappers.common;

public interface FullMapper<T, ListResponse, DetailedResponse, Command>
        extends DtoMapper<T, ListResponse, DetailedResponse> {

    /**
     * Converts a command to an entity.
     * Must be implemented by each service.
     *
     * @param command the command to convert
     * @return the entity
     */
    T toEntity(Command command);
}
