package com.smdcommerce.DAO;

import com.smdcommerce.service.Categoria;
import com.smdcommerce.service.Produto;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Keller Maciel
 */
public class ProdutoCarrinhoDAO {
    private CategoriaDAO categoriaDAO;
    private ProdutoDAO produtoDAO;
    
    public ProdutoCarrinhoDAO() {
        this.categoriaDAO = new CategoriaDAO();
        this.produtoDAO = new ProdutoDAO();
    }
    
    public boolean produtoAdicionado(int idUsuario, int idProduto) throws Exception{
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        boolean result = false;
        try{
            con = PostgreJDBC.getConnection();
            pstm = con.prepareStatement("select * from produto_carrinho where id_usuario = ? and id_produto = ? ");
            pstm.setInt(1, idUsuario);
            pstm.setInt(2, idProduto);
            rs = pstm.executeQuery();
            
            if(rs.next()){
                result = true;
            }
            
        }catch (SQLException ex){
            throw new SQLException(ex.getMessage());
        }finally{
            if(con != null)
                con.close();
            
            if(pstm != null)
                pstm.close();
        }

        return result;
    }
    
    public Produto buscar(int id) throws Exception {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        StringBuilder sql = new StringBuilder("select pc.id, prod.descricao, prod.foto, prod.valor, pc.quantidade, prod.id_categoria ");
        sql.append("from produto_carrinho pc ");
        sql.append("join produto prod on pc.id_produto = prod.id ");
        sql.append("where pc.id = ? ");
        
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

    public Produto buscarAdicionado(int idProduto, int idUsuario) throws Exception {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        StringBuilder sql = new StringBuilder("select * from produto_carrinho ");
        sql.append("where id_produto = ? and id_usuario = ? ");
        
        Produto produto = null;
        try {
            con = PostgreJDBC.getConnection();
            pstm = con.prepareStatement(sql.toString());
            pstm.setInt(1, idProduto);
            pstm.setInt(2, idUsuario);
            
            rs = pstm.executeQuery();
            
            if(rs.next()){
                produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setQuantidade(rs.getInt("quantidade"));
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

    public List<Produto> buscarTodos(int idUsuario) throws Exception {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        StringBuilder sql = new StringBuilder("select prod.id, prod.descricao, prod.foto, prod.valor, pc.quantidade, prod.id_categoria ");
        sql.append("from produto_carrinho pc ");
        sql.append("join produto prod on pc.id_produto = prod.id ");
        sql.append("where pc.id_usuario = ? ");
        
        List<Produto> produtos = new ArrayList<>();        
        try{
            con = PostgreJDBC.getConnection();
            pstm = con.prepareStatement(sql.toString());
            pstm.setInt(1, idUsuario);
            
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
    
    public boolean adicionar(int idProduto, int idUsuario) throws Exception {
        boolean resultado = false;
        
        if(this.produtoAdicionado(idUsuario, idProduto)){
            Produto produto = this.buscarAdicionado(idProduto, idUsuario);
            produto.setQuantidade(produto.getQuantidade() + 1);
            resultado = this.alterar(produto, idUsuario);
        }
        else {
            resultado = this.inserir(idProduto, idUsuario);
        }

        return resultado;
    }

    public boolean inserir(int produto, int idUsuario) throws Exception {
        Connection con = null;
        PreparedStatement pstm = null;
        boolean resultado = false;
        
        StringBuilder sql = new StringBuilder("insert into produto_carrinho (id_usuario, id_produto, quantidade)  ");
        sql.append("values (?, ?, ?) ");
        
        try{
            con = PostgreJDBC.getConnection();
            pstm = con.prepareStatement(sql.toString());
            pstm.setInt(1, idUsuario);
            pstm.setInt(2, produto);
            pstm.setInt(3, 1);
            
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
    
    public boolean alterar(Produto produtoNovo, int idUsuario) throws Exception {
        Connection con = null;
        PreparedStatement pstm = null;
        boolean resultado = false;
        
        StringBuilder sql = new StringBuilder("update produto_carrinho ");
        sql.append("set quantidade = ? where id = ? ");
        
        try{
            con = PostgreJDBC.getConnection();
            pstm = con.prepareStatement(sql.toString());
            pstm.setInt(1, produtoNovo.getQuantidade());
            pstm.setInt(2, produtoNovo.getId());
            
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

    public boolean remover(int idProduto, int idUsuario) throws Exception {
        Connection con = null;
        PreparedStatement pstm = null;
        boolean resultado = false;
        
        Produto produto = this.buscarAdicionado(idProduto, idUsuario);
     
        try{
            con = PostgreJDBC.getConnection();
            pstm = con.prepareStatement("delete from produto_carrinho where id = ? ");
            pstm.setInt(1, produto.getId());
            
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
