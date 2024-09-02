import Servlets.Level1;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.PrintWriter;

import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class Level1Test {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private ServletContext servletContext;

    @Mock
    private RequestDispatcher requestDispatcher;

    @Mock
    private PrintWriter writer;

    @Mock
    private ServletConfig servletConfig;


    private Level1 level1;


    @BeforeEach
    void setUp() throws Exception {
        System.out.println("Iniciando tests Level1");
        MockitoAnnotations.initMocks(this);
        level1 = new Level1();

        when(request.getSession()).thenReturn(session);
        when(response.getWriter()).thenReturn(writer);
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletConfig.getServletContext()).thenReturn(servletContext);
        level1.init(servletConfig);
    }

    @Test
    void testDoGet() throws Exception {
        when(request.getParameter("username")).thenReturn("Jesus");

        level1.doGet(request, response);

        verify(session).setAttribute("gameCounter", 1);
        verify(session).setAttribute("username", "Jesus");

        verify(response).setContentType("text/html;charset=UTF-8");
        verify(writer).println(contains("Jesus"));
    }
    @Test
    void testDoPost_Opcion1() throws Exception {
        when(request.getParameter("opcion")).thenReturn("opcion1");
        when(servletContext.getRequestDispatcher("/level2")).thenReturn(requestDispatcher);

        level1.doPost(request, response);

        verify(requestDispatcher).forward(request, response);
    }

    @Test
    void testDoPost_Opcion2() throws Exception {
        when(request.getParameter("opcion")).thenReturn("opcion2");
        when(servletContext.getRequestDispatcher("/defeat.jsp")).thenReturn(requestDispatcher);

        level1.doPost(request, response);

        verify(requestDispatcher).forward(request, response);
    }

    @AfterAll

    public static void end (){
        System.out.println("Tests level1 exitosos");
    }

}