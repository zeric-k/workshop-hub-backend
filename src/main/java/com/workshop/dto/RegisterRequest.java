package com.workshop.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String role;

    // Fields for Space (SPACE_OWNER)
    private String spaceName;
    private String location;

    // Fields for UserProfile (REGULAR_USER)
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNo;

    // getters & setters
}
