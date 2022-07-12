/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.smdcommerce.controller;

import com.smdcommerce.DAO.CategoriaDAO;
import com.smdcommerce.DAO.ProdutoCarrinhoDAO;
import com.smdcommerce.DAO.ProdutoDAO;
import com.smdcommerce.service.Categoria;
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
public class AdicionarCarrinhoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProdutoCarrinhoDAO produtoCarrinhoDAO = new ProdutoCarrinhoDAO();
        
        boolean sucesso = false;
        String mensagem = null;
        String caminho = "index.jsp";

        try {
            HttpSession session = request.getSession();
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            int produtoId = Integer.parseInt(request.getParameter("id_prod"));   
            if(request.getParameter("retorno") != null)
                caminho = request.getParameter("retorno");  

            if (usuario != null) {
                produtoCarrinhoDAO.adicionar(produtoId, usuario.getId());
                sucesso = true;
                mensagem = "Adicionado ao Carrinho";
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
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(caminho);
            requestDispatcher.forward(request, response);
        }
    }
}
