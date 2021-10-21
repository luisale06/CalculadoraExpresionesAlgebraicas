package com.example.webproject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
@WebServlet(name = "historialExpresiones", value = "/historialExpresiones")
public class historialExpresiones extends HttpServlet {

    String username,  archivo = "E:\\TEC\\Datos 1\\Proyecto 2\\WebProject\\src\\historial.csv", lineas_archivo = null;
    BufferedReader lector = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter historial = response.getWriter();
        username = request.getParameter("username");
        historial.println("<html><body>");
        //Lector de historial en csv
        try {
            lector = new BufferedReader(new FileReader(archivo));

            while ((lineas_archivo = lector.readLine()) != null) {

                String[] fila = lineas_archivo.split(",");

                for (String indice : fila) {
                    if (fila[0].equals(username)) {
                        historial.println("  |  " + indice + "  |  ");
                        //System.out.printf("%-15s", indice);
                    }
                }

                if(fila[0].equals(username)) {
                    historial.println("<br><br>");
                    //System.out.println();
                }
            }
        }
        catch(Exception e) {e.printStackTrace();}
        finally {lector.close();}

        historial.println("</body></html>");
    }
}
