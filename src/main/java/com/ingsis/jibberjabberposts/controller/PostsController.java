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
    public ResponseEntity<?> updatePost(@RequestBody PostCreationDTO post, @PathVariable long id) throws NotFoundException {
        return service.updatePost(post, id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    public ResponseEntity<?> deletePost(@PathVariable long id){
        return service.deletePost(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/like/{id}")
    public ResponseEntity<?> likePost(@PathVariable long id) throws NotFoundException {
        return service.likePost(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/dislike/{id}")
    public ResponseEntity<?> dislike(@PathVariable long id) throws NotFoundException {
        return service.dislikePost(id);
    }
}
