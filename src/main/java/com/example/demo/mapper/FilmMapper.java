package com.example.demo.mapper;

import com.example.demo.dto.film.FilmCreateRequest;
import com.example.demo.dto.film.FilmResponse;
import com.example.demo.dto.film.FilmUpdateRequest;
import com.example.demo.entity.Film;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface FilmMapper {

    FilmResponse toResponse(Film film);

    @Mapping(target = "lastUpdate", ignore = true)
    @Mapping(target = "filmId", ignore = true)
    Film toEntity(FilmCreateRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "lastUpdate", ignore = true)
    @Mapping(target = "filmId", ignore = true)
    void updateEntity(@MappingTarget Film film, FilmUpdateRequest request);
}