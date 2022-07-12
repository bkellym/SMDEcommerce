/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.smdcommerce.controller;

import com.smdcommerce.DAO.CategoriaDAO;
import com.smdcommerce.service.Produto;
import com.smdcommerce.DAO.ProdutoDAO;
import com.smdcommerce.service.Categoria;
import com.smdcommerce.service.Usuario;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Keller Maciel
 */
public class CadastrarProdutoServlet extends HttpServlet {

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
                produto.setDescricao(request.getParameter("descricao"));
                produto.setUrl_image(request.getParameter("imagem"));
                produto.setValor(Float.parseFloat(request.getParameter("valor")));
                produto.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));

                CategoriaDAO categoriaDAO = new CategoriaDAO();
                Categoria categoria = categoriaDAO.buscar(Integer.parseInt(request.getParameter("categoria")));
                produto.setCategoria(categoria);
                produtoDAO.inserir(produto);
                
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
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("adminProdutos.jsp");
            requestDispatcher.forward(request, response);
        }
    }

}
