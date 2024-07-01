package com.relations.jparepo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.relations.jparepo.repositories.projects_repo;
import com.relations.jparepo.models.Projects;

@RestController
@RequestMapping("/project")
public class project_controller {

    @Autowired
    private projects_repo projectRepository;

    @GetMapping("/test")
    public String test(){
        return "This Port Works !";
    }

    @PostMapping("/add")
    public ResponseEntity<Projects> addProject(@RequestBody Projects project){
        return ResponseEntity.ok(projectRepository.save(project));
    }
}
