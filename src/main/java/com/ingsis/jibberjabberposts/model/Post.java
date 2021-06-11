package com.ingsis.jibberjabberposts.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private Long userId;

    @ElementCollection
    private Set<Long> likes;

    public Post(){}

    public Post(String text) {
        this.text = text;
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

    public void like(Long userId) {
        likes.add(userId);
    }

    public void dislike(Long userId) {
        likes.remove(userId);
    }
}
