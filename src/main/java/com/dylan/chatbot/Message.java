package com.dylan.chatbot;

public class Message {
     enum Role{
        USER,
        ASSISTANT
    }
    Role role;
    String content;

    public Message(Role role, String content){
        this.role = role;
        this.content = content;

    }
}
