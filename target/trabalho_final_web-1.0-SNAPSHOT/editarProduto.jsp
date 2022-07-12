<%-- 
    Document   : perfilCliente
    Created on : 30 de mai de 2022, 16:55:46
    Author     : Keller Maciel
--%>

<%@page import="com.smdcommerce.service.Produto"%>
<%@page import="com.smdcommerce.DAO.ProdutoDAO"%>
<%@page import="com.smdcommerce.service.Categoria"%>
<%@page import="com.smdcommerce.DAO.CategoriaDAO"%>
<%@page import="java.util.List"%>
<%@include file="validaAdmin.jsp" %>
<%@include file="header.jsp" %>

<%
    int produtoId = Integer.parseInt(request.getParameter("produtoId"));
    ProdutoDAO produtoDAO = new ProdutoDAO();
    Produto produto = produtoDAO.buscar(produtoId);

%>

        <div class="container">
            <div class="row">
                <div class="col s12 white" style="min-height: 80vh; padding: 32px;">
                    
                    <form action="EditarProduto" class="col s12" method="post">                        
                        <div class="row">
                            <div class="col s2"> </div>
                            <div class="input-field col s8">
                                <input id="descricao" name="descricao" type="text" class="validate" value="<%=produto.getDescricao()%>">
                                <label for="descricao"> Descrição </label>
                            </div>
                        </div>
                            
                        <div class="row">
                            <div class="col s2"> </div>
                            <div class="input-field col s8">
                                <input id="imagem" name="imagem" type="text" class="validate" value="<%=produto.getUrl_image()%>">
                                <label for="imagem"> Url da Imagem </label>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="col s2"> </div>
                            <div class="input-field col s8">
                                <input id="valor" name="valor" type="text" class="validate" value="<%=produto.getValor()%>">
                                <label for="valor"> Valor </label>
                            </div>
                        </div>
                            
                        <div class="row">
                            <div class="col s2"> </div>
                            <div class="input-field col s8">
                                <input id="quantidade" name="quantidade" type="text" class="validate" value="<%=produto.getQuantidade()%>">
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
                                        <option <%if(produto.getCategoria().getId() == cat.getId()) { %> selected <% } %>  value="<%=cat.getId()%>"><%=cat.getDescricao()%></option>
                                    <% } %>
                                </select>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="col s4"> </div>
                            <div class="input-field col s4">
                                <input id="categ_id" name="prod_id" type="hidden" value='<%=produto.getId()%>'/> 
                                <button class="btn waves-effect waves-light deep-orange darken-2" type="submit" name="action" style="width: 100%;"> Editar </button>
                            </div>
                        </div>
                    </form>             
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
