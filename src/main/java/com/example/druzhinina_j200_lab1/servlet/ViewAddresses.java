package com.example.druzhinina_j200_lab1.servlet;

import com.example.druzhinina_j200_lab1.model.Address;
import com.example.druzhinina_j200_lab1.model.Client;
import com.example.druzhinina_j200_lab1.sevice.ClientBase;
import com.example.druzhinina_j200_lab1.sevice.UpdateAddress;
import jakarta.ejb.EJB;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@WebServlet(name = "viewaddresses", value = "/viewaddresses")
public class ViewAddresses extends HttpServlet {
    @EJB
    UpdateAddress updateAddress;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        ClientBase clientBase = (ClientBase) request.getSession().getAttribute("clientBase");
        String clientID = Objects.toString(request.getSession().getAttribute("clientID"));
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF8");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<table cellspacing=\"3\" cellpadding=\"3\" border=\"1\">");
        out.println("<tr>");
        out.println("<th>IP</th>");
        out.println("<th>MAC</th>");
        out.println("<th>Модель</th>");
        out.println("<th>Адрес</th>");
        out.println("</tr>");
        for (Client client : clientBase.getClients()) {
            if (!client.getAddresses().isEmpty()) {
                for (Address address1 : client.getAddresses()) {
                    if (address1.getClientID().equals(clientID)) {
                        out.println("<td>" + address1.getIp() + "</td>");
                        out.println("<td>" + address1.getMac() + "</td>");
                        out.println("<td>" + address1.getModel() + "</td>");
                        out.println("<td>" + address1.getAddress() + "</td>");
                        out.println("</tr>");
                    }
                }
            }
        }
        out.println("<form action=\"viewaddresses\" method=\"post\" >");
        out.println("<p>" + "IP " +
                "<input type=\"text\"" +
                " pattern=\"^([0-1][\\d][\\d].|2[0-4][\\d].|25[0-5].){3,3}([0-1][\\d][\\d]|2[0-4][\\d]|25[0-5])\" " +
                "placeholder= \"255.255.255.255\"" +
                "name=\"IP\"" +
                "required/>"+"</p>");
        out.println("<p>" + "MAC " +
                "<input type=\"text\" " +
                " pattern=\"([\\dA-Za-z]{1,2}-){5,5}([\\dA-Za-z]{1,2})\" " +
                "placeholder= \"1A-1B-2C-4E-8H-9\"" +
                "name=\"MAC\"" +
                "required/>"+"</p>");
        out.println("<p>" + "Модель устройства"
                +"<input type=\"text\" " +
                "pattern=\".{1,100}\"" +
                "name=\"model\" " +
                "required >" +"</p>");
        out.println("<p>" + "Адрес местонахождения устройства"
                +"<input type=\"text\" " +
                "pattern=\".{1,200}\"" +
                "name=\"address\" " +
                "required >" +"</p>");
        out.println("<p>" + "<input type=\"submit\" name=\"signin\" value=\"Добавить/изменить адрес\" /><br>" + "</p>");
        out.println("<a href=\"update\">Внести изменения</a>");
        out.println("</form>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        response.setCharacterEncoding("UTF8");
        ClientBase clientBase = (ClientBase) request.getSession().getAttribute("clientBase");
        String clientID = Objects.toString(request.getSession().getAttribute("clientID"));
        String IP = request.getParameter("IP");
        String mac = request.getParameter("MAC");
        String model = request.getParameter("model");
        String address = request.getParameter("address");
                        updateAddress.updateAddress(clientID, clientBase.getClients(), IP, mac, model, address);
                        doGet(request, response);
    }
}

