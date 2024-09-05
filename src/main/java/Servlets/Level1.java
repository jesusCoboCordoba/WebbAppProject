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

@WebServlet("/level1")
public class Level1 extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("Level","1");
        HttpSession sesion = request.getSession();
        Integer games = (Integer) sesion.getAttribute("gameCounter");
        if (games == null) {
            games = 0;
        }
        games++;
        sesion.setAttribute("gameCounter", games);

        String username = request.getParameter("username");
        sesion.setAttribute("username",username);
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        out.println("<html>");
        out.println("<body style=\" text-align: center;\">");
        out.println("<head style= text-align: center; > <title>Alien:</title> </head>");
        out.println("<h2 >"+ "Entonces "+username + "¿ Vas a aceptar nuestro desafío ? " +"</h2>");
        out.println("<form action='/level1' method='post'>");
        out.println("<input type='radio' name='opcion' value='opcion1'> Aceptar el desafio <br>");
        out.println("<input type='radio' name='opcion' value='opcion2'> Rehusar el desafio <br>");
        out.println("<input type='submit' value='Enviar'>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("Level","1");

        String opcionSeleccionada = request.getParameter("opcion");

        if (opcionSeleccionada.equals("opcion1")){
            String path = "/level2";
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
