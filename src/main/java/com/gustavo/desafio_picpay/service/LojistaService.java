package com.gustavo.desafio_picpay.service;


import com.gustavo.desafio_picpay.entities.Lojista;
import com.gustavo.desafio_picpay.repositories.LojistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LojistaService {

    @Autowired
    LojistaRepository lojistaRepository;

    public List<Lojista> listarLojistas(){
        return lojistaRepository.findAll();
    }

    public Lojista criarLojista(Lojista lojista){
        return lojistaRepository.save(lojista);
    }

    public void deletarLojista(Long id){
        lojistaRepository.deleteById(id);
    }

    public Lojista atualizarLojista(Long id, Lojista lojista){
        Lojista lojistaAtualizado = lojistaRepository.findById(id).orElseThrow();

        lojistaAtualizado.setNomeCompleto(lojista.getNomeCompleto());
        lojistaAtualizado.setEmail(lojista.getEmail());
        lojistaAtualizado.setSenha(lojista.getSenha());

        return lojistaRepository.save(lojistaAtualizado);

    }



}
