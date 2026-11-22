package com.pm.miniwaf.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/demo")
    public String helloWorld(@RequestParam(required = false) String name){
        if(name!=null){
            return "Hello " + name + "!";
        }
        else{
            return "Hello world!";
        }
    }
}
