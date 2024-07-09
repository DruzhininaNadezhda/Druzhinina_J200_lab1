package com.example.druzhinina_j200_lab1.sevice;

import com.example.druzhinina_j200_lab1.model.Address;
import com.example.druzhinina_j200_lab1.model.Client;
import jakarta.ejb.Singleton;

import java.util.List;
@Singleton
public class DeleteAddress {
    public void deleteAddress(String clientid, String ip, List<Client> clients) {
        for (Client client : clients) {
            for (Address address : client.getAddresses()) {
                if (address.getIp().equals(ip)){
                    client.getAddresses().remove(address);
                }
            }
        }
    }
}