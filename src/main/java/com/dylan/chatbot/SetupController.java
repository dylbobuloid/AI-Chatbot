package com.dylan.chatbot;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class SetupController {

    @Autowired
    private ClaudeService claudeService;


    @PostMapping("/api/setup")
    public String setup(@RequestBody SetupRequest identity){

        return claudeService.setupPrompt(identity);
    }
}
