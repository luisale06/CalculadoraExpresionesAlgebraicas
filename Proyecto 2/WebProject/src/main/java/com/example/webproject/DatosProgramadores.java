package com.example.webproject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(name = "DatosProgramadores", value = "/DatosProgramadores")
public class DatosProgramadores extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter respuesta = response.getWriter();

        respuesta.println("<html><body>");

        respuesta.println("Luis Alejandro Barreda");

        respuesta.println("<br><br>");

        respuesta.println("Luis Pablo Céspedes");

        respuesta.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
