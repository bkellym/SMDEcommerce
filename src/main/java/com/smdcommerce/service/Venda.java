package com.smdcommerce.service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Keller Maciel
 */
public class Venda {
    private int id;
    private Usuario usuario;
    private Timestamp dataCompra;
    private List<Produto> produtos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Timestamp getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Timestamp dataCompra) {
        this.dataCompra = dataCompra;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
