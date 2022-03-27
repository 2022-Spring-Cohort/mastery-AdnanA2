package com.survivingcodingbootcamp.blog.controller;

import com.survivingcodingbootcamp.blog.model.Hashtag;
import com.survivingcodingbootcamp.blog.repository.HashtagRepository;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HashtagController {

    private HashtagRepository hashtagRepo;

    public HashtagController(HashtagRepository hashtagRepo) {
        this.hashtagRepo = hashtagRepo;
    }

    @GetMapping("/hashtags")
    public String DisplayHashtagsPage(Model model) {
        model.addAttribute("hashtags", hashtagRepo.findAll());
        return "all-hashtags-template";
    }
    @GetMapping("/hashtags/{id}")
    public String DisplayHashtagsPage(Model model, @PathVariable long id){
        model.addAttribute("hashtag",hashtagRepo.findById(id).get());
        return "single-hashtag-template";
    }

}
