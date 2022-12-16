package com.cts.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class TestController {

    @GetMapping("/status/check")
    public String getStatus(){

        return "working";
    }
}
