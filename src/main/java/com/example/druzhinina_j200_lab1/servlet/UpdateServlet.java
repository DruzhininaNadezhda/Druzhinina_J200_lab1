package com.example.druzhinina_j200_lab1.servlet;

import com.example.druzhinina_j200_lab1.model.Client;
import com.example.druzhinina_j200_lab1.sevice.ClientBase;
import com.example.druzhinina_j200_lab1.sevice.IDControl;
import com.example.druzhinina_j200_lab1.sevice.UpdateClient;
import jakarta.ejb.EJB;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@WebServlet(name = "update", value = "/update")
public class UpdateServlet extends HttpServlet {
    @EJB
    IDControl idControl;
    @EJB
    UpdateClient updateClient;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClientBase clientBase = (ClientBase) request.getSession().getAttribute("clientBase");
        request.setCharacterEncoding("UTF8");
        String clientID = "";
        String client_name ="";
        String type ="";
        String date = "";

        if (request.getParameter("button2")!=null) {
            clientID = request.getParameter("button2");
        }else {
            clientID = Objects.toString(request.getSession().getAttribute("clientID"));
        }
        for (Client client : clientBase.getClients()) {
            if (client.getClientID().equals(clientID)) {
                client_name = client.getClient_name();
                type = client.getTypeClient();
                date = client.getDate();
            }
        }

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF8");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println(" ");
        out.println("<form action=\"update\" method=\"post\" >");
        out.println("<h1>Обновление клиента</h1>");
        out.println("<p>" + "Имя клиента"
                + "<input type=\"text\" value=\"" + client_name + "\"" +
                "pattern=\"[а-яёА-ЯЁ, .\\-]{1,100}\"" +
                "name=\"client_name\" " +
                "required >" + "</p>");
        out.println("<p>" + "ID " +
                "<input type=\"number\" " + "value=\"" + clientID + "\"" +
                "pattern=\"\\d+\"" +
                " placeholder= \"0123456789\" " +
                "name=\"clientID\" " +
                "required readonly/>" + "</p>");
        out.println("<p>" + "Тип клиента" + type +
                "<label for=\"type\"></label>\n" +
                "  <select id=\"type\" name=\"type\">\n" +
                "    <option value=\"Юридическое лицо\">Юридическое лицо</option>\n" +
                "    <option value=\"Физическое лицо\">Физическое лицо</option>\n" + "value=\"" + type + "\"" +
                "  </select>" + "</p>");
        out.println("<p>Дата создания клиента \n" +
                "<input type=\"date\" " + "value=\"" + date + "\"" +
                "list=\"dateList\"" +
                "name=\"date\"" +
                "required/>" + "</p>");
        request.getSession().setAttribute("clientID", clientID);
        out.println("<a href=\"viewaddresses\">Просмотр и добавление адресов</a>");
        out.println("<p>" + "<input type=\"submit\" name=\"signin\" value=\"Внести изменения\" /><br>" + "</p>");
        out.println("</form>");
        out.println("<a href=\"viewclients\">Список клиентов</a>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        response.setCharacterEncoding("UTF8");
        ClientBase clientBase = (ClientBase) request.getSession().getAttribute("clientBase");
        String clientID = request.getParameter("clientID");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF8");
        PrintWriter out = response.getWriter();
        String client_name = request.getParameter("client_name");
        String type = request.getParameter("type");
        String date = request.getParameter("date");
        if (clientBase.getClients() != null) {
            if (idControl.idControl(clientID, clientBase.getClients())) {
                updateClient.updateClient(clientID, clientBase.getClients(), client_name, type, date);
                doGet(request, response);
                out.println("<html><body>");
                out.println("<h1>Обновление произошло</h1>");
                out.println("<a href=\"viewclients\">Просмотр клиентов</a>");
                out.println("</body></html>");

            }
        }
    }
}
