package com.survivingcodingbootcamp.blog.controller;

import com.survivingcodingbootcamp.blog.model.Hashtag;
import com.survivingcodingbootcamp.blog.model.Post;
import com.survivingcodingbootcamp.blog.model.Topic;
import com.survivingcodingbootcamp.blog.repository.PostRepository;
import com.survivingcodingbootcamp.blog.repository.TopicRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.util.Optional;

@Controller
@RequestMapping("/topics")
public class TopicController {

    private TopicRepository topicRepo;
    private PostRepository postRepo;

    public TopicController(TopicRepository topicRepo, PostRepository postRepo) {
        this.topicRepo = topicRepo;
        this.postRepo = postRepo;
    }
    @GetMapping("/{id}")
    public String displaySingleTopic(@PathVariable long id, Model model) {
        model.addAttribute("topic", topicRepo.findById(id).get());
        return "single-topic-template";
    }

//    @PostMapping("/{id}")
//    public String addNewPost(@PathVariable long id, @RequestParam String topic){
//        Post post = postRepo.findById(id).get();
//        postRepo.save(post);
//
//
//        return "redirect:/post/"+id;
//    }
    @PostMapping("/{id}")
    public String addNewPost(@PathVariable long id, @RequestParam String topic){
        Post post = postRepo.findById(id).get();
//        postRepo.save(post);

        Optional<Post> post1 = postRepo.findById(id);
        if(post1.isPresent()){
                post1.get().getTopic();
                postRepo.save(post1.get());
        }

        return "redirect:/topics/"+id;
    }


}
