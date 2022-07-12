<%-- 
    Document   : validarAdmin
    Created on : 21 de jun de 2022, 12:12:52
    Author     : Keller Maciel
--%>
<%@page import="com.smdcommerce.service.Usuario"%>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null || !usuario.isEhAdmin()) {
        request.setAttribute("mensagem", "Você não tem uma sessão válida de usuário do tipo cliente");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);
    }
%>