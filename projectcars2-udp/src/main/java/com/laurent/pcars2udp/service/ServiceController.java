package com.laurent.pcars2udp.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {



    @RequestMapping("/status")
    public String status() {
        return "OK";
    }
}