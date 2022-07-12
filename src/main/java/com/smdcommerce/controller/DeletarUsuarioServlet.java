package com.smdcommerce.controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

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
public class DeletarUsuarioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        boolean sucesso = false;
        String mensagem = null;

        try {
            int usuarioId = Integer.parseInt(request.getParameter("usuarioId"));
            
            HttpSession session = request.getSession();
            Usuario usuarioSessao = (Usuario) session.getAttribute("usuario");
            if(usuarioSessao.getId() == usuarioId){
                usuarioDAO.excluir(usuarioId);
                session.invalidate();
                sucesso = true;
                mensagem = "Cliente deletado com sucesso";
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
