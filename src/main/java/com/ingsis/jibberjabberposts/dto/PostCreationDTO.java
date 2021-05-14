package com.ingsis.jibberjabberposts.dto;

public class PostCreationDTO {
    private String text;

    public PostCreationDTO(){}

    public PostCreationDTO(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
