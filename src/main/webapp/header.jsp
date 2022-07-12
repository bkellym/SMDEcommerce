<%-- 
    Document   : menu
    Created on : 21 de jun de 2022, 11:01:38
    Author     : Keller Maciel
--%>
<%@page import="com.smdcommerce.service.Produto"%>
<%@page import="java.util.List"%>
<%@page import="com.smdcommerce.DAO.ProdutoCarrinhoDAO"%>
<%@page import="com.smdcommerce.service.Usuario"%>
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
                        <%if(usuario != null) {%>
                            <li> Olá, <%= usuario.getNome()%>! </li>
                            <li style="height: 64px;"> 
                                <div class="row" style='width: 90px; height: 64px;'>
                                    <a href="perfil.jsp"> <div class = 'col s2'> <i class="material-icons" style="font-size: 40px">person</i></div></a>
                                </div>
                            </li>
                            <%if(usuario.isEhAdmin()) {%>
                                <li style="height: 64px;">
                                    <div class="row" style='width: 90px; height: 64px;'>
                                        <a href="adminProdutos.jsp"><div class = 'col s2'><i class="material-icons" style="font-size: 40px">assignment</i></div></a>
                                    </div>
                                </li>
                                <li style="height: 64px;">
                                    <div class="row" style='width: 90px; height: 64px;'>
                                        <a href="adminCategorias.jsp"><div class = 'col s2'><i class="material-icons" style="font-size: 40px">ballot_outline</i></div></a>
                                    </div>
                                </li>
                            <% } %>
                        <% } else { %>       
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
                        <% } %>
                        <% if( usuario == null || !usuario.isEhAdmin()) { %>
                                <li style="height: 64px;">
                                    <div class="row" style='width: 90px; height: 64px;'>
                                        <div class = 'col s2'><a href="carrinho.jsp"><i class="material-icons" style="font-size: 40px">shopping_cart</i></a></div>
                                        <% 
                                            if(usuario != null) {
                                            ProdutoCarrinhoDAO produtoCarrinhoDAO = new ProdutoCarrinhoDAO();
                                            List<Produto> prodCarrinho = produtoCarrinhoDAO.buscarTodos(usuario.getId());
                                            int itens = 0;
                                            for(Produto prod: prodCarrinho){
                                                itens = itens + prod.getQuantidade();
                                            }
                                         %>
                                         <span class="new badge deep-orange darken-2" data-badge-caption=""> <%=itens%> </span>
                                        <% } %>
                                    </div>
                                </li>
                        <% } %>
                        <% if(usuario != null) { %>
                        <li style="height: 64px;">
                            <div class="row" style='width: 90px; height: 64px;'>
                                <a href="LogoutCliente"><div class = 'col s2'><i class="material-icons" style="font-size: 40px">exit_to_app</i></div></a>
                            </div>
                        </li>
                        <% } %>
                    </ul>
                </div>
            </div>
        </div>
      </nav>