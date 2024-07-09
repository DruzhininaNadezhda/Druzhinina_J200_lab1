package com.example.druzhinina_j200_lab1.servlet;

import java.io.*;

import com.example.druzhinina_j200_lab1.sevice.ClientBase;
import com.example.druzhinina_j200_lab1.sevice.IDControl;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


@WebServlet(name = "creatServlet", value = "/creatServlet")
public class CreatServlet extends HttpServlet {
    @EJB
    private ClientBase clientBase;
    @EJB
    IDControl idControl;
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF8");
        PrintWriter out = response.getWriter();
        out.println("<html><head>");
        out.println("<title>Создание клиента</title>");
        out.println("<h1>Создание клиента</h1>");
        out.println("<html><head>");
        out.println("<html><body>");
        out.println("<form action=\"creatServlet\" method=\"post\" >");
        out.println("<p>" + "Имя клиента"
                +"<input type=\"text\" " +
                "pattern=\"[а-яёА-ЯЁ, .\\-]{1,100}\"" +
                "name=\"client_name\" " +
                "required >" +"</p>");
        out.println("<p>" + "ID " +
                "<input type=\"number\" " +
                "pattern=\"\\d+\"" +
                " placeholder= \"0123456789\" " +
                "name=\"clientID\" " +
                "required/>"+ "</p>");
        out.println("<p>" + "Тип клиента" +
                "<label for=\"type\">Тип клиента:</label>\n" +
                "  <select id=\"type\" name=\"type\">\n" +
                "    <option value=\"Юридическое лицо\">Юридическое лицо</option>\n" +
                "    <option value=\"Физическое лицо\">Физическое лицо</option>\n" +
                "  </select>"+"</p>");
        out.println("<p>Дата создания клиента \n" +
                "<input type=\"date\" " +
                "list=\"dateList\"" +
                "name=\"date\"" +
                "required/>"+"</p>");
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
        out.println("<p>"+"<input type=\"submit\" name=\"signin\" value=\"Ввести данные\" /><br>"+"</p>");
        out.println("</form>");
        out.println("</body></html>");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        String client_name = request.getParameter("client_name");
        String clientID = request.getParameter("clientID");
        String type = request.getParameter("type");
        String date = request.getParameter("date");
        String IP = request.getParameter("IP");
        String mac = request.getParameter("MAC");
        String model = request.getParameter("model");
        String address = request.getParameter("address");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF8");
        PrintWriter out = response.getWriter();
        if (clientBase.getClients()!=null) {
            if (idControl.idControl(clientID,clientBase.getClients())) {
                out.println("<html><body>");
                out.println("<h1>Клиент с таким ID существует</h1>");
                request.getSession().setAttribute("clientID", clientID);
                request.getSession().setAttribute("IP", IP);
                request.getSession().setAttribute("clientBase", clientBase);
                out.println("<a href=\"update\">Изменить клиента</a>");
                out.println("<a href=\"creatServlet\">Создать клиента</a>");
                out.println("<a href=\"viewclients\">Список клиентов</a>");
            } else {
                clientBase.addClient(client_name, clientID, type, date, IP, mac, model, address);
                request.getSession().setAttribute("clientBase", clientBase);
                out.println("<META HTTP-EQUIV=\"Refresh\" CONTENT=\"0.1;url=viewclients\">");
                //request.getServletContext().getRequestDispatcher("/viewclients").forward(request, response);
                }
        } else {
            clientBase.addClient(client_name, clientID, type, date, IP, mac, model, address);
            request.getSession().setAttribute("clientBase", clientBase);
            out.println("<META HTTP-EQUIV=\"Refresh\" CONTENT=\"0.1;url=viewclients\">");
            //request.getServletContext().getRequestDispatcher("/viewclients").forward(request, response);
        }

        }
    }