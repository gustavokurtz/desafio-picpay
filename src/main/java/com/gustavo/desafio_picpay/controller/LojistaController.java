package com.gustavo.desafio_picpay.controller;

import com.gustavo.desafio_picpay.entities.Lojista;
import com.gustavo.desafio_picpay.service.LojistaService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lojista")
public class LojistaController {

    @Autowired
    LojistaService lojistaService;

    @PostMapping
    public ResponseEntity<Lojista> criarLojista(@RequestBody Lojista lojista){
        return ResponseEntity.status(201).body(lojistaService.criarLojista(lojista));
    }

    @GetMapping
    public List<Lojista> listarLojistas(){
        return lojistaService.listarLojistas();
    }

}
