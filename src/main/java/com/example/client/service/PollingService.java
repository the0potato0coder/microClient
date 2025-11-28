package com.example.client.service;

import com.example.client.model.Client;
import com.example.client.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PollingService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private RestTemplate restTemplate;

    // Cron expression for 6:00 PM every day
    @Scheduled(cron = "0 0 18 * * *")
    public void checkTicketStatus() {
        System.out.println("Starting Daily Polling for Ticket Status...");

        // 1. Get all uncollected tickets from MySQL
        List<Client> pendingClients = clientRepository.findByCollectedFalse();

        for (Client client : pendingClients) {
            try {
                // 2. Call Microserver to check status
                // Assumes microserver has endpoint: GET /server/check/{transactionId}
                String url = "http://localhost:8090/server/check/" + client.getTransactionId();

                Boolean isCollected = restTemplate.getForObject(url, Boolean.class);

                // 3. If collected on server, update MySQL
                if (Boolean.TRUE.equals(isCollected)) {
                    client.setCollected(true);
                    clientRepository.save(client);
                    System.out.println("Updated status for Transaction ID: " + client.getTransactionId());
                }
            } catch (Exception e) {
                System.err.println("Error polling for Transaction ID " + client.getTransactionId() + ": " + e.getMessage());
            }
        }
        System.out.println("Polling completed.");
    }
}