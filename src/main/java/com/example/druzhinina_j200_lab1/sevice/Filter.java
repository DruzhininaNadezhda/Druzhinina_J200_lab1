package com.example.druzhinina_j200_lab1.sevice;
import com.example.druzhinina_j200_lab1.model.Address;
import com.example.druzhinina_j200_lab1.model.Client;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Filter {
    public List<Client> filterType(String type, List<Client> clients) {
        System.out.println(type);
        if (type == null || type.isEmpty()) {
            return clients;
        } else {
            return clients.stream().filter(client -> client.getTypeClient().equals(type)).collect(Collectors.toList());
        }
    }

    public List<Client> filterName(String name, List<Client> clients) {
        if (name == null || name.isEmpty()) {
            return clients;
        } else {
            List<Client> result = clients.stream().filter(client -> client.getClient_name().contains(name)).collect(Collectors.toList());
            return result;
        }
    }

}
