package com.donaMaria_.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
public class MainController {
    @GetMapping
    public String mainRoute(){
        return "application is running";
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}
