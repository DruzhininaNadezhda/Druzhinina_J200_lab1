package com.example.druzhinina_j200_lab1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    private String clientID;
    Client client;
    private String ip;
    private String mac;
    private String model;
    private String address;
}
