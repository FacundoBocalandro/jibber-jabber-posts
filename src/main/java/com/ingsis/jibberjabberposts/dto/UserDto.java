package com.ingsis.jibberjabberposts.dto;

public class UserDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String role;

    public UserDto(){}

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRole() {
        return role;
    }
}
