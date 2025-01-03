package com.bharti.blog_app_api.payload;

import jakarta.validation.constraints.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserDto {
//    @Pattern(regexp = "\\d+", message = "Id should be only numeric values")
    private int userId;

    @NotEmpty(message = "Please enter your name")
    @Size(min = 4, message = "Name must be at least 4 characters !!")
    @Pattern(regexp = "^[a-zA-Z ]+$")
    private String name;

    @NotBlank
    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 15, message = "Password must be at least 8 characters long and less than 15 characters")
    @Pattern(regexp = "^[a-zA-Z0-9#@]+$", message = "Password must contain only letters, numbers, #, and @")
    private String password;

    @NotEmpty
    private String about;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
