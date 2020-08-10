package com.codeup.blog.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false)
    private String body;

    @OneToOne
    private User owner;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<PostImage> images;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "posts_categories",
            joinColumns = {@JoinColumn(name = "post_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    private List<PostCategory> categories;

    @Column(nullable = false)
    private String queryHash;

    // spring framework uses this empty constructor
    public Post() {
    }

    //insert
    public Post(String title, String body, User user, List<PostImage> postImages, List<PostCategory> postCategories, String queryHash) {
        this.title = title;
        this.body = body;
        this.owner = user;
        this.images = postImages;
        this.categories = postCategories;
        this.queryHash = queryHash;
    }

    // read
    public Post(long id, String title, String body, User user, List<PostImage> postImages, List<PostCategory> postCategories, String queryHash) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.owner = user;
        this.images = postImages;
        this.categories = postCategories;
        this.queryHash = queryHash;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getOwner() {
        return this.owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<PostImage> getImages() {
        return this.images;
    }

    public void setImages(List<PostImage> images) {
        this.images = images;
    }

    public List<PostCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<PostCategory> categories) {
        this.categories = categories;
    }

    public String getQueryHash() {
        return queryHash;
    }

    public void setQueryHash(String queryHash) {
        this.queryHash = queryHash;
    }
}
