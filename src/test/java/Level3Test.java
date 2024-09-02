import Servlets.Level3;
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

public class Level3Test {
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


    private Level3 level3;
    @BeforeEach
    void setUp() throws Exception {
        System.out.println("Iniciando tests Level3");
        MockitoAnnotations.initMocks(this);
        level3 = new Level3();

        when(request.getSession()).thenReturn(session);
        when(response.getWriter()).thenReturn(writer);
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletConfig.getServletContext()).thenReturn(servletContext);
        level3.init(servletConfig);
    }

    @Test
    void testDoGet() throws Exception {
        level3.doGet(request, response);
        verify(request).setAttribute("Level", "3");
        verify(response).setContentType("text/html;charset=UTF-8");
        verify(writer).println(contains("Hola humano , mi nombre es ⏁⊑⟒⟒ ⊑⍜⋏⟒⌇⏁ ⍜⋏⟒"));
    }
    @Test
    void testDoPost_UsernameMatches() throws Exception {
        when(session.getAttribute("username")).thenReturn("Jesus");
        when(request.getParameter("usernameLevel3")).thenReturn("Jesus");
        when(servletContext.getRequestDispatcher("/level4")).thenReturn(requestDispatcher);

        level3.doPost(request, response);

        verify(requestDispatcher).forward(request, response);
    }

    @Test
    void testDoPost_UsernameDoesNotMatch() throws Exception {
        when(session.getAttribute("username")).thenReturn("Jesus");
        when(request.getParameter("usernameLevel3")).thenReturn("Nata");
        when(servletContext.getRequestDispatcher("/defeat.jsp")).thenReturn(requestDispatcher);

        level3.doPost(request, response);

        verify(requestDispatcher).forward(request, response);
    }

    @AfterAll

    public static void end (){
        System.out.println("Tests level3 exitosos");
    }

}
