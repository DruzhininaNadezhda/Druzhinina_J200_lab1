package com.example.druzhinina_j200_lab1.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class IP {
    private String IpAddress;
    public String getIp() {
        try {
            URL url = new URL("https://httpbin.org/ip");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder responseContent = new StringBuilder();
            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                responseContent.append(inputLine);
            }
            reader.close();
            IpAddress = responseContent.substring(13);
        } catch (IOException e) {
            IpAddress = "Не_определён._Необходимо_заполнить_вручную";
            e.printStackTrace();
        }
        return IpAddress;
    }
        public  String getMac(){
        String mac = null;
            try {
                InetAddress ip = InetAddress.getLocalHost();
                System.out.println("Current IP address : " + ip.getHostAddress());
                NetworkInterface network = NetworkInterface.getByInetAddress(ip);

                byte[] mac1 = network.getHardwareAddress();

                System.out.print("Current MAC address : ");

                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < mac1.length; i++) {
                    sb.append(String.format("%02X%s", mac1[i], (i < mac1.length - 1) ? "-" : ""));
                }
                mac= sb.toString();
                System.out.println(sb.toString());

            } catch (UnknownHostException e) {

                e.printStackTrace();

            } catch (SocketException e){

                e.printStackTrace();

            }

            return mac.toString();
        }

    }
