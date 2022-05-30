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
public class LoginClienteServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean sucesso = false;
        String mensagem = null;
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = null;
        
        String destino = "index.jsp";

        try {
            String login = request.getParameter("login");
            
            if(usuarioDAO.existeLogin(login)){
                String senha = request.getParameter("senha");
                usuario = usuarioDAO.realizaLogin(login, senha);
                
                if(usuario != null){
                    sucesso = true;
                    HttpSession session = request.getSession(true);
                    session.setAttribute("usuario", usuario);
                    mensagem = "Login com sucesso!";
                    destino = "perfilCliente.jsp";
                } else {
                    sucesso = false;
                    mensagem = "Senha incorreta";
                }
            } else{
                sucesso = false;
                mensagem = "Não existe nome de usuario, deseja se cadastrar?";
            }

        } catch (Exception e){
            sucesso = false;
            mensagem = e.getMessage();
        }
        
        request.setAttribute("statusAcao", sucesso);
        request.setAttribute("mensagem", mensagem);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(destino);
        requestDispatcher.forward(request, response);
    }
}
