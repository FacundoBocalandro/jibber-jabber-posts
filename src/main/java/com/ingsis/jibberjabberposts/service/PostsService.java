package com.ingsis.jibberjabberposts.service;

import com.ingsis.jibberjabberposts.dto.PostCreationDTO;
import com.ingsis.jibberjabberposts.model.Post;
import com.ingsis.jibberjabberposts.repository.PostsRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostsService {

    @Autowired
    private PostsRepository repository;

    public ResponseEntity<?> createPost(PostCreationDTO postCreationDTO, String token) {
        Post post = new Post(postCreationDTO.getText());
        // Long userId = AuthenticationService.getUserId(token);
        Post saved = repository.save(post);
        return ResponseEntity.ok(saved);
    }

    public List<Post> getAllPosts() {
        return repository.findAll();
    }

    public ResponseEntity<?> updatePost(PostCreationDTO post, long id) throws NotFoundException {
        Post oldPost = repository.findById(id).orElseThrow(() -> new NotFoundException("Post not found"));
        oldPost.setText(post.getText());

        Post saved = repository.save(oldPost);
        return ResponseEntity.ok(saved);
    }

    public ResponseEntity<?> deletePost(long id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> likePost(String token, long id) throws NotFoundException {
        Post post = repository.findById(id).orElseThrow(() -> new NotFoundException("Post not found"));
        // Long userId = AuthenticationService.getUserId(token);
        post.like(1L);
        repository.save(post);
        return ResponseEntity.ok().build();
    }
}