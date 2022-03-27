package com.survivingcodingbootcamp.blog.controller;

import com.survivingcodingbootcamp.blog.model.Hashtag;
import com.survivingcodingbootcamp.blog.model.Post;
import com.survivingcodingbootcamp.blog.repository.HashtagRepository;
import com.survivingcodingbootcamp.blog.repository.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/posts")
public class PostController {
    private PostRepository postRepo;
    private HashtagRepository hashtagRepo;

    public PostController(PostRepository postRepo, HashtagRepository hashtagRepo) {
        this.postRepo = postRepo;
        this.hashtagRepo = hashtagRepo;
    }

    @GetMapping("/{id}")
    public String displaySinglePost(@PathVariable long id, Model model) {
        model.addAttribute("post", postRepo.findById(id).get());
        return "single-post-template";
    }
    @PostMapping("/{id}")
    public String addHashtag(@PathVariable long id, @RequestParam String hashtag){
        Post post = postRepo.findById(id).get();
//        postRepo.save(post);

        Optional<Hashtag> hashtag1 = hashtagRepo.findByNameIgnoreCase(hashtag);
        if(hashtag1.isPresent()){
            if(!hashtag1.get().getPosts().contains(post)){
                hashtag1.get().addPosts(post);
                hashtagRepo.save(hashtag1.get());
            }
        }
        else{
            Hashtag hashtag2 = new Hashtag(hashtag);
            hashtag2.addPosts(post);
            hashtagRepo.save(hashtag2);
        }
        return "redirect:/posts/"+id;

    }


}
