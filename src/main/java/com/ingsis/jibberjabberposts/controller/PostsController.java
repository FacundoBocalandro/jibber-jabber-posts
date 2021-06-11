package com.ingsis.jibberjabberposts.controller;

import com.ingsis.jibberjabberposts.dto.PostCreationDTO;
import com.ingsis.jibberjabberposts.model.Post;
import com.ingsis.jibberjabberposts.service.PostsService;
import javassist.NotFoundException;
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

    @RequestMapping(method = RequestMethod.PUT, value = "/update/{id}")
    public void updatePost(@RequestBody PostCreationDTO post, @PathVariable long id) throws NotFoundException {
        service.updatePost(post, id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    public void deletePost(@PathVariable long id){
        service.deletePost(id);
    }
}
