package com.ingsis.jibberjabberposts.controller;

import com.ingsis.jibberjabberposts.dto.PostCreationDTO;
import com.ingsis.jibberjabberposts.model.Post;
import com.ingsis.jibberjabberposts.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostsController {
    @Autowired
    private PostsService service;

    @PostMapping("/create")
    public ResponseEntity<?> createPost(@RequestBody PostCreationDTO post) {
        return service.createPost(post);
    }

    @GetMapping
    public List<Post> getAllPosts(){
        return service.getAllPosts();
    }
}
