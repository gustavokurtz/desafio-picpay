package com.gustavo.desafio_picpay.dto;

import org.springframework.beans.factory.annotation.Autowired;

public class TransacaoResponseDTO {

        AuthorizationResponseDTO authorizationResponseDTO = new AuthorizationResponseDTO();

        private Object notificationResponse;
        private String transferResponse; // resposta da transferência, se necessário

        public AuthorizationResponseDTO getAuthorizationResponse() {
            return authorizationResponseDTO;
        }

        public void setAuthorizationResponse(AuthorizationResponseDTO authorizationResponseDTO) {
            this.authorizationResponseDTO = authorizationResponseDTO;
        }

        public Object getNotificationResponse() {
            return notificationResponse;
        }

        public void setNotificationResponse(Object notificationResponse) {
            this.notificationResponse = notificationResponse;
        }

        public String getTransferResponse() {
            return transferResponse;
        }

        public void setTransferResponse(String transferResponse) {
            this.transferResponse = transferResponse;
        }
}
