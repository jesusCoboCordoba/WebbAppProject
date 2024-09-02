<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
   <head>
      <title> Each Tag Example&</title>
   </head>

   <body style="background-color: red; text-align: center;">

   <c:choose>
           <c:when test="${Level == '1'}">
               <h1> DERROTA  <h1>
               <p> No has aceptado el reto </p>
           </c:when>
           <c:when test="${Level == '2'}">
              <h1> DERROTA  <h1>
              <p> No has aceptado ir con el lider alien </p>
           </c:when>
           <c:when test="${Level == '3'}">
           <h1> DERROTA  <h1>
           <p> Has mentido sobre tu nombre </p>
           </c:when>
           <c:when test="${Level == '4'}">
              <h1> DERROTA  <h1>
                      <p> Respuesta Incorrecta </p>
                      </c:when>

           <c:otherwise>
               <p>Has perdido. ¡Inténtalo de nuevo!</p>
           </c:otherwise>

        </c:choose>
     <c:out value = " Intentalo de nuevo" />
   </body>
 <form action="/WebbAppProject/welcomePage.jsp" method="GET">
     <button type="submit"> reiniciar </button>
 </form>

</html>