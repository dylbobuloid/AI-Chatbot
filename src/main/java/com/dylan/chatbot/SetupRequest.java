package com.dylan.chatbot;

public class SetupRequest {
    private String description;

    public SetupRequest(){
    }

    public SetupRequest(String description){
        this.description = description;

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
