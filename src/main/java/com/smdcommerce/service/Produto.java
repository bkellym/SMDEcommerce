package com.smdcommerce.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Keller Maciel
 */
public class Produto {
    private int id;
    private String descricao;
    private String url_image;
    private BigDecimal valor;
    private int quantidade;
    private Categoria categoria;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }

    public BigDecimal getValor() {
        return valor.setScale(2, RoundingMode.DOWN );
    }

    public void setValor(float valor) {
        this.valor = new BigDecimal(valor);
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
