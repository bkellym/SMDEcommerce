<%-- 
    Document   : login
    Created on : 30 de mai de 2022, 11:05:15
    Author     : Keller Maciel
--%>

<%@page import="com.smdcommerce.service.Usuario"%>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
%>
<%@include file="header.jsp" %>        
        
        <div class="container" style="margin-top: 8vh;">
            <div class="row">
                <div class="col s1"> </div>
                <div class="col s10 white" style="border-radius: 64px 0px; padding: 32px 16px ">
                    <h3 class="deep-orange-text text-darken-2" style="text-align: center; font-weight: bold;"> Realize seu Login </h3>
                    
                    <form class="col s12" action="LoginCliente" method="post">
                        <div class="row">
                            <div class="col s2"> </div>
                            <div class="input-field col s8">
                                <input id="login" name="login" type="text" class="validate">
                                <label for="login">Login</label>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="col s2"> </div>
                            <div class="input-field col s8">
                                <input id="senha" name="senha" type="password" class="validate">
                                <label for="senha">Senha</label>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="col s4"> </div>
                            <div class="input-field col s4">
                                <button class="btn waves-effect waves-light deep-orange darken-2" type="submit" name="action" style="width: 100%;"> Entrar </button>
                            </div>
                        </div>
                    </form>
                    
                    <div class="row">
                        <div style="text-align: center;">
                            Não possui cadastro ainda? <a href="sass.html" class="deep-orange-text text-darken-2" > Cadastre-se </a> agora!                        
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    </body>
</html>
