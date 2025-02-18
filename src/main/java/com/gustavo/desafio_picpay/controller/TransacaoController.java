package com.gustavo.desafio_picpay.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gustavo.desafio_picpay.dto.AuthorizationResponse;
import com.gustavo.desafio_picpay.dto.TransferenciaDTO;
import com.gustavo.desafio_picpay.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping
    public String criarTransacao(@RequestBody TransferenciaDTO transferenciaDTO) {
        // 1. Consulta o serviço autorizador
        RestTemplate restTemplate = new RestTemplate();
        String uriAuth = "https://util.devi.tools/api/v2/authorize";

        ResponseEntity<AuthorizationResponse> responseEntity =
                restTemplate.exchange(uriAuth, HttpMethod.GET, null, AuthorizationResponse.class);

        AuthorizationResponse authResponse = responseEntity.getBody();

        // 2. Verifica se a autorização foi concedida
        if (authResponse != null && "success".equalsIgnoreCase(authResponse.getStatus()) &&
                authResponse.getData() != null && Boolean.TRUE.equals(authResponse.getData().getAuthorization())) {

            // 3. Se autorizado, executa a transferência
            String respostaTransferencia = transacaoService.transferir(
                    transferenciaDTO.getIdPagador(),
                    transferenciaDTO.getIdRecebedor(),
                    transferenciaDTO.getValor(),
                    transferenciaDTO.getTipoRecebedor()
            );

            // 4. Após a transferência, envia a notificação (utilizando o verbo POST)
            String uriNotificacao = "https://util.devi.tools/api/v1/notify";
            String respostaNotificacao;
            try {
                // Caso não haja um corpo específico para a notificação, pode-se enviar null
                respostaNotificacao = restTemplate.postForObject(uriNotificacao, null, String.class);
            } catch (Exception e) {
                // Aqui você pode definir um fallback ou logar a falha, se necessário
                respostaNotificacao = "Falha ao enviar notificação: " + e.getMessage();
            }

            // 5. Retorna uma resposta combinada ou trate conforme a necessidade
            return "Transferência executada: " + respostaTransferencia + " | Notificação: " + respostaNotificacao;
        } else {
            throw new RuntimeException("Transferência não autorizada pelo serviço externo.");
        }
    }
}
