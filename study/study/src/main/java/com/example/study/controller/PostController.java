package com.example.study.controller;

import com.example.study.model.searchParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PostController {

    @PostMapping(value = "/postMethod")
    public searchParam postMethod(@RequestBody searchParam searchParam){

        return searchParam;
    }
}
