package com.example.druzhinina_j200_lab1.sevice;

import com.example.druzhinina_j200_lab1.model.Client;
import jakarta.ejb.Singleton;

import java.util.List;
@Singleton
public class Delete {
    public void deleteClient(String clientid, List<Client> clients) {
        for (Client client : clients) {
            if (client.getClientID().equals(clientid)) {
                clients.remove(client);
            }
        }
    }
}
