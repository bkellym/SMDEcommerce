/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.smdcommerce.controller;

import com.smdcommerce.DAO.UsuarioDAO;
import com.smdcommerce.service.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Keller Maciel
 */
public class EditarUsuarioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = new Usuario();
        
        boolean sucesso = false;
        String mensagem = null;

        try {
            usuario.setId(Integer.parseInt(request.getParameter("user_id")));
            usuario.setNome(request.getParameter("nome"));
            usuario.setEmail(request.getParameter("email"));
            usuario.setLogin(request.getParameter("login"));
            usuario.setSenha(request.getParameter("senha"));
            
            HttpSession session = request.getSession();
            Usuario usuarioSessao = (Usuario) session.getAttribute("usuario");
            if(usuarioSessao.getId() == usuario.getId()){
                usuario.setEhAdmin(usuarioSessao.isEhAdmin());
                usuarioDAO.alterar(usuario);
                session.setAttribute("usuario", usuario);
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
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("perfil.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
