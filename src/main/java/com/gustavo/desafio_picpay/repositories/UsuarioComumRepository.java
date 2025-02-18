package com.gustavo.desafio_picpay.repositories;

import com.gustavo.desafio_picpay.entities.UsuarioComum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioComumRepository extends JpaRepository<UsuarioComum, Long> {
}
