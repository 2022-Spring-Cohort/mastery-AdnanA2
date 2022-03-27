package com.survivingcodingbootcamp.blog.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Hashtag {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToMany
    Collection<Post> posts;


    public Hashtag(String name) {
        this.name = name;
        this.posts = new ArrayList<>();
    }

    public Hashtag() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Collection<Post> getPosts() {
        return posts;
    }

    public void addPosts(Post post){
        posts.add(post);
    }


}
