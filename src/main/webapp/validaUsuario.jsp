<%-- 
    Document   : validaUsuario
    Created on : 21 de jun de 2022, 12:12:43
    Author     : Keller Maciel
--%>
<%@page import="com.smdcommerce.service.Usuario"%>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null) {
        request.setAttribute("mensagem", "Voc� n�o tem uma sess�o v�lida de usu�rio do tipo cliente");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);
    }
%>