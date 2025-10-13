package com.example.demo.service;

import com.example.demo.repository.ActorRepository;
import com.example.demo.entity.Actor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {
    private final ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public List<Actor> findAll() {
        return (List<Actor>) actorRepository.findAll();
    }

    public Actor findById(int id) {
        return actorRepository.findById(id).orElse(null);
    }

    public Actor create(Actor actor) {
        return actorRepository.save(actor);
    }

    public Actor update(Integer id,Actor actor) {
        Actor entity = actorRepository.findById(id).orElse(null);
        if(entity == null){
            return null;
        }
        return actorRepository.save(actor);
    }

    public void delete(Integer id) {
        actorRepository.deleteById(id);
    }
}
