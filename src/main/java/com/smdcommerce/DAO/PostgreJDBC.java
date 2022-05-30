package com.smdcommerce.DAO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Keller Maciel
 */
public class PostgreJDBC {

    /**
     *
     * @return Conexção com o Banco de Dados
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException{
        Connection con = null;
        
        try{
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/smdcommerce", "postgres", "ufc123");
            
        }catch (ClassNotFoundException ex){
            throw new SQLException(ex.getMessage());
        }

        return con;
    }
}
