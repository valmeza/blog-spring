package com.codeup.blog.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Pattern(regexp = "([a-zA-Z0-9_.]+@[a-zA-Z0-9]+.[a-zA-Z]{2,3}[.] {0,1}[a-zA-Z]+)", message="email must be valid email address" )
    @NotBlank(message = "Email can't be blank!")
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, length = 50, unique = true)
    @NotBlank(message = "Username can't be blank")
    private String username;

    @Pattern(regexp="^(?=.*?[A-Z])(?=.*?[0-9]).{8,}$",message="Password length must be at least 8 characters with one uppercase letter and one digit")
    @NotBlank(message = "Password can't be blank!")
    @Column(nullable = false)
    private String password;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean isAdmin;

    public User() {
    }

    public User(String email, String username, String password, boolean isAdmin) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public User(long id, String email, String username, String password, boolean isAdmin) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public User(User copy) {
        id = copy.id; // This line is SUPER important! Many things won't work if it's absent
        email = copy.email;
        username = copy.username;
        password = copy.password;
        isAdmin = copy.isAdmin;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
