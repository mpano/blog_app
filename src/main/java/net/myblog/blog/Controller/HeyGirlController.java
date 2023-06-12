package net.myblog.blog.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeyGirlController {
    @GetMapping("/hey")
    public String hey(){
        return "Hey Girl are you good";
    }
}
