package com.codeup.blog.daos;

import com.codeup.blog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostsRepository extends JpaRepository<Post, Long> {
    // HQL
    @Query("FROM Post AS a WHERE a.title LIKE %:search% or a.body LIKE %:search%")
    List<Post> searchByTitle(@Param("search") String search);

    // query methods
    Post findFirstByTitle(String title); // Select * from posts where title = ? Limit 1;

    Post findByTitle(String ad_to_be_deleted);
}
