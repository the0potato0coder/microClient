package com.example.client.service;

import com.example.client.model.Client;
import com.example.client.repository.ClientRepository;
import com.example.client.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Autowired
    private ServerService serverService;

    public Client processClientRequest(Client data) {
        // 1. Save to MySQL (Synchronous - must happen first)
        Client savedData = repository.save(data);
        System.out.println("Saved to MySQL ID: " + savedData.getId());

        // 2. Trigger Async Call to MongoDB App
        serverService.pushDataToMongoServer(savedData);

        // 3. Return immediately (User doesn't wait for MongoDB sync)
        return savedData;
    }
}