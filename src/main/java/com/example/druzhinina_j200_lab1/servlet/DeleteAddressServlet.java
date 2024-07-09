package com.example.druzhinina_j200_lab1.servlet;

import com.example.druzhinina_j200_lab1.sevice.ClientBase;
import com.example.druzhinina_j200_lab1.sevice.DeleteAddress;
import jakarta.ejb.EJB;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "deleteaddressservlet", value = "/deleteaddressservlet")
public class DeleteAddressServlet extends HttpServlet {
    @EJB
    DeleteAddress deleteAddress;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clientID = request.getParameter("button3");
        String ip = request.getParameter("button4");
        ClientBase clientBase = (ClientBase) request.getSession().getAttribute("clientBase");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF8");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println ("<p>" + clientID + "</p>");
        deleteAddress.deleteAddress(clientID,ip,clientBase.getClients());
        out.println("<h1>Удаление произошло</h1>");
        out.println("<a href=\"viewclients\">Просмотр клиентов</a>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clientID = request.getParameter("button3");
        String ip = request.getParameter("button4");
        ClientBase clientBase = (ClientBase) request.getSession().getAttribute("clientBase");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF8");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println ("<p>" + clientID + "</p>");
        deleteAddress.deleteAddress(clientID,ip,clientBase.getClients());
        out.println("<h1>Удаление произошло</h1>");
        out.println("<a href=\"viewclients\">Просмотр клиентов</a>");
        out.println("</body></html>");
    }
}
