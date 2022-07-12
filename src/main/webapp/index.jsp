<%-- 
    Document   : index
    Created on : 30 de mai de 2022, 11:05:15
    Author     : Keller Maciel
--%>
<%@page import="com.smdcommerce.DAO.ProdutoCarrinhoDAO"%>
<%@page import="com.smdcommerce.service.Categoria"%>
<%@page import="com.smdcommerce.DAO.CategoriaDAO"%>
<%@page import="java.util.List"%>
<%@page import="com.smdcommerce.service.Produto"%>
<%@page import="com.smdcommerce.DAO.ProdutoDAO"%>
<%@page import="com.smdcommerce.service.Usuario"%>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    int categoriaId =  0;
    
    if(request.getParameter("categoriaId") != null){
        categoriaId = Integer.parseInt(request.getParameter("categoriaId"));
    }
    ProdutoDAO produtoDAO = new ProdutoDAO();
    List<Produto> produtos = produtoDAO.buscarEmEstoque(categoriaId); 
    CategoriaDAO categoriaDAO = new CategoriaDAO();
    List<Categoria> categorias = categoriaDAO.buscarTodas();
%>

<%@include file="header.jsp" %>
        
        <div class="container">
            <div class="row">
                <div class="col s12 white" style="min-height: 80vh;"> 
                    <div class="row">
                        <div class="col s8"> </div>
                        <div class="input-field col s4">
                            <select class="browser-default" id='categoria' name='categoria' 
                                    onchange="redireciona()" >
                              <option value="0" <%if(categoriaId == 0) { %> selected <%}%> >-- Selecione uma Categoria --</option>
                              <% for(Categoria cat : categorias){ %>
                                    <option value="<%=cat.getId()%>" <%if(categoriaId == cat.getId()) { %> selected <%}%> ><%=cat.getDescricao()%></option>
                                <% } %>
                            </select>
                        </div>
                    </div>
                    
                    <div class="row">
                    <% for(Produto prod : produtos){ %>
                        <div class="col s4 m4">
                          <div class="card">
                            <div class="card-image">
                              <img src="<%=prod.getUrl_image()%>" style="width: 100%; height: 160px;object-fit: cover;">
                              <span class="card-title teal lighten-1" style="font-size: 1em;"><%=prod.getDescricao()%></span>
                              <% if(usuario != null && !usuario.isEhAdmin()) { %>
                               <form action="AdicionarCarrinho" class="col s1" method="post"> 
                                    <input id="id_prod" name="id_prod" type="hidden" value='<%=prod.getId()%>'/> 
                                    <a class="btn-floating halfway-fab waves-effect waves-light red" href="javascript:;" onclick="parentNode.submit();">
                                        <i class="material-icons">add</i>
                                    </a>
                                </form> 
                              <% } %>
                            </div>
                            <div class="card-content">
                              <p>R$ <%=prod.getValor().toString()%></p>
                              <p><%=prod.getCategoria().getDescricao()%></p>
                            </div>
                          </div>
                        </div>
                    <% } %>
                  </div>
                </div>
            </div>
        </div>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
        
        <script type="text/javascript"> 
            
            function redireciona(){
                select =  document.getElementById("categoria");
                categId = select.options[select.selectedIndex].value;
                window.location.href = '?categoriaId='+categId;
            }
            
            M.toast({
                html: <%= request.getAttribute("mensagem") %>, 
                displayLength: 4000
            });
        </script> 
    </body>
</html>
