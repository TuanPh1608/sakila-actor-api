package com.example.demo.mapper;

import com.example.demo.dto.actor.CreateActorRequest;
import com.example.demo.entity.Actor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ActorMapper {
    @Mapping(target = "id",  ignore = true)
    @Mapping(target = "lastUpdate", ignore = true)
    Actor toEntity(CreateActorRequest dto);
}
