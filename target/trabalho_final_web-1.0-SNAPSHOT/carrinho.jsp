<%-- 
    Document   : perfilCliente
    Created on : 30 de mai de 2022, 16:55:46
    Author     : Keller Maciel
--%>

<%@page import="java.math.BigDecimal"%>
<%@page import="com.smdcommerce.DAO.ProdutoCarrinhoDAO"%>
<%@page import="com.smdcommerce.service.Categoria"%>
<%@page import="com.smdcommerce.DAO.CategoriaDAO"%>
<%@page import="com.smdcommerce.service.Produto"%>
<%@page import="java.util.List"%>
<%@page import="com.smdcommerce.DAO.ProdutoDAO"%>

<%@include file="validaUsuario.jsp" %>
<%@include file="header.jsp" %>

<%  
    ProdutoCarrinhoDAO produtoDAO = new ProdutoCarrinhoDAO();
    List<Produto> produtos = produtoDAO.buscarTodos(usuario.getId()); 
%>
        
        <div class="container">
            <div class="row">
                <div class="col s12 white" style="padding: 32px; min-height: 80vh">
                    <ul class="collection">   
                    
                    <% for(Produto prod : produtos){ %>
                        
                    <li class="collection-item avatar"> 
                        <div class="row">
                            <div class="col s9"> 
                                <img src="<%=prod.getUrl_image()%>" alt="" class="circle">
                                <span class="title"> <%=prod.getDescricao()%> </span>
                                <p> Valor: R$<%=prod.getValor().toString()%> </p>
                            </div>
                            <div class="col s2"> 
                                <p> <%=prod.getQuantidade()%> <%if(prod.getQuantidade()> 1) { %> unidades <%} else { %> unidade <% } %>  <br>
                                    Total: R$ <%=prod.getValor().multiply( new BigDecimal(prod.getQuantidade())).toString()%>
                                </p>
                            </div>
                            <form action="AdicionarCarrinho" class="col s1" method="post"> 
                                <input name="id_prod" type="hidden" value='<%=prod.getId()%>'/> 
                                <input name="retorno" type="hidden" value="carrinho.jsp"/> 
                                <a href="javascript:;" onclick="parentNode.submit();" class="secondary-content" style="margin-right: 32px;">
                                    <i class="material-icons">add</i>
                                </a> 
                            </form> 
                            <form action="RemoverCarrinho" class="col s1" method="post"> 
                                <input name="id_prod" type="hidden" value='<%=prod.getId()%>'/> 
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
