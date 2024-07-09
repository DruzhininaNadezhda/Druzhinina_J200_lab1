package com.example.druzhinina_j200_lab1.sevice;

import com.example.druzhinina_j200_lab1.model.Client;
import jakarta.ejb.Singleton;

import java.util.List;

@Singleton
public class IDControl {
    public boolean idControl(String id, List<Client> clients) {
        for (Client client : clients) {
            if (client.getClientID() != null) {
                if (client.getClientID().equals(id)) {
                    return true;
                }
            }
        } return false;
    }
}
