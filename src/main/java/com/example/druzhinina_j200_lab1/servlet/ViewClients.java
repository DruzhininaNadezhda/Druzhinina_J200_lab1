package com.example.druzhinina_j200_lab1.servlet;

import com.example.druzhinina_j200_lab1.model.Address;
import com.example.druzhinina_j200_lab1.model.Client;
import com.example.druzhinina_j200_lab1.sevice.ClientBase;
import com.example.druzhinina_j200_lab1.sevice.Filter;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;

@WebServlet(name = "viewclients", value = "/viewclients")
public class ViewClients extends HttpServlet {


    private Filter filter;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        htmlPage(request, response);
        request.setCharacterEncoding("UTF8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF8");
        PrintWriter out = response.getWriter();
        ClientBase clientBase = (ClientBase) request.getSession().getAttribute("clientBase");
        if (clientBase != null) {
            for (Client client : clientBase.getClients()) {
                if (!client.getAddresses().isEmpty()) {
                    for (Address address1 : client.getAddresses()) {
                        out.println("<tr>");
                        out.println("<td>" + client.getClient_name() + "</td>");
                        out.println("<td>" + client.getClientID() + "</td>");
                        out.println("<td>" + client.getTypeClient() + "</td>");
                        out.println("<td>" + client.getDate() + "</td>");
                        out.println("<td>" + address1.getIp() + "</td>");
                        out.println("<td>" + address1.getMac() + "</td>");
                        out.println("<td>" + address1.getModel() + "</td>");
                        out.println("<td>" + address1.getAddress() + "</td>");
                        out.println("<td>" + "<form action=\"delete\" method=\"post\">" +
                                "<p><input type=\"hidden\" name=\"button1\" value=\"" + client.getClientID() + "\"size =\"5\"</p>" +
                                "<p><input type=\"submit\" name=\"button11\" value=\"Удалить клиента \n и все его адреса\"</p></form>");
                        out.println("<p><form action=\"update\" method=\"doPost\"><p>" +
                                "<p><input type=\"hidden\" name=\"button2\" value=\"" + client.getClientID() + "\"size =\"5\"</p>" +
                                "<p><input type=\"submit\" name=\"button22\" value=\"Обновить клиента\"</p></form>");
                        out.println("<p><form action=\"deleteaddressservlet\" method=\"doPost\"><p>" +
                                "<p><input type=\"hidden\" name=\"button3\" value=\"" + client.getClientID() + "\"size =\"5\"</p>" +
                                "<p><input type=\"hidden\" name=\"button4\" value=\"" + address1.getIp() + "\"size =\"5\"</p>" +
                                "<p><input type=\"submit\" name=\"button34\" value=\"Удалить адрес\"</p></form>" + "</td>");
                        out.println("</tr>");
                    }
                }
            }
        }
        out.println("</table>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        htmlPage(request, response);
        request.setCharacterEncoding("UTF8");
        String typeFilter = request.getParameter("typeFilter");
        String dataFilter = request.getParameter("dataFilter");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF8");
        PrintWriter out = response.getWriter();
        List<Client> ddd;
        ClientBase clientBase = (ClientBase) request.getSession().getAttribute("clientBase");
        ddd = clientBase.getClients();
        filter = new Filter();
        for (Client client : clientBase.getClients()) {
            for (Address address1 : client.getAddresses()) {
                if (address1.getAddress().contains(dataFilter)) {
                    out.println("<tr>");
                    out.println("<td>" + client.getClient_name() + "</td>");
                    out.println("<td>" + client.getClientID() + "</td>");
                    out.println("<td>" + client.getTypeClient() + "</td>");
                    out.println("<td>" + client.getDate() + "</td>");
                    out.println("<td>" + address1.getIp() + "</td>");
                    out.println("<td>" + address1.getMac() + "</td>");
                    out.println("<td>" + address1.getModel() + "</td>");
                    out.println("<td>" + address1.getAddress() + "</td>");
                    out.println("<td>" + "<form action=\"delete\" method=\"post\">" +
                            "<p><input type=\"hidden\" name=\"button1\" value=\"" + client.getClientID() + "\"size =\"5\"</p>" +
                            "<p><input type=\"submit\" name=\"button11\" value=\"Удалить клиента \n и все его адреса\"</p></form>");
                    out.println("<p><form action=\"update\" method=\"doPost\"><p>" +
                            "<p><input type=\"hidden\" name=\"button2\" value=\"" + client.getClientID() + "\"size =\"5\"</p>" +
                            "<p><input type=\"submit\" name=\"button22\" value=\"Изменить клиента\"</p></form>");
                    out.println("<p><form action=\"deleteaddressservlet\" method=\"doPost\"><p>" +
                            "<p><input type=\"hidden\" name=\"button3\" value=\"" + client.getClientID() + "\"size =\"5\"</p>" +
                            "<p><input type=\"hidden\" name=\"button4\" value=\"" + address1.getIp() + "\"size =\"5\"</p>" +
                            "<p><input type=\"submit\" name=\"button22\" value=\"Удалить адрес\"</p></form>" + "</td>");
                    out.println("</tr>");
                }
            }
        }for (Client client : filter.filterType(typeFilter, filter.filterName(dataFilter, ddd))) {
            for (Address address1 : client.getAddresses()) {
                    out.println("<tr>");
                    out.println("<td>" + client.getClient_name() + "</td>");
                    out.println("<td>" + client.getClientID() + "</td>");
                    out.println("<td>" + client.getTypeClient() + "</td>");
                    out.println("<td>" + client.getDate() + "</td>");
                    out.println("<td>" + address1.getIp() + "</td>");
                    out.println("<td>" + address1.getMac() + "</td>");
                    out.println("<td>" + address1.getModel() + "</td>");
                    out.println("<td>" + address1.getAddress() + "</td>");
                    out.println("<td>" + "<form action=\"delete\" method=\"post\">" +
                            "<p><input type=\"hidden\" name=\"button1\" value=\"" + client.getClientID() + "\"size =\"5\"</p>" +
                            "<p><input type=\"submit\" name=\"button11\" value=\"Удалить клиента \n и все его адреса\"</p></form>");
                    out.println("<p><form action=\"update\" method=\"doPost\"><p>" +
                            "<p><input type=\"hidden\" name=\"button2\" value=\"" + client.getClientID() + "\"size =\"5\"</p>" +
                            "<p><input type=\"submit\" name=\"button22\" value=\"Изменить клиента \n и его адреса\"</p></form>");
                    out.println("<p><form action=\"deleteaddressservlet\" method=\"doPost\"><p>" +
                        "<p><input type=\"hidden\" name=\"button3\" value=\"" + client.getClientID() + "\"size =\"5\"</p>" +
                            "<p><input type=\"hidden\" name=\"button4\" value=\"" + address1.getIp() + "\"size =\"5\"</p>" +
                        "<p><input type=\"submit\" name=\"button22\" value=\"Удалить адрес\"</p></form>" + "</td>");
                    out.println("</tr>");
                }
        }

        out.println("</table>");
        out.println("</body></html>");

    }

    private void htmlPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF8");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Список клиентов</h1>");
        out.println("<form action=\"viewclients\" method=\"post\" >");
        out.println("<h1>Поиск</h1>");
        out.println("<p><label for=\"typeFilter\">Тип клиента:</label>\n" +
                "  <select id=\"typeFilter\" name=\"typeFilter\">\n" +
                "    <option value=\"Юридическое лицо\">Юридическое лицо</option>\n" +
                "    <option value=\"Физическое лицо\">Физическое лицо</option>\n" +
                "  </select>" + "</p>");
        out.println("<p>Данные клиента: имя/адрес"
                + "<input type=\"text\"name=\"dataFilter\" " + "</p>");
        out.println("<p>" + "<input type=\"submit\" name=\"signin\" value=\"Поиск\" /><br>" + "</p>");
        out.println("</form>");
        out.println("</body></html>");
        out.println("<a href=\"viewclients\">Список всех клиентов</a>");
        out.println("<a href=\"creatServlet\">Добавить клиента</a>");
        out.println("<table cellspacing=\"7\" cellpadding=\"7\">");
        out.println("<style> " +
                "table { width: 70%; border-collapse: collapse; } " +
                "td, th { padding: 2px; " +
                "border: 1px solid #333; } " +
                "th { background: #cadadd; } " +
                "tbody tr:hover {background: #334FFF; color: #000103; } " + //цвета выделения мышкой
                "</style>");
        out.println("<tr>");
        out.println("<th>Имя клиента</th>");
        out.println("<th>ID</th>");
        out.println("<th>Тип клиента</th>");
        out.println("<th>Дата создания клиента</th>");
        out.println("<th>IP</th>");
        out.println("<th>MAC</th>");
        out.println("<th>Модель</th>");
        out.println("<th>Адрес</th>");
        out.println("</tr>");

    }
}


