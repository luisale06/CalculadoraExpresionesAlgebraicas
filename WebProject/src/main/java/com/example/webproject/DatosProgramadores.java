package com.example.webproject;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet en el cual se despliegan los datos de los programadores del proyecto
 * @author Luis Alejandro Barreda Acevedo
 * @see DatosProgramadores
 */

@WebServlet(name = "DatosProgramadores", value = "/DatosProgramadores")
public class DatosProgramadores extends HttpServlet {

    /**
     * Metodo predefinido de los servlets. Genera, mediante codigo HTML,
     * la pagina donde se visualizan los nombres y datos deseados
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter respuesta = response.getWriter();

        respuesta.println("<html><body>");

        respuesta.println("Estudiantes de Ingeniería en Computadores del Tecnológico de Costa Rica");
        respuesta.println("<br><br>");
        respuesta.println("<br><br>");

        respuesta.println("Luis Alejandro Barreda");
        respuesta.println("<br><br>");
        respuesta.println("ID interno: 2020425927");
        respuesta.println("<br><br>");
        respuesta.println("<br><br>");

        respuesta.println("Luis Pablo Céspedes");
        respuesta.println("<br><br>");
        respuesta.println("ID interno: 201156162");
        respuesta.println("<br><br>");
        respuesta.println("<br><br>");

        respuesta.println("</body></html>");
    }
}
