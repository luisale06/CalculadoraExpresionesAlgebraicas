package com.example.webproject;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.Date;
import java.util.Stack;
import static com.example.webproject.expressionsTree.*;

/**
 * Servlet encargado de calcular la expresión algebraica introducida y almacenar en el registro csv
 * la fecha, la expresión y el resultado
 * @author Luis Alejandro Barreda Acevedo
 * @see Calcular
 */

@WebServlet(name = "Calcular", value = "/Calcular")
public class Calcular extends HttpServlet {
    String expression, expr, newuser, newdate, newexp, newresult, file = "E:\\TEC\\Datos 1\\Proyecto 2\\WebProject\\src\\historial.csv";
    String[] arrayInfix;
    Stack<String> S, E;
    Node raiz;
    int resultado;

    /**
     * método predefinido de los servlets. Adquiere la expresión introducida por el usuario en el archivo
     * index.jsp (página principal de la interfaz web) y la resuelve. Seguidamente, la despliega en pantalla
     * junto con la fecha de resolución. Finalmente, almacena en el archivo csv el nombre de usuario introducido,
     * la fecha de resolución, la expresión algebraica y el resultado obtenido
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //Calculadora de expresiones____________________________________________________
        //Entrada de Datos
        expression = request.getParameter("expression");
        //Depurar la expresión algebráica
        expr = depurar(expression);
        arrayInfix = expr.split(" ");
        //Declaración de pilas
        E = new Stack<>();
        //Añade el array a la Pila de Entrada (E)
        for (int i = arrayInfix.length - 1;
             i >= 0; i--) {
            E.push(arrayInfix[i]);
        }
        //Evaluador
        try{
            S = postfix(E);
            System.out.println(S);
            raiz = construct(S);
            resultado = evalTree(raiz);
        } catch (Exception ex) {resultado = Integer.parseInt("Error en la expresión algebraica");}

        //Modificador de historial en csv_________________________________________________
        newuser = request.getParameter("username");
        newdate = String.valueOf(new Date());
        newexp = expression;
        newresult = String.valueOf(resultado);
        try {
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println(newuser + "," + newdate + ":" + "," + newexp + "," + newresult);
            pw.flush();
            pw.close();
        }
        catch(Exception e) {e.printStackTrace();}

        PrintWriter respuesta = response.getWriter();
        respuesta.println("<html><body>");
        respuesta.println("Resultado: " + resultado);
        respuesta.println("<br><br>");
        respuesta.println("Fecha: " + new Date());
        respuesta.println("</body></html>");
    }
}
