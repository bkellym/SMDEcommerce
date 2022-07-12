<%-- 
    Document   : perfilCliente
    Created on : 30 de mai de 2022, 16:55:46
    Author     : Keller Maciel
--%>
<%@include file="validaUsuario.jsp" %>
<%@include file="header.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col s12 white" style="height: 80vh; padding: 32px;">
                    
                    <h3 class="deep-orange-text text-darken-2" style="text-align: center; font-weight: bold;"> Edite seu Perfil </h3>
                    
                    <form action="EditarUsuario" class="col s12" method="post">
                        <div class="row">
                            <div class="col s2"> </div>
                            <div class="input-field col s8">
                                <input id="nome" name="nome" type="text" class="validate" value="<%=usuario.getNome()%>">
                                <label for="nome">Nome</label>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="col s2"> </div>
                            <div class="input-field col s8">
                                <input id="exib_login" name="exib_login" type="text" class="validate" disabled value="<%=usuario.getLogin()%>">
                                <label for="exib_login"> Login </label>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="col s2"> </div>
                            <div class="input-field col s8">
                                <input id="email" name="email" type="email" class="validate" value="<%=usuario.getEmail()%>">
                                <label for="email"> Email </label>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="col s2"> </div>
                            <div class="input-field col s8">
                                <input id="senha" name="senha" type="password" class="validate" value="<%=usuario.getSenha()%>">
                                <label for="senha">Senha</label>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="col s2"> </div>
                            <div class="input-field col s4">
                                <input id="login" name="login" type="hidden" value='<%=usuario.getLogin()%>'/> 
                                <input id="isAdmin" name="isAdmin" type="hidden" value='<%=usuario.isEhAdmin()%>'/> 
                                <input id="user_id" name="user_id" type="hidden" value='<%=usuario.getId()%>'/> 
                                <button class="btn waves-effect waves-light deep-orange darken-2" type="submit" name="action" style="width: 100%;"> Editar </button>
                            </div>
                            </form>
                            <div class="input-field col s4">
                                <form action="DeletarUsuario" class="col s12" method="post"> 
                                    <input id="usuarioId" name="usuarioId" type="hidden" value='<%=usuario.getId()%>'/> 
                                    <button class="btn waves-effect waves-light red darken-2" type="submit" name="action" style="width: 100%;"> Excluir perfil </button>
                                </form>
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

