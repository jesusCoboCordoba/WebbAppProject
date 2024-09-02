import Servlets.Level2;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class Level2Test {
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


    private Level2 level2;


    @BeforeEach
    void setUp() throws Exception {
        System.out.println("Iniciando tests Level2");
        MockitoAnnotations.initMocks(this);
        level2 = new Level2();

        when(request.getSession()).thenReturn(session);
        when(response.getWriter()).thenReturn(writer);
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletConfig.getServletContext()).thenReturn(servletContext);
        level2.init(servletConfig);
    }


    @Test
    void testDoGet_Opcion1() throws Exception {
        when(request.getParameter("opcion")).thenReturn("opcion1");
        when(servletContext.getRequestDispatcher("/level3")).thenReturn(requestDispatcher);

        level2.doGet(request, response);

        verify(requestDispatcher).forward(request, response);
    }

    @Test
    void testDoGet_Opcion2() throws Exception {
        when(request.getParameter("opcion")).thenReturn("opcion2");
        when(servletContext.getRequestDispatcher("/defeat.jsp")).thenReturn(requestDispatcher);

        level2.doGet(request, response);

        verify(requestDispatcher).forward(request, response);
    }

    @AfterAll

    public static void end (){
        System.out.println("Tests level2 exitosos");
    }

}
