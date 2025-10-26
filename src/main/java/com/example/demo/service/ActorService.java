package com.example.demo.service;

import com.example.demo.dto.actor.CreateActorRequest;
import com.example.demo.mapper.ActorMapper;
import com.example.demo.repository.ActorRepository;
import com.example.demo.entity.Actor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {
    private final ActorRepository actorRepository;
    private final ActorMapper actorMapper;
    public ActorService(ActorRepository actorRepository, ActorMapper actorMapper) {
        this.actorRepository = actorRepository;
        this.actorMapper = actorMapper;
    }

    public List<Actor> findAll() {
        return (List<Actor>) actorRepository.findAll();
    }

    public Actor findById(Short id) {
        return actorRepository.findById(id).orElse(null);
    }

    public Actor create(CreateActorRequest dto) {
        Actor actor = actorMapper.toEntity(dto);
        return actorRepository.save(actor);
    }

    public Actor update(Short id,Actor actor) {
        Actor entity = actorRepository.findById(id).orElse(null);
        if(entity == null){
            return null;
        }
        return actorRepository.save(actor);
    }

    public void delete(Short id) {
        actorRepository.deleteById(id);
    }
}
