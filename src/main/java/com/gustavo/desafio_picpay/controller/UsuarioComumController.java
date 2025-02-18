package com.gustavo.desafio_picpay.controller;

import com.gustavo.desafio_picpay.entities.Lojista;
import com.gustavo.desafio_picpay.entities.UsuarioComum;
import com.gustavo.desafio_picpay.service.LojistaService;
import com.gustavo.desafio_picpay.service.UsuarioComumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UsuarioComumController {
    @Autowired
    UsuarioComumService usuarioComumService;

    @PostMapping
    public ResponseEntity<UsuarioComum> criarUsuarioComum(@RequestBody UsuarioComum usuarioComum){
        return ResponseEntity.status(201).body(usuarioComumService.criarUsuarioComum(usuarioComum));
    }

    @GetMapping
    public List<UsuarioComum> listarUsuarioComum(){
        return usuarioComumService.listarUsuarioComum();
    }
}
