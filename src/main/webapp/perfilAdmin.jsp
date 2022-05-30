<%-- 
    Document   : perfilCliente
    Created on : 30 de mai de 2022, 16:55:46
    Author     : Keller Maciel
--%>

<%@page import="com.smdcommerce.service.Usuario"%>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null || !usuario.isEhAdmin()) {
        request.setAttribute("mensagem", "Você não tem uma sessão válida de usuário do tipo cliente");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <body class="teal darken-4">
        <nav>
        <div class="nav-wrapper teal lighten-1">
            <div class = 'row'>
                <div class = 'col s2'></div>
                <div class = 'col s8'>
                <a href="index.jsp" class="brand-logo">SMDEcommerce</a>
                    <ul class="right">
                        <% if (usuario == null) { %>
                        <li style="height: 64px;"> 
                            <div class="row" style='width: 288px; height: 64px;'>
                                <div class = 'col s2'> <i class="material-icons" style="font-size: 40px">person</i> </div>
                                <div class = 'col s10' style="line-height: 0.2; vertical-align: middle; margin-top: 16px"> 
                                    <div class="row">
                                        <div class = 'col s12'> 
                                            Faça <a href="login.jsp" a style="display: inline; font-weight: bold;">login</a>
                                        </div>
                                    </div>
                                    <div class="row">
                                    <div class = 'col s12'> 
                                        ou crie seu <a href="cadastroUsuario.jsp" style="display: inline; font-weight: bold;">cadastro</a>  </div>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <% } else { %>
                        <li style="height: 64px;"> 
                            <div class="row" style='width: 90px; height: 64px;'>
                                <a href="#"> <div class = 'col s2'> <i class="material-icons" style="font-size: 40px">person</i></div></a>
                            </div>
                        </li>
                        <% } %>
                        <li style="height: 64px;">
                            <div class="row" style='width: 90px; height: 64px;'>
                                <a href="#"><div class = 'col s2'><i class="material-icons" style="font-size: 40px">shopping_cart</i></div></a>
                            </div>
                        </li>
                        <% if (usuario != null) { %>
                        <li style="height: 64px;">
                            <div class="row" style='width: 90px; height: 64px;'>
                                <a href="LogoutCliente"><div class = 'col s2'><i class="material-icons" style="font-size: 40px">exit_to_app</i></div></a>
                            </div>
                        </li>
                        <% } %>
                    </ul>
                </div>
            </div>
        </div>
      </nav>
        
        
        <div class="container">
            <div class="row">
                <div class="col s12 white" style="height: 80vh; padding: 32px;">
                    
                    <div class="row">
                        <div class="col s2">
                            <div class="circle teal lighten-5" style="width: 9vw">
                                <i class="material-icons" style="font-size: 9vw">person</i>
                            </div>
                        </div>
                        <div class="col s6">
                            Bem-vindo(a) adminstrador, <%= usuario.getNome()%>!
                        </div>
                    </div>
                    
                </div>
            </div>
        </div>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
        
        <% if (request.getAttribute("mensagem") != null) { %>
        
        <script type="text/javascript"> 
            M.toast({
                html: <%= request.getAttribute("mensagem") %>, 
                displayLength: 4000
            });
        </script> 
        
        <% } %>
    </body>
</html>
