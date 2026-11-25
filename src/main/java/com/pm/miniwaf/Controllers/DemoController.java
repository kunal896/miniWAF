package com.pm.miniwaf.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class DemoController {

    @GetMapping("/demo")
    public String helloWorld(@RequestParam(required = false) String name, Model model){
        if(name!=null){
            model.addAttribute("name",name);
        }
        else{
            model.addAttribute("name", "World");
        }

        return "hello";
    }


//    //combined into /demo
//    @GetMapping("/test")
//    public String vulnerableTest(@RequestParam String name, Model model)
//    {
//
//        model.addAttribute("userParameter",name);
//        return "vulnerableView";
//    }
}
