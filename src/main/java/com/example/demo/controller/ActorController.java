package com.example.demo.controller;

import com.example.demo.ActorRepository;
import com.example.demo.entity.Actor;
import com.example.demo.service.ActorService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("/actor")
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
    public Actor findById(@PathVariable int id){
        return actorService.findById(id);
    }

    @PostMapping
    public Actor save(@RequestBody Actor actor){
        return actorService.create(actor);
    }

    @PutMapping("/{id}")
    public Actor update(@RequestBody Actor actor,  @PathVariable Integer id){
        return actorService.update(id, actor);
    }
}
