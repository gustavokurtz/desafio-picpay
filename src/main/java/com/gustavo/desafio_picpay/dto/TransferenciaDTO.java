package com.gustavo.desafio_picpay.dto;

import java.math.BigDecimal;

public class TransferenciaDTO {
    private Long idPagador;
    private Long idRecebedor;
    private BigDecimal valor;
    private String tipoRecebedor; // "LOJISTA" ou "COMUM"


    // Getters e Setters
    public Long getIdPagador() {
        return idPagador;
    }

    public void setIdPagador(Long idPagador) {
        this.idPagador = idPagador;
    }

    public Long getIdRecebedor() {
        return idRecebedor;
    }

    public void setIdRecebedor(Long idRecebedor) {
        this.idRecebedor = idRecebedor;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public void setTipoRecebedor(String tipoRecebedor){
        this.tipoRecebedor = tipoRecebedor;
    }

    public String getTipoRecebedor(){
        return tipoRecebedor;
    }
}
