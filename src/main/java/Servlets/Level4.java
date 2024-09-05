package Servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

@WebServlet("/level4")
public class Level4 extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        request.setAttribute("Level", "4");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String pregunta = preguntasRandom ();
        session.setAttribute("pregunta",pregunta);
        response.setContentType("text/html;charset=UTF-8");
        out.println("<html>");
        out.println("<body style=\" text-align: center;\">");
        out.println("<br>");
        out.println("<h3 >" + " Lider alien: " + "</h3>");
        out.println("<h4>" + " Muy bien " + username + "  responde esto y seras libre" + "</h4>");
        out.println("<h4>" +  pregunta + "</h4>");
        out.println("<form action='/level4' method='get' autocomplete='off' >");
        out.println("<input type='text' name='respuesta' placeholder='Ingresa tu respuesta'>");
        out.println("<button type='submit'>Enviar</button>");
        out.println("<br>");
        out.println("</body>");
        out.println("</html>");
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("Level", "4");
        HttpSession session = request.getSession();

        HashMap<String,String> PreguntasYRespuestas= mapaPreguntas();
        String RespuestaUsuario = request.getParameter("respuesta");
        System.out.println(RespuestaUsuario);
        String RespuestaCorrecta = PreguntasYRespuestas.get(session.getAttribute("pregunta"));
        System.out.println(RespuestaCorrecta);
        if(RespuestaUsuario.equalsIgnoreCase(RespuestaCorrecta)){

            String path = "/victory.jsp";
            ServletContext servletContext = this.getServletContext();
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
            requestDispatcher.forward(request, response);
        }else{
            String path = "/defeat.jsp";
            ServletContext servletContext = this.getServletContext();
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
            requestDispatcher.forward(request, response);
        }
    }





        public HashMap<String,String> mapaPreguntas (){
        HashMap<String,String> PreguntasYRespuestas = new HashMap<>();
        String pregunta1 = "Cuando tenia 4 años mi hermana tenia el doble de mi edad ahora tengo 40 años, cuantos años tiene mi hermana?";
        String pregunta2 = "El monte Everest se descubrió en 1852 ¿Cuál era la montaña más alta antes?";
        String pregunta3 = "Tu hermana y tú tienen la misma cantidad de uvas.¿Cuántas uvas tienes que darle a tu hermana para que ella tenga 10 más que tú?";
        String respuesta1 = "44";
        String respuesta2 = "El monte Everest";
        String respuesta3 = "5";


        PreguntasYRespuestas.put(pregunta1, respuesta1);
        PreguntasYRespuestas.put(pregunta2, respuesta2);
        PreguntasYRespuestas.put(pregunta3, respuesta3);
        return PreguntasYRespuestas;
    }
    public String preguntasRandom (){
       ArrayList<String> bancoDePreguntas = new ArrayList<>();
        String pregunta1 = "Cuando tenia 4 años mi hermana tenia el doble de mi edad ahora tengo 40 años, cuantos años tiene mi hermana?";
        String pregunta2 = "El monte Everest se descubrió en 1852 ¿Cuál era la montaña más alta antes?";
        String pregunta3 = "Tu hermana y tú tienen la misma cantidad de uvas.¿Cuántas uvas tienes que darle a tu hermana para que ella tenga 10 más que tú?";

        bancoDePreguntas.add(pregunta1);
        bancoDePreguntas.add(pregunta2);
        bancoDePreguntas.add(pregunta3);
        Random random = new Random();
        int randomNumber = random.nextInt(3) ;

        return bancoDePreguntas.get(randomNumber);
    }
}
