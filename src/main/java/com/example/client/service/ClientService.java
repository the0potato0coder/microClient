package com.example.client.service;

import com.example.client.model.Client;
import com.example.client.repository.ClientRepository;
import com.example.client.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Autowired
    private ServerService serverService;

    public Client processClientRequest(Client data) {
        // 1. Auto-generate Transaction ID
        String uniqueID = UUID.randomUUID().toString();
        data.setTransactionId(uniqueID); // Set it on the object

        // 2. Save to MySQL (Synchronous)
        Client savedData = repository.save(data);
        System.out.println("Generated ID: " + uniqueID);
        System.out.println("Saved to MySQL ID: " + savedData.getId());

        // 3. Trigger Async Call to MongoDB App
        serverService.pushDataToMongoServer(savedData);

        // 4. Return immediately (The controller will return this object with the new ID to the user)
        return savedData;
    }
}