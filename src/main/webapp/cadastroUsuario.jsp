<%-- 
    Document   : cadastroUsuario
    Created on : 30 de mai de 2022, 12:29:25
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
                    <h3 class="deep-orange-text text-darken-2" style="text-align: center; font-weight: bold;"> Cadastre-se </h3>
                    
                    <form action="CadastrarUsuario" class="col s12" method="post">
                        <div class="row">
                            <div class="col s2"> </div>
                            <div class="input-field col s4">
                                <input id="nome" name="nome" type="text" class="validate">
                                <label for="nome">Nome</label>
                            </div>
                            <div class="input-field col s4">
                                <input id="sobrenome" name="sobrenome" type="text" class="validate">
                                <label for="sobrenome">Sobrenome</label>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="col s2"> </div>
                            <div class="input-field col s8">
                                <input id="login" name="login" type="text" class="validate">
                                <label for="login"> Login </label>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="col s2"> </div>
                            <div class="input-field col s8">
                                <input id="email" name="email" type="email" class="validate">
                                <label for="email"> Email </label>
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
                                <button class="btn waves-effect waves-light deep-orange darken-2" type="submit" name="action" style="width: 100%;"> Cadastrar </button>
                            </div>
                        </div>
                    </form>
                    
                    <div class="row">
                        <div style="text-align: center;">
                            Já é cadastrado? Faça o seu <a href="sass.html" class="deep-orange-text text-darken-2" >login</a>.
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    </body>
</html>
