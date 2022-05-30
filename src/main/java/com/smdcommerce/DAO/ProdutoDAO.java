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
public class ProdutoDAO {

    private CategoriaDAO categoriaDAO;
    
    public ProdutoDAO() {
        this.categoriaDAO = new CategoriaDAO();
    }
    
    public List<Produto> buscarEmEstoque(Categoria categoria)throws Exception {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        List<Produto> produtos = new ArrayList<>();        
        try{
            con = PostgreJDBC.getConnection();
            
            StringBuilder sql = new StringBuilder("select * from produto where quantidade > 0 ");
            if(categoria != null)
                sql.append("and id_categoria = ? ");

            pstm = con.prepareStatement(sql.toString());
                        
            if(categoria != null)
                pstm.setInt(1, categoria.getId());
            
            rs = pstm.executeQuery();
            
            while(rs.next()){
                Produto prod = new Produto();
                prod.setId(rs.getInt("id"));
                prod.setDescricao(rs.getString("descricao"));
                prod.setUrl_image(rs.getString("foto"));
                prod.setValor(rs.getFloat("valor"));
                prod.setQuantidade(rs.getInt("quantidade"));
                
                if(categoria != null){
                    prod.setCategoria(categoria);
                } else {
                    prod.setCategoria(categoriaDAO.buscar(rs.getInt("id_categoria")));
                }
                
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
    
    public Produto buscar(int id) throws Exception {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        Produto produto = null;
        try{
            con = PostgreJDBC.getConnection();
            pstm = con.prepareStatement("select * from produto where id = ? ");
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

    public List<Produto> buscarTodos(Categoria categoria)throws Exception {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        List<Produto> produtos = new ArrayList<>();        
        try{
            con = PostgreJDBC.getConnection();
            
            StringBuilder sql = new StringBuilder("select * from produto ");
            if(categoria != null)
                sql.append("where id_categoria = ? ");

            pstm = con.prepareStatement(sql.toString());
                        
            if(categoria != null)
                pstm.setInt(1, categoria.getId());
            
            rs = pstm.executeQuery();
            
            while(rs.next()){
                Produto prod = new Produto();
                prod.setId(rs.getInt("id"));
                prod.setDescricao(rs.getString("descricao"));
                prod.setUrl_image(rs.getString("foto"));
                prod.setValor(rs.getFloat("valor"));
                prod.setQuantidade(rs.getInt("quantidade"));
                
                if(categoria != null){
                    prod.setCategoria(categoria);
                } else {
                    prod.setCategoria(categoriaDAO.buscar(rs.getInt("id_categoria")));
                }
                
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
    
    public boolean inserir(Produto produtoNovo) throws Exception{
        Connection con = null;
        PreparedStatement pstm = null;
        boolean resultado = false;
        
        StringBuilder sql = new StringBuilder("insert into produto (descricao, foto, valor, quantidade, id_categoria) ");
        sql.append("values (?, ?, ?, ?, ?) ");
        
        try{
            con = PostgreJDBC.getConnection();
            pstm = con.prepareStatement(sql.toString());
            pstm.setString(1, produtoNovo.getDescricao());
            pstm.setString(2, produtoNovo.getUrl_image());
            pstm.setFloat(3, produtoNovo.getValor());
            pstm.setInt(4, produtoNovo.getQuantidade());
            pstm.setInt(5, produtoNovo.getCategoria().getId());
            
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
    
    public boolean alterar(Produto produtoNovo) throws Exception{
        Connection con = null;
        PreparedStatement pstm = null;
        boolean resultado = false;
        
        StringBuilder sql = new StringBuilder("update produto set descricao = ?, ");
        sql.append("foto = ?, valor = ?, quantidade = ?, id_categoria = ?  ");
        sql.append("where id = ? ");
        
        try{
            con = PostgreJDBC.getConnection();
            pstm = con.prepareStatement(sql.toString());
            pstm.setString(1, produtoNovo.getDescricao());
            pstm.setString(2, produtoNovo.getUrl_image());
            pstm.setFloat(3, produtoNovo.getValor());
            pstm.setInt(4, produtoNovo.getQuantidade());
            pstm.setInt(5, produtoNovo.getCategoria().getId());
            pstm.setInt(6, produtoNovo.getId());
            
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
    
    public boolean remover(int id) throws Exception{
        Connection con = null;
        PreparedStatement pstm = null;
        boolean resultado = false;
     
        try{
            con = PostgreJDBC.getConnection();
            pstm = con.prepareStatement("delete from produto where id = ? ");
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
