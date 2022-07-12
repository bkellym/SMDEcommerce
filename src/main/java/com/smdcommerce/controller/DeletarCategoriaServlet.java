/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.smdcommerce.controller;

import com.smdcommerce.DAO.CategoriaDAO;
import com.smdcommerce.service.Categoria;
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
public class DeletarCategoriaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        Categoria categoria = new Categoria();
        
        boolean sucesso = false;
        String mensagem = null;

        try {
            HttpSession session = request.getSession(true);
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            if (usuario != null && usuario.isEhAdmin()) {
                categoriaDAO.remover(Integer.parseInt(request.getParameter("id_categ")));
 
                sucesso = true;
                mensagem = "Categoria removida com sucesso";
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
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("adminCategorias.jsp");
            requestDispatcher.forward(request, response);
        }
    }

}
