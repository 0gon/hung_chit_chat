package com.hung_chit_chat.front;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class FrontController {

    @GetMapping("/chat")
    public String chat() {
        return "chat.html";
    }

    @GetMapping("/signin")
    public String signin() {
        return "signin.html";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup.html";
    }

    @GetMapping("/app")
    public String roomList() {
        return "app.html";
    }

    @GetMapping("/popup/addFriend")
    public String addFriend() {
        return "/popup/addFriend.html";
    }

    @GetMapping("/popup/makeRoom")
    public String makeRoom() {
        return "/popup/makeRoom.html";
    }

    @GetMapping("/popup/room")
    public String room() {
        return "/popup/room.html";
    }

}
