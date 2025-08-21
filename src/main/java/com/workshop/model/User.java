package com.workshop.model;

import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "users") // maps to the users table
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String role;

    // For REGULAR_USER
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserProfile userProfile;

    // For SPACE_OWNER
    @OneToOne(mappedBy = "owner", cascade = CascadeType.ALL)
    private Space spaces;

    // ---- Constructors ----
    public User() {}

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // ---- Getters & Setters ----
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
