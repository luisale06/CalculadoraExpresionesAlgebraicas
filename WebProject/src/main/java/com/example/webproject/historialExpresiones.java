package com.example.webproject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Servlet encargado de desplegar el registro de expresiones algebraicas, resultado y fechas de resolución
 * @author Luis Alejandro Barreda Acevedo
 * @see historialExpresiones
 */

@WebServlet(name = "historialExpresiones", value = "/historialExpresiones")
public class historialExpresiones extends HttpServlet {

    String username,  archivo = "E:\\TEC\\Datos 1\\Proyecto 2\\WebProject\\src\\historial.csv", lineas_archivo = null;
    BufferedReader lector = null;

    /**
     * Metodo predefinido de los servlets, encargado de recibir el nombre de usuario de referencia
     * para buscar en el archivo csv el historial de calculos a nombre del mismo.
     * @param request
     * @param response
     * @throws IOException
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
