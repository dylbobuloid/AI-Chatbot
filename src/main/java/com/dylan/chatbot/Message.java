package com.dylan.chatbot;

import com.google.gson.annotations.SerializedName;

public class Message {
     enum Role{
        @SerializedName("user")USER,
        @SerializedName("assistant")ASSISTANT
    }
    Role role;
    String content;

    public Message(Role role, String content){
        this.role = role;
        this.content = content;

    }

    public Role getRole() {
        return role;
    }

    public String getContent() {
        return content;
    }


}
