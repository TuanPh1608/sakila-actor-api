package com.example.demo.controller;

import com.example.demo.dto.actor.CreateActorRequest;
import com.example.demo.entity.Actor;
import com.example.demo.service.ActorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actor")
@Tag(name = "Actor", description = "This is Actor tag")
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping
    public List<Actor> actor(){
        return (List<Actor>) actorService.findAll();
    }

    @GetMapping("/{id}")
    public Actor findById(@PathVariable Short id){
        return actorService.findById(id);
    }

    @PostMapping
    public Actor save(@Valid @RequestBody CreateActorRequest dto){
        return actorService.create(dto);
    }

    @PutMapping("/{id}")
    public Actor update(@RequestBody Actor actor,  @PathVariable Short id){
        return actorService.update(id, actor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Short id){
        actorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
