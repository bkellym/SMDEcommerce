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

<%
    int categoriaId = Integer.parseInt(request.getParameter("categoriaId"));
    CategoriaDAO categoriaDAO = new CategoriaDAO();
    Categoria categoria = categoriaDAO.buscar(categoriaId);

%>

        <div class="container">
            <div class="row">
                <div class="col s12 white" style="min-height: 80vh; padding: 32px;">
                    
                    <form action="EditarCategoria" class="col s12" method="post">                        
                        <div class="row">
                            <div class="col s2"> </div>
                            <div class="input-field col s8">
                                <input id="descricao" name="descricao" type="text" class="validate" value="<%=categoria.getDescricao()%>">
                                <label for="descricao"> Descrição </label>
                            </div>
                        </div>
                        
                        <div class="row">
                            <div class="col s4"> </div>
                            <div class="input-field col s4">
                                <input id="categ_id" name="categ_id" type="hidden" value='<%=categoria.getId()%>'/> 
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
