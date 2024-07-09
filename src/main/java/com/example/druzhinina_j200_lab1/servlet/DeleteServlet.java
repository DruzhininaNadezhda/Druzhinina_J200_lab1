package com.example.druzhinina_j200_lab1.servlet;

import com.example.druzhinina_j200_lab1.sevice.ClientBase;
import jakarta.ejb.EJB;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "delete", value = "/delete")
public class DeleteServlet extends HttpServlet {
    @EJB
    com.example.druzhinina_j200_lab1.sevice.Delete delete;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clientID = request.getParameter("button1");
        ClientBase clientBase = (ClientBase) request.getSession().getAttribute("clientBase");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF8");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println ("<p>" + clientID + "</p>");
        delete.deleteClient(clientID,clientBase.getClients());
        out.println("<h1>Удаление произошло</h1>");
        out.println("<a href=\"viewclients\">Просмотр клиентов</a>");
        out.println("</body></html>");

    }
}
