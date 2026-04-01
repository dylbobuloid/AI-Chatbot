package com.dylan.chatbot;
import com.google.gson.Gson;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static com.dylan.chatbot.Message.Role.USER;

@Service
public class ClaudeService {
    @Value("classpath:prompt.txt")
    Resource promptFile;

    ArrayList<Message> messages = new ArrayList<>();



    public String sendMessage(String userMessage) throws IOException, InterruptedException {
        // First we need to create a message object
        // Then we need to add the user message to the array so we can keep a log
        Message newMessage = new Message(USER, userMessage);
        messages.add(newMessage);

        String systemPrompt = new String(promptFile.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        Gson gson = new Gson();
        String apiKey = System.getenv("ANTHROPIC_API_KEY");
        HttpClient client = HttpClient.newHttpClient();
        String jsonBody = """
                {
                    "model": "claude-sonnet-4-20250514",
                    "max_tokens": 1024,
                    "system": %s ,
                    "messages": [
                        {"role": "user", "content": "%s"}
                    ]
                }
                """.formatted(gson.toJson(systemPrompt), userMessage);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.anthropic.com/v1/messages"))
                .header("x-api-key", apiKey)
                .header("anthropic-version", "2023-06-01")
                .header("content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ClaudeResponse data = gson.fromJson(response.body(), ClaudeResponse.class);
        return data.content[0].text;

    }

}
