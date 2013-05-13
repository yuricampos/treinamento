<%-- 
    Document   : sucesso
    Created on : 13/05/2013, 17:36:01
    Author     : yuricampos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
String usuario = (String) session.getAttribute("usuario");
if(usuario == null){
            String redirectURL = "index.jsp";
        response.sendRedirect(redirectURL);
}
            %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login com sucesso! Bem vindo <%=usuario %></h1>
        <a href="logout.jsp">Sair</a>
    </body>
</html>
