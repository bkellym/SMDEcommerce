<%-- 
    Document   : perfilCliente
    Created on : 30 de mai de 2022, 16:55:46
    Author     : Keller Maciel
--%>

<%@page import="com.smdcommerce.service.Categoria"%>
<%@page import="com.smdcommerce.DAO.CategoriaDAO"%>
<%@page import="java.util.List"%>
<%@include file="validaAdmin.jsp" %>
<%@include file="header.jsp" %>

        <div class="container">
            <div class="row">
                <div class="col s12 white" style="min-height: 80vh; padding: 32px;">
                    
                    <form action="CadastrarCategoria" class="col s12" method="post">                        
                        <div class="row">
                            <div class="col s2"> </div>
                            <div class="input-field col s8">
                                <input id="descricao" name="descricao" type="text" class="validate">
                                <label for="descricao"> Descrição </label>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="col s4"> </div>
                            <div class="input-field col s4">
                                <button class="btn waves-effect waves-light deep-orange darken-2" type="submit" name="action" style="width: 100%;"> Cadastrar </button>
                            </div>
                        </div>
                    </form>
                    
                    <%  
                        CategoriaDAO categoriaDAO = new CategoriaDAO();
                        List<Categoria> categorias = categoriaDAO.buscarTodas(); 
                    %>
                    
                    <ul class="collection">   
                    
                    <% for(Categoria cat : categorias){ %>
                        
                    <li class="collection-item"> 
                        <div class='row'> 
                            <div class="col s10"> <%=cat.getDescricao()%> </div> 
                            <form action="editarCategoria.jsp?categoriaId=<%=cat.getId()%>" class="col s1" method="post"> 
                                <a href="javascript:;" onclick="parentNode.submit();" class="secondary-content">
                                    <i class="material-icons">create</i>
                                </a> 
                            </form> 
                            <form action="DeletarCategoria" class="col s1" method="post"> 
                                <input id="id_categ" name="id_categ" type="hidden" value='<%=cat.getId()%>'/> 
                                <a href="javascript:;" onclick="parentNode.submit();" class="secondary-content">
                                    <i class="material-icons">delete</i>
                                </a> 
                            </form> 
                        </div>
                    </li>
                    
                    <% } %>
                    
                    </ul>
                    
                    
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
