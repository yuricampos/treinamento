<%-- 
    Document   : index
    Created on : 13/05/2013, 16:02:26
    Author     : yuricampos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Faça seu login</title>
        <link rel="stylesheet" type="text/css" href="css/bootstrap-responsive.css" />
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
        <script type="text/javascript" src="js/bootstrap.js"></script>
    </head>
</head>
<body>
    <div class="hero-unit">
        <h1>Faça o login</h1>
        <p class="text-success">Para começar, faça seu login, caso ainda não tenha, <a href="iniciarcadastro.jsp">faça seu cadastro</a></p>
        <%
                                String status = (String) request.getAttribute("status");
                                if (status == "erro") {%>
        <p class="text-error">Login ou senha incorretos!</p>
        <%}%>
        <form class="form-horizontal" name="login" action="Login" method="post">
            <div class="control-group">
                <label class="control-label" for="usuario">Usuário</label>
                <div class="controls">
                    <input type="text" id="usuario" name="usuario" placeholder="Usuário">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="senha">Senha</label>
                <div class="controls">
                    <input type="password" id="senha" name="senha" placeholder="Senha">
                </div>
            </div>
            <div class="control-group">
                <div class="controls">
                    <button type="submit" class="btn btn-large btn-primary">Acessar!</button>
                </div>
            </div>
        </form>
    </div>
</body>
</html>
