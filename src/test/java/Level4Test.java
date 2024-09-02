import Servlets.Level4;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.PrintWriter;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class Level4Test {
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
    @Mock
    private Level4 level4;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        level4 = new Level4();

        when(request.getSession()).thenReturn(session);
        when(response.getWriter()).thenReturn(writer);
        when(request.getServletContext()).thenReturn(servletContext);


        when(servletConfig.getServletContext()).thenReturn(servletContext);
        level4.init(servletConfig);
    }

    @Test
    void testDoGet_CorrectAnswer() throws Exception {
        when(session.getAttribute("pregunta")).thenReturn("Cuando tenia 4 años mi hermana tenia el doble de mi edad ahora tengo 40 años, cuantos años tiene mi hermana?");
        when(request.getParameter("respuesta")).thenReturn("44");
        when(servletContext.getRequestDispatcher("/victory.jsp")).thenReturn(requestDispatcher);

        level4.doGet(request, response);

        verify(requestDispatcher).forward(request, response);
    }

    @Test
    void testDoGet_WrongAnswer() throws Exception {
        when(session.getAttribute("pregunta")).thenReturn("Cuando tenia 4 años mi hermana tenia el doble de mi edad ahora tengo 40 años, cuantos años tiene mi hermana?");
        when(request.getParameter("respuesta")).thenReturn("45");
        when(servletContext.getRequestDispatcher("/defeat.jsp")).thenReturn(requestDispatcher);

        level4.doGet(request, response);

        verify(requestDispatcher).forward(request, response);
    }

}
