package com.googleFormPlugin.googleFormPlugin.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PluginController {

    @GetMapping("/getString")
    public String returnDting(){
        return "Hello from Rahul";
    }
    
}
