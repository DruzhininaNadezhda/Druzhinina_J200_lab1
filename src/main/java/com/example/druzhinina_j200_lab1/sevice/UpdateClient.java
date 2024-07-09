package com.example.druzhinina_j200_lab1.sevice;

import com.example.druzhinina_j200_lab1.model.Client;
import jakarta.ejb.Singleton;
import java.util.List;
@Singleton
public class UpdateClient {

    public void updateClient(String clientid, List<Client> clients, String client_name, String typeClient, String date) {
        for (Client client : clients) {
                if (client.getClientID().equals(clientid)) {
                    client.setClient_name(client_name);
                    client.setTypeClient(typeClient);
                    client.setDate(date);
                    //client.setAddresses(updateAddress.updateAddress(clientid, client.getAddresses(), ip, mac, model, address));
                        }
                    }
                }
            }