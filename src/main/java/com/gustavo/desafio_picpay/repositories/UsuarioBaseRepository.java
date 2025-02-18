package com.gustavo.desafio_picpay.repositories;

import com.gustavo.desafio_picpay.entities.UsuarioBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioBaseRepository extends JpaRepository<UsuarioBase, Long> {
}
