package com.dylan.chatbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ClaudeService claudeService;

    @RequestMapping("/")
    public String helloWorld(){
        return "Hello World";
    }
    @PostMapping("/message")
    public String sendMessage(@RequestBody String userMessage) throws IOException, InterruptedException {

        return claudeService.sendMessage(userMessage);
    }
}