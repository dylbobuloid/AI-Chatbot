package com.dylan.chatbot;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@Service
public class ClaudeService {

    public String sendMessage(String userMessage) throws IOException, InterruptedException {
        Gson gson = new Gson();
        String apiKey = System.getenv("ANTHROPIC_API_KEY");
        HttpClient client = HttpClient.newHttpClient();
        String jsonBody = """
                {
                    "model": "claude-sonnet-4-20250514",
                    "max_tokens": 1024,
                    "messages": [
                        {"role": "user", "content": "%s"}
                    ]
                }
                """.formatted(userMessage);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.anthropic.com/v1/messages"))
                .header("x-api-key", apiKey)
                .header("anthropic-version", "2023-06-01")
                .header("content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

}
