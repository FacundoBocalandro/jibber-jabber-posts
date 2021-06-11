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

    public ResponseEntity<?> createPost(PostCreationDTO postCreationDTO) {
        Post post = new Post(postCreationDTO.getText());
        Post saved = repository.save(post);
        return ResponseEntity.ok(saved);
    }

    public List<Post> getAllPosts() {
        return repository.findAll();
    }

    public void updatePost(PostCreationDTO post, long id) throws NotFoundException {
        Post oldPost = repository.findById(id).orElseThrow(() -> new NotFoundException("Post not found"));
        oldPost.setText(post.getText());

        repository.save(oldPost);
    }

    public void deletePost(long id) {
        repository.deleteById(id);
    }
}