package com.redis.redisChat.demo.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontController {

    @GetMapping("/chat")
    public String getMethodName() {
        return "chat.html";
    }

    @GetMapping("/login")
    public String getMethodName2() {
        return "login.html";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup.html";
    }
}
