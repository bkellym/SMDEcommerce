package com.smdcommerce.DAO;

import com.smdcommerce.service.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Keller Maciel
 */
public class UsuarioDAO {
    
    public boolean existeLogin(String login) throws Exception {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        boolean result = false;
        try{
            con = PostgreJDBC.getConnection();
            pstm = con.prepareStatement("select 1 from usuario where login = ? ");
            pstm.setString(1, login);
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
    
    public Usuario realizaLogin(String login, String senha) throws Exception{
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        Usuario result = new Usuario();
        try{
            con = PostgreJDBC.getConnection();
            pstm = con.prepareStatement("select * from usuario where login = ? and senha = ?");
            pstm.setString(1, login);
            pstm.setString(2, senha);
            rs = pstm.executeQuery();
            
            if(rs.next()){
                result.setId(rs.getInt("id"));
                result.setNome(rs.getString("nome"));
                result.setLogin(rs.getString("login"));
                result.setEmail(rs.getString("email"));
                result.setEndereco(rs.getString("endereco"));
                result.setEhAdmin(rs.getInt("admins") == 1);
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
    
    public Usuario busca(int id) throws Exception {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        Usuario usuario = new Usuario();
        try{
            con = PostgreJDBC.getConnection();
            pstm = con.prepareStatement("select * from usuario where id = ? ");
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            
            if(rs.next()){
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setLogin(rs.getString("login"));
                usuario.setEmail(rs.getString("email"));
                usuario.setEndereco(rs.getString("endereco"));
                usuario.setEhAdmin(rs.getInt("admins") == 1);
            }
            
        }catch (SQLException ex){
            throw new SQLException(ex.getMessage());
        }finally{
            if(con != null)
                con.close();
            
            if(pstm != null)
                pstm.close();
        }

        return usuario;
    }
    
    public List<Usuario> obterUsuarios() throws Exception {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        
        List<Usuario> usuarios = new ArrayList<>();
        try{
            con = PostgreJDBC.getConnection();
            stm = con.createStatement();
            rs = stm.executeQuery("select * from usuario");
            
            while(rs.next()){
                Usuario usu = new Usuario();
                usu.setId(rs.getInt("id"));
                usu.setNome(rs.getString("nome"));
                usu.setLogin(rs.getString("login"));
                usu.setEmail(rs.getString("email"));
                usu.setEndereco(rs.getString("endereco"));
                usu.setEhAdmin(rs.getInt("admins") == 1);
                
                usuarios.add(usu);
            }
            
        }catch (SQLException ex){
            throw new SQLException(ex.getMessage());
        }finally{
            if(con != null)
                con.close();
            
            if(stm != null)
                stm.close();
        }

        return usuarios;
    }
    
    public boolean inserir(Usuario usuarioNovo) throws Exception {
        Connection con = null;
        PreparedStatement pstm = null;
        boolean resultado = false;
        
        StringBuilder sql = new StringBuilder("insert into usuario (nome, login, senha, email, endereco, admins) ");
        sql.append("values (?, ?, ?, ?, ?, ?) ");
        
        try{
            con = PostgreJDBC.getConnection();
            pstm = con.prepareStatement(sql.toString());
            pstm.setString(1, usuarioNovo.getNome());
            pstm.setString(2, usuarioNovo.getLogin());
            pstm.setString(3, usuarioNovo.getSenha());
            pstm.setString(4, usuarioNovo.getEmail());
            pstm.setString(5, usuarioNovo.getEndereco());
            pstm.setInt(6, usuarioNovo.isEhAdmin() ? 1 : 0);
            
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
    
    public boolean alterar(Usuario usuarioNovo) throws Exception {
        Connection con = null;
        PreparedStatement pstm = null;
        boolean resultado = false;
        
        StringBuilder sql = new StringBuilder("update usuario set nome = ?, ");
        sql.append("senha = ?, email = ?, endereco = ?, admins = ? ");
        sql.append("where id = ? ");
        
        try{
            con = PostgreJDBC.getConnection();
            pstm = con.prepareStatement(sql.toString());
            pstm.setString(1, usuarioNovo.getNome());
            pstm.setString(2, usuarioNovo.getSenha());
            pstm.setString(3, usuarioNovo.getEmail());
            pstm.setString(4, usuarioNovo.getEndereco());
            pstm.setInt(5, usuarioNovo.isEhAdmin() ? 1 : 0);
            pstm.setInt(6, usuarioNovo.getId());
            
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
    
    public boolean excluir(int id) throws Exception {
        Connection con = null;
        PreparedStatement pstm = null;
        boolean resultado = false;
     
        try{
            con = PostgreJDBC.getConnection();
            pstm = con.prepareStatement("delete from usuario where id = ? ");
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
