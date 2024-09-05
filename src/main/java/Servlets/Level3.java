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

@WebServlet("/level3")
public class Level3 extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("Level", "3");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        out.println("<html>");

        out.println("<body style=\" text-align: center;\" >");
        out.println("<br>");
        out.println("<h3>" + " Lider alien: " + "</h3>");
        out.println("<h4>" + " Hola humano , mi nombre es ⏁⊑⟒⟒ ⊑⍜⋏⟒⌇⏁ ⍜⋏⟒" + "</h4>");
        out.println("<h4>" + " cual es el tuyo? " + "</h4>");
        out.println("<form action='/level3' method='post' autocomplete='off'>");
        out.println("<input type='text' name='usernameLevel3' placeholder='Ingresa tu nombre'>");
        out.println("<button type='submit'>Enviar</button>");
        out.println("<br>");
        out.println("</body>");
        out.println("</html>");

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("Level","3");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String usernameLevel3 = request.getParameter("usernameLevel3");


        if (username.equals(usernameLevel3)){
            String path = "/level4";
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
