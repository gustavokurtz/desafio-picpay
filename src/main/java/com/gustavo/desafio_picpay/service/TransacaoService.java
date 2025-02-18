package com.gustavo.desafio_picpay.service;

import com.gustavo.desafio_picpay.entities.Lojista;
import com.gustavo.desafio_picpay.entities.UsuarioBase;
import com.gustavo.desafio_picpay.entities.UsuarioComum;
import com.gustavo.desafio_picpay.repositories.LojistaRepository;
import com.gustavo.desafio_picpay.repositories.UsuarioComumRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class TransacaoService {

    @Autowired
    private UsuarioComumRepository usuarioComumRepository;

    @Autowired
    private LojistaRepository lojistaRepository;

    @Transactional
    public String transferir(Long idPagador, Long idRecebedor, BigDecimal valor, String tipoRecebedor) {
        // Valida se o valor da transferência não é nulo e é maior que zero
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Valor da transferência inválido.");
        }

        // O pagador deve ser um UsuarioComum
        UsuarioComum pagador = usuarioComumRepository.findById(idPagador)
                .orElseThrow(() -> new RuntimeException("Usuário comum pagador não encontrado"));

        if (pagador.getSaldo() == null) {
            pagador.setSaldo(BigDecimal.ZERO);
        }

        if (pagador.getSaldo().compareTo(valor) < 0) {
            throw new RuntimeException("Saldo insuficiente.");
        }

        // Verifica o tipo do recebedor
        if ("LOJISTA".equalsIgnoreCase(tipoRecebedor)) {
            Lojista recebedor = lojistaRepository.findById(idRecebedor)
                    .orElseThrow(() -> new RuntimeException("Recebedor lojista não encontrado"));
            if (recebedor.getSaldo() == null) {
                recebedor.setSaldo(BigDecimal.ZERO);
            }



            pagador.setSaldo(pagador.getSaldo().subtract(valor));
            recebedor.setSaldo(recebedor.getSaldo().add(valor));

            usuarioComumRepository.save(pagador);
            lojistaRepository.save(recebedor);
        } else {
            // Se for COMUM ou outro valor, busca no repositório de UsuarioComum
            UsuarioComum recebedor = usuarioComumRepository.findById(idRecebedor)
                    .orElseThrow(() -> new RuntimeException("Recebedor comum não encontrado"));
            if (recebedor.getSaldo() == null) {
                recebedor.setSaldo(BigDecimal.ZERO);
            }
            pagador.setSaldo(pagador.getSaldo().subtract(valor));
            recebedor.setSaldo(recebedor.getSaldo().add(valor));

            usuarioComumRepository.save(pagador);
            usuarioComumRepository.save(recebedor);
        }
        return "Transferência realizada com sucesso!";
    }
}
