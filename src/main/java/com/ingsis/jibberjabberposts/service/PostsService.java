package com.ingsis.jibberjabberposts.service;

import com.ingsis.jibberjabberposts.dto.PostCreationDTO;
import com.ingsis.jibberjabberposts.dto.UserDto;
import com.ingsis.jibberjabberposts.model.Post;
import com.ingsis.jibberjabberposts.repository.PostsRepository;
import com.ingsis.jibberjabberposts.utils.PostTimestampComparator;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostsService {

    @Autowired
    private PostsRepository repository;

    private Long getUserId(){
        return ((UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }

    private String getUsername(){
        return ((UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

    public ResponseEntity<?> createPost(PostCreationDTO postCreationDTO) {
        Post post = new Post(postCreationDTO.getText(), getUserId(), getUsername(), LocalDateTime.now());
        Post saved = repository.save(post);
        return ResponseEntity.ok(saved);
    }

    public List<Post> getAllPosts() {
        return repository.findAll().stream().sorted(new PostTimestampComparator()).collect(Collectors.toList());
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

    public ResponseEntity<?> likePost(long id) throws NotFoundException {
        Post post = repository.findById(id).orElseThrow(() -> new NotFoundException("Post not found"));
        post.like(getUserId());
        repository.save(post);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> dislikePost(long id) throws NotFoundException {
        Post post = repository.findById(id).orElseThrow(() -> new NotFoundException("Post not found"));
        post.dislike(getUserId());
        repository.save(post);
        return ResponseEntity.ok().build();
    }

    public List<Post> getUserPosts(long userId) {
        return repository.findAllByUserId(userId);
    }
}