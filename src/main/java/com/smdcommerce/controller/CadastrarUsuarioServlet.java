package com.smdcommerce.controller;

import com.smdcommerce.DAO.UsuarioDAO;
import com.smdcommerce.service.Usuario;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Keller Maciel
 */
public class CadastrarUsuarioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = new Usuario();
        
        boolean sucesso = false;
        String mensagem = null;

        try {
            String nome = request.getParameter("nome").trim();
            String sobrenome = request.getParameter("sobrenome").trim();
            usuario.setNome(nome + " " + sobrenome);
            usuario.setEmail(request.getParameter("email"));
            usuario.setLogin(request.getParameter("login"));
            usuario.setSenha(request.getParameter("senha"));
            usuario.setEhAdmin(false);
            
            if(!usuarioDAO.existeLogin(usuario.getLogin())){
                usuarioDAO.inserir(usuario);
                sucesso = true;
                mensagem = "Cliente inserido com sucesso";
            } else {
                sucesso = false;
                mensagem = "Login já cadastra, favor selecionar outro.";
            }

        } catch (Exception ex) {
            sucesso = false;
            mensagem = ex.getMessage();
        }finally{
            request.setAttribute("statusAcao", sucesso);
            request.setAttribute("mensagem", mensagem);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
