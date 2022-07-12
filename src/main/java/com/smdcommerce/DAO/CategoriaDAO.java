package com.smdcommerce.DAO;

import com.smdcommerce.service.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Keller Maciel
 */
public class CategoriaDAO {
    
    public Categoria buscar(int id) throws Exception {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        Categoria categoria = null;
        try{
            con = PostgreJDBC.getConnection();
            pstm = con.prepareStatement("select * from categoria where id = ? ");
            pstm.setInt(1, id);
            
            rs = pstm.executeQuery();
            
            if(rs.next()){
                categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setDescricao(rs.getString("descricao"));
            }
            
        }catch (SQLException ex){
            throw new SQLException(ex.getMessage());
        }finally{
            if(con != null)
                con.close();
            
            if(pstm != null)
                pstm.close();
        }

        return categoria;
    }
    
    public List<Categoria> buscarTodas() throws Exception {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        
        List<Categoria> categorias = new ArrayList<>();
        try{
            con = PostgreJDBC.getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery("select * from categoria order by descricao ");
            
            while(rs.next()){
                Categoria cat = new Categoria();
                cat.setId(rs.getInt("id"));
                cat.setDescricao(rs.getString("descricao"));
                
                categorias.add(cat);
            }
            
        }catch (SQLException ex){
            throw new SQLException(ex.getMessage());
        }finally{
            if(con != null)
                con.close();
            
            if(stm != null)
                stm.close();
        }

        return categorias;
    }
    
    public boolean inserir(Categoria categoriaNova) throws Exception {
        Connection con = null;
        PreparedStatement pstm = null;
        boolean resultado = false;
        
        StringBuilder sql = new StringBuilder("insert into categoria (descricao) ");
        sql.append("values (?) ");
        
        try{
            con = PostgreJDBC.getConnection();
            pstm = con.prepareStatement(sql.toString());
            pstm.setString(1, categoriaNova.getDescricao());
            
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
    
    public boolean alterar(Categoria categoriaNova) throws Exception {
        Connection con = null;
        PreparedStatement pstm = null;
        boolean resultado = false;
        
        StringBuilder sql = new StringBuilder("update categoria set descricao = ? ");
        sql.append("where id = ? ");
        
        try{
            con = PostgreJDBC.getConnection();
            pstm = con.prepareStatement(sql.toString());
            pstm.setString(1, categoriaNova.getDescricao());
            pstm.setInt(2, categoriaNova.getId());
            
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
            pstm = con.prepareStatement("delete from categoria where id = ? ");
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