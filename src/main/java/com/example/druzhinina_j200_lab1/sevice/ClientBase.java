package com.example.druzhinina_j200_lab1.sevice;

import com.example.druzhinina_j200_lab1.model.Address;
import com.example.druzhinina_j200_lab1.model.Client;
import jakarta.ejb.Singleton;

import java.util.*;

@Singleton
public class ClientBase {
    private List<Client> clients;
    public void addClient (String client_name, String clientID, String typeClient, String date, String ip, String mac, String model, String address)
    { if (clients == null) {
        clients = new LinkedList<>();
        clients.add(
                Client.builder()
                        .client_name(client_name)
                        .clientID(clientID)
                        .typeClient(typeClient)
                        .date(date)
                        .addresses(new LinkedList<>(Arrays.asList(
                                Address.builder().ip(ip).mac(mac).model(model).address(address).clientID(clientID).build()
                        )))
                        .build()
        );
    } else {clients.add(
            Client.builder()
                    .client_name(client_name)
                    .clientID(clientID)
                    .typeClient(typeClient)
                    .date(date)
                    .addresses(new LinkedList<>(Arrays.asList(
                            Address.builder().ip(ip).mac(mac).model(model).address(address).clientID(clientID).build()
                    )))
                    .build());}
    }
    public List<Client> getClients() {
        return clients;
    }
}
