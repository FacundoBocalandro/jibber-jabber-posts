package com.ingsis.jibberjabberposts.service;

import com.ingsis.jibberjabberposts.dto.PostCreationDTO;
import com.ingsis.jibberjabberposts.model.Post;
import com.ingsis.jibberjabberposts.repository.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostsService {

    @Autowired
    private PostsRepository repository;

    public ResponseEntity<?> createPost(PostCreationDTO postCreationDTO) {
        Post post = new Post(postCreationDTO.getText());
        Post saved = repository.save(post);
        return ResponseEntity.ok(saved);
    }

    public List<Post> getAllPosts() {
        return repository.findAll();
    }
}