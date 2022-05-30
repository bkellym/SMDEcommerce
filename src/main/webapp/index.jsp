<%-- 
    Document   : index
    Created on : 30 de mai de 2022, 11:05:15
    Author     : Keller Maciel
--%>
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
                        <li style="height: 64px;">
                            <div class="row" style='width: 90px; height: 64px;'>
                                <div class = 'col s2'><a href="sass.html"><i class="material-icons" style="font-size: 40px">shopping_cart</i></a></div>
                            </div>
                      </li>
                    </ul>
                </div>
            </div>
        </div>
      </nav>
        
        
        <div class="container">
            <div class="row">
                <div class="col s12 white" style="height: 80vh;"> </div>
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
