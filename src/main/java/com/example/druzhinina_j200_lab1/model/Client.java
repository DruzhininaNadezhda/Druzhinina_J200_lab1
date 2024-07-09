package com.example.druzhinina_j200_lab1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {
    private String clientID;
    private String client_name;
    private String typeClient;
    private String  date;
    private List<Address> addresses;


}
