package com.gustavo.desafio_picpay.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gustavo.desafio_picpay.dto.AuthorizationResponseDTO;
import com.gustavo.desafio_picpay.dto.TransacaoResponseDTO;
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

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;


    private TransacaoResponseDTO transacaoResponseDTO;

    @PostMapping("/criar")
    public TransacaoResponseDTO criarTransacao(@RequestBody TransferenciaDTO transferenciaDTO) {
        // 1. Consulta o serviço autorizador
        RestTemplate restTemplate = new RestTemplate();
        String uriAuth = "https://util.devi.tools/api/v2/authorize";

        ResponseEntity<AuthorizationResponseDTO> responseEntity =
                restTemplate.exchange(uriAuth, HttpMethod.GET, null, AuthorizationResponseDTO.class);

        AuthorizationResponseDTO authResponse = responseEntity.getBody();

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

            // 4. Após a transferência, envia a notificação (usando o verbo POST)
            String uriNotificacao = "https://util.devi.tools/api/v1/notify";
            String notificationResponseString;
            try {
                // Envia POST; se não houver corpo específico, envia null
                notificationResponseString = restTemplate.postForObject(uriNotificacao, null, String.class);
            } catch (Exception e) {
                notificationResponseString = "{\"error\": \"Falha ao enviar notificação: " + e.getMessage() + "\"}";
            }

            // Converte a resposta da notificação para objeto (JSON)
            Object notificationResponse;
            try {
                ObjectMapper mapper = new ObjectMapper();
                notificationResponse = mapper.readTree(notificationResponseString);
            } catch (JsonProcessingException e) {
                // Se a conversão falhar, retorna a string bruta
                notificationResponse = notificationResponseString;
            }

            // 5. Cria e retorna o objeto de resposta combinada
            TransacaoResponseDTO transacaoResponse = new TransacaoResponseDTO();
            transacaoResponse.setAuthorizationResponse(authResponse);
            transacaoResponse.setNotificationResponse(notificationResponse);
            // Você pode incluir também a resposta da transferência se necessário
            transacaoResponse.setTransferResponse(respostaTransferencia);
            return transacaoResponse;
        } else {
            throw new RuntimeException("Transferência não autorizada pelo serviço externo.");
        }
    }


}
