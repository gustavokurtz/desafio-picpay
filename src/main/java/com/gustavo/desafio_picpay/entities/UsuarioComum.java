package com.gustavo.desafio_picpay.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class UsuarioComum extends UsuarioBase {
    // Nenhum atributo adicional necessário
}
