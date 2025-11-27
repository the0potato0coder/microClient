package com.example.client.service;

import com.example.client.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ServerService {

    @Autowired
    private RestTemplate restTemplate;

    @Async // This method runs in a separate thread
    public void pushDataToMongoServer(Client data) {
        String url = "http://localhost:8090/server/add"; // URL of your Mongo App

        try {
            // Simulate a slight delay to prove it's async (optional)
            Thread.sleep(2000);

            restTemplate.postForObject(url, data, String.class);
            System.out.println("SUCCESS: Data sent to MongoDB Server asynchronously.");
        } catch (Exception e) {
            System.err.println("ERROR: Failed to send data to Mongo Server: " + e.getMessage());
        }
    }
}