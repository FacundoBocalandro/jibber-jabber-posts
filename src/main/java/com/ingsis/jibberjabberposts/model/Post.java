package com.ingsis.jibberjabberposts.model;

import javax.persistence.*;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

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
}
