package com.smdcommerce.DAO;

import com.smdcommerce.service.Categoria;
import com.smdcommerce.service.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Keller Maciel
 */
public class ProdutoVendaDAO {
    private CategoriaDAO categoriaDAO;
    
    public ProdutoVendaDAO() {
        this.categoriaDAO = new CategoriaDAO();
    }

    public Produto buscar(int id) throws Exception {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        StringBuilder sql = new StringBuilder("select pv.id, prod.descricao, prod.foto, pv.valor, pv.quantidade, prod.id_categoria ");
        sql.append("from produto_venda pv ");
        sql.append("join produto prod on pv.id_produto = prod.id ");
        sql.append("where pv.id = ? ");
        
        Produto produto = null;
        try {
            con = PostgreJDBC.getConnection();
            pstm = con.prepareStatement(sql.toString());
            pstm.setInt(1, id);
            
            rs = pstm.executeQuery();
            
            if(rs.next()){
                produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setUrl_image(rs.getString("foto"));
                produto.setValor(rs.getFloat("valor"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setCategoria(categoriaDAO.buscar(rs.getInt("id_categoria")));
            }
            
        }catch (SQLException ex){
            throw new SQLException(ex.getMessage());
        }finally{
            if(con != null)
                con.close();
            
            if(pstm != null)
                pstm.close();
        }

        return produto;
    }

    public List<Produto> buscarTodos(int idVenda) throws Exception {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        StringBuilder sql = new StringBuilder("select pv.id, prod.descricao, prod.foto, pv.valor, pv.quantidade, prod.id_categoria ");
        sql.append("from produto_venda pv ");
        sql.append("join produto prod on pv.id_produto = prod.id ");
        sql.append("where pv.id_venda = ? ");
        
        List<Produto> produtos = new ArrayList<>();        
        try{
            con = PostgreJDBC.getConnection();
            pstm = con.prepareStatement(sql.toString());
            pstm.setInt(1, idVenda);
            
            rs = pstm.executeQuery();
            
            while(rs.next()){
                Produto prod = new Produto();
                prod.setId(rs.getInt("id"));
                prod.setDescricao(rs.getString("descricao"));
                prod.setUrl_image(rs.getString("foto"));
                prod.setValor(rs.getFloat("valor"));
                prod.setQuantidade(rs.getInt("quantidade"));
                prod.setCategoria(categoriaDAO.buscar(rs.getInt("id_categoria")));
                
                produtos.add(prod);
            }
            
        }catch (SQLException ex){
            throw new SQLException(ex.getMessage());
        }finally{
            if(con != null)
                con.close();
            
            if(pstm != null)
                pstm.close();
        }

        return produtos;
    }
    
    public boolean inserir(Produto produtoNovo, int idVenda) throws Exception {
        Connection con = null;
        PreparedStatement pstm = null;
        boolean resultado = false;
        
        if(idVenda < 0) return false;
        
        StringBuilder sql = new StringBuilder("insert into produto_venda (valor, quantidade, id_produto, id_venda) ");
        sql.append("values (?, ?, ?, ?) ");
        
        try{
            con = PostgreJDBC.getConnection();
            pstm = con.prepareStatement(sql.toString());
            pstm.setFloat(1, produtoNovo.getValor());
            pstm.setInt(2, produtoNovo.getQuantidade());
            pstm.setInt(3, produtoNovo.getId());
            pstm.setInt(4, idVenda);
            
            resultado = pstm.executeUpdate() > 0;
        }catch (SQLException ex){
            throw new SQLException(ex.getMessage());
        }finally{
            if(con != null)
                con.close();
            
            if(pstm != null)
                pstm.close();
        }

        return resultado;
    }

    public boolean alterar(Produto produtoNovo) throws Exception {
        Connection con = null;
        PreparedStatement pstm = null;
        boolean resultado = false;
        
        StringBuilder sql = new StringBuilder("update produto_venda ");
        sql.append("set valor = ?, quantidade = ? where id = ? ");
        
        try{
            con = PostgreJDBC.getConnection();
            pstm = con.prepareStatement(sql.toString());
            pstm.setFloat(1, produtoNovo.getValor());
            pstm.setInt(2, produtoNovo.getQuantidade());
            pstm.setInt(3, produtoNovo.getId());
            
            resultado = pstm.executeUpdate() > 0;
        }catch (SQLException ex){
            throw new SQLException(ex.getMessage());
        }finally{
            if(con != null)
                con.close();
            
            if(pstm != null)
                pstm.close();
        }

        return resultado;
    }

    public boolean remover(int id) throws Exception {
        Connection con = null;
        PreparedStatement pstm = null;
        boolean resultado = false;
     
        try{
            con = PostgreJDBC.getConnection();
            pstm = con.prepareStatement("delete from produto_venda where id = ? ");
            pstm.setInt(1, id);
            
            resultado = pstm.executeUpdate() > 0;
        }catch (SQLException ex){
            throw new SQLException(ex.getMessage());
        }finally{
            if(con != null)
                con.close();
            
            if(pstm != null)
                pstm.close();
        }

        return resultado;
    } 
}
