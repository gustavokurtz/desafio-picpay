package com.gustavo.desafio_picpay.service;

import com.gustavo.desafio_picpay.entities.Lojista;
import com.gustavo.desafio_picpay.entities.UsuarioComum;
import com.gustavo.desafio_picpay.repositories.LojistaRepository;
import com.gustavo.desafio_picpay.repositories.UsuarioComumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioComumService {

    @Autowired
    UsuarioComumRepository usuarioComumRepository;

    public List<UsuarioComum> listarUsuarioComum(){
        return usuarioComumRepository.findAll();
    }

    public UsuarioComum criarUsuarioComum(UsuarioComum usuarioComum){
        return usuarioComumRepository.save(usuarioComum);
    }

    public void deletarUsuarioComum(Long id){
        usuarioComumRepository.deleteById(id);
    }

    public UsuarioComum atualizarLojista(Long id, UsuarioComum usuarioComum){
        UsuarioComum UsuarioComumAtualizado = usuarioComumRepository.findById(id).orElseThrow();

        UsuarioComumAtualizado.setNomeCompleto(usuarioComum.getNomeCompleto());
        UsuarioComumAtualizado.setEmail(usuarioComum.getEmail());
        UsuarioComumAtualizado.setSenha(usuarioComum.getSenha());

        return usuarioComumRepository.save(UsuarioComumAtualizado);

    }
}
