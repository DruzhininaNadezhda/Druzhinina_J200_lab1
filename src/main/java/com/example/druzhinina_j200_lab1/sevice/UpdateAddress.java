package com.example.druzhinina_j200_lab1.sevice;

import com.example.druzhinina_j200_lab1.model.Address;
import com.example.druzhinina_j200_lab1.model.Client;
import jakarta.ejb.Singleton;
import java.util.List;

@Singleton
public class UpdateAddress {
    public void updateAddress(String clientid, List<Client> clients, String ip, String mac, String model, String address) {
        for (Client client : clients) {
            if (client.getClientID().equals(clientid)) {
                for (Address address1 : client.getAddresses()) {
                    if (address1.getIp().equals(ip)) {
                        address1.setAddress(address);
                        address1.setModel(model);
                        address1.setMac(mac);
                        return;
                    }
                    }
                    List<Address> addresses = client.getAddresses();
                    addresses.add(Address.builder().ip(ip).mac(mac).address(address).clientID(clientid).model(model).build());
                    client.setAddresses(addresses);
                }
            }
        }
    }

