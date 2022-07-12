/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.smdcommerce.controller;

import com.smdcommerce.DAO.ProdutoDAO;
import com.smdcommerce.service.Produto;
import com.smdcommerce.service.Usuario;
import java.io.IOException;
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
public class DeletarProdutoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Produto produto = new Produto();
        
        boolean sucesso = false;
        String mensagem = null;

        try {
            HttpSession session = request.getSession(true);
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            if (usuario != null && usuario.isEhAdmin()) {
                produtoDAO.remover(Integer.parseInt(request.getParameter("id_prod")));
 
                sucesso = true;
                mensagem = "Produto removido com sucesso";
            }else {
                sucesso = false;
                mensagem = "Você não tem permissão para essa ação";
            }

        } catch (Exception ex) {
            sucesso = false;
            mensagem = ex.getMessage();
        }finally{
            request.setAttribute("statusAcao", sucesso);
            request.setAttribute("mensagem", mensagem);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("adminProdutos.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
