package com.dylan.chatbot;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @PostMapping("/message")
    public String sendMessage(@RequestBody String userMessage) {
        // We'll add chatbot logic here
        return "You said: " + userMessage;
    }
}