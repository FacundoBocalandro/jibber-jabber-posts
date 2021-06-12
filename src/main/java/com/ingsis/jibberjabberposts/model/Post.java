package com.ingsis.jibberjabberposts.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private Long userId;

    private String username;

    private LocalDateTime timestamp;

    @ElementCollection
    private Set<Long> likes;

    public Post(){}

    public Post(String text, Long userId, String username, LocalDateTime timestamp) {
        this.text = text;
        this.userId = userId;
        this.username = username;
        this.timestamp = timestamp;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getText() { return text; }

    public void setText(String text) {
        this.text = text;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setLikes(Set<Long> likes) {
        this.likes = likes;
    }

    public Set<Long> getLikes() {
        return likes;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void like(Long userId) {
        likes.add(userId);
    }

    public void dislike(Long userId) {
        likes.remove(userId);
    }

    public String getUsername() {
        return username;
    }
}
