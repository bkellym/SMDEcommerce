package com.smdcommerce.DAO;

import com.smdcommerce.service.Produto;
import com.smdcommerce.service.Venda;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Keller Maciel
 */
public class VendaDAO {
    
    private UsuarioDAO usuarioDAO;
    private ProdutoVendaDAO produtoDAO;
    
    public VendaDAO(){
        this.usuarioDAO = new UsuarioDAO();
        this.produtoDAO = new ProdutoVendaDAO();
    }
    
    private int buscaId(int idUsuario) throws Exception {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        int id = 0;
        try{
            con = PostgreJDBC.getConnection();
            pstm = con.prepareStatement("select max(id) as id from venda where id_usuario = ? ");
            pstm.setInt(1, idUsuario);
            
            rs = pstm.executeQuery();
            
            if(rs.next()){
                id = rs.getInt("id");
            }
            
        }catch (SQLException ex){
            throw new SQLException(ex.getMessage());
        }finally{
            if(con != null)
                con.close();
            
            if(pstm != null)
                pstm.close();
        }

        return id;
    }
    
    public List<Venda> buscarTodos(int idUsuario) throws Exception {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        List<Venda> vendas = new ArrayList<>();        
        try{
            con = PostgreJDBC.getConnection();
            pstm = con.prepareStatement("select * from venda where id_usuario = ? ");
            pstm.setInt(1, idUsuario);
            
            rs = pstm.executeQuery();
            
            while(rs.next()){
                Venda ven = new Venda();
                ven.setId(rs.getInt("id"));
                ven.setDataCompra(new Timestamp(rs.getDate("data_hora").getTime()));
                ven.setUsuario(usuarioDAO.busca(idUsuario));
                ven.setProdutos(produtoDAO.buscarTodos(rs.getInt("id")));
                vendas.add(ven);
            }
            
        }catch (SQLException ex){
            throw new SQLException(ex.getMessage());
        }finally{
            if(con != null)
                con.close();
            
            if(pstm != null)
                pstm.close();
        }

        return vendas;
    }
    
    public boolean inserir(Venda vendaNova) throws Exception {
        Connection con = null;
        PreparedStatement pstm = null;
        boolean resultado = false;
        
        StringBuilder sql = new StringBuilder("insert into venda (data_hora, id_usuario) ");
        sql.append("values (?, ?) ");
        
        try{
            con = PostgreJDBC.getConnection();
            pstm = con.prepareStatement(sql.toString());
            pstm.setDate(1, new Date(System.currentTimeMillis()));
            pstm.setInt(2, vendaNova.getUsuario().getId());
            
            resultado = pstm.executeUpdate() > 0;
            
            if(resultado){
                int idVenda = buscaId(vendaNova.getUsuario().getId());
                
                List<Produto> produtos = vendaNova.getProdutos();
            
                for (Produto p : produtos){
                    produtoDAO.inserir(p, idVenda);
                }
                
            }
            
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
    
    public boolean alterar(Venda vendaNova) throws Exception {
        Connection con = null;
        PreparedStatement pstm = null;
        boolean resultado = false;

        try{
            
            List<Produto> produtos = vendaNova.getProdutos();
            
            for (Produto p : produtos){
                produtoDAO.alterar(p);
            }

            con = PostgreJDBC.getConnection();
            pstm = con.prepareStatement("update venda set data_hora = ? where id = ?");
            pstm.setDate(1, new Date(System.currentTimeMillis()));
            pstm.setInt(2, vendaNova.getId());
            
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
            pstm = con.prepareStatement("delete from venda where id = ? ");
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
