<%-- 
    Document   : perfilCliente
    Created on : 30 de mai de 2022, 16:55:46
    Author     : Keller Maciel
--%>

<%@page import="com.smdcommerce.service.Categoria"%>
<%@page import="com.smdcommerce.DAO.CategoriaDAO"%>
<%@page import="com.smdcommerce.service.Produto"%>
<%@page import="java.util.List"%>
<%@page import="com.smdcommerce.DAO.ProdutoDAO"%>

<%@include file="validaAdmin.jsp" %>
<%@include file="header.jsp" %>
        
        <div class="container">
            <div class="row">
                <div class="col s12 white" style="padding: 32px;">
                    
                    <form action="CadastrarProduto" class="col s12" method="post">                        
                        <div class="row">
                            <div class="col s2"> </div>
                            <div class="input-field col s8">
                                <input id="descricao" name="descricao" type="text" class="validate">
                                <label for="descricao"> Descrição </label>
                            </div>
                        </div>
                            
                        <div class="row">
                            <div class="col s2"> </div>
                            <div class="input-field col s8">
                                <input id="imagem" name="imagem" type="text" class="validate">
                                <label for="imagem"> Url da Imagem </label>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="col s2"> </div>
                            <div class="input-field col s8">
                                <input id="valor" name="valor" type="text" class="validate">
                                <label for="valor"> Valor </label>
                            </div>
                        </div>
                            
                        <div class="row">
                            <div class="col s2"> </div>
                            <div class="input-field col s8">
                                <input id="quantidade" name="quantidade" type="text" class="validate">
                                <label for="quantidade"> Quantidade </label>
                            </div>
                        </div>
                            
                            <%  
                                CategoriaDAO categoriaDAO = new CategoriaDAO();
                                List<Categoria> categorias = categoriaDAO.buscarTodas(); 
                            %>
                            
                        <div class="row">
                            <div class="col s2"> </div>
                            <div class="input-field col s8">
                                
                                <select class="browser-default" id='categoria' name='categoria'>
                                  <option value="" disabled selected>-- Selecione uma Categoria --</option>
                                  <% for(Categoria cat : categorias){ %>
                                        <option value="<%=cat.getId()%>"><%=cat.getDescricao()%></option>

                                    <% } %>
                                </select>
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
                        ProdutoDAO produtoDAO = new ProdutoDAO();
                        List<Produto> produtos = produtoDAO.buscarTodos(null); 
                    %>
                    
                    <ul class="collection">   
                    
                    <% for(Produto prod : produtos){ %>
                        
                    <li class="collection-item avatar"> 
                        <img src="<%=prod.getUrl_image()%>" alt="" class="circle">
                        <span class="title"> <%=prod.getDescricao()%> </span>
                        <p> Categoria: <%=prod.getCategoria().getDescricao()%> <br>
                            Valor: R$<%=prod.getValor().toString()%>  |  Em estoque: <%=prod.getQuantidade()%>
                        </p>
                        <form action="editarProduto.jsp?produtoId=<%=prod.getId()%>" class="col s1" method="post"> 
                            <a href="javascript:;" onclick="parentNode.submit();" class="secondary-content" style="margin-right: 32px;">
                                <i class="material-icons">create</i>
                            </a> 
                        </form> 
                        <form action="DeletarProduto" class="col s1" method="post"> 
                            <input id="id_prod" name="id_prod" type="hidden" value='<%=prod.getId()%>'/> 
                            <a href="javascript:;" onclick="parentNode.submit();" class="secondary-content">
                                <i class="material-icons">delete</i>
                            </a> 
                        </form> 
                    </li>
                    
                    <% } %>
                    
                    </ul>
                    
                </div>
            </div>
        </div>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
        
        <% if (request.getAttribute("mensagem") != null) { %>
        
        <script type="text/javascript"> 
            document.addEventListener('DOMContentLoaded', function() {
                var elems = document.querySelectorAll('select');
                var instances = M.FormSelect.init(elems, options);
            });
            
            M.toast({
                html: <%= request.getAttribute("mensagem") %>, 
                displayLength: 4000
            });
            
            
        </script> 
        
        <% } %>
    </body>
</html>
