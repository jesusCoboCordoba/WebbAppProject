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


@WebServlet("/level2")
public class Level2 extends HttpServlet {
    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("Level", "2");
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        out.println("<html>");
        out.println("<body style=\" text-align: center;\" >");
        out.println("<br>");
        out.println("<h3 >" + name + " estas dispuesto a hablar con nuestro lider?" + "</h3>");
        out.println("<form action='/level2' method='get'>");
        out.println("<input type='radio' name='opcion' value='opcion1'> Aceptar ir con el lider alien <br>");
        out.println("<input type='radio' name='opcion' value='opcion2'> Rehusarte hablar con el <br>");
        out.println("<input type='submit' value='Enviar'>");
        out.println("<br>");
        out.println("<h4 style=\" text-align: center; border: solid 1px black; \">" +"Estadisticas" );
        out.println("<br>");
        out.println("<br>");
        out.println("Numero de juegos:"+ session.getAttribute("gameCounter"));
        out.println("<br>");
        out.println( "Nombre del jugador:" +session.getAttribute("username"));
        out.println("<br>");
        out.println(" IP:" + request.getRemoteAddr() + "</h4>");
        out.println("</body>");
        out.println("</html>");
    }

        public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


            request.setAttribute("Level", "2");

            String opcionSeleccionada = request.getParameter("opcion");

            if (opcionSeleccionada.equals("opcion1")){
            String path = "/level3";
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
}
