package com.gustavo.desafio_picpay.dto;

public class AuthorizationResponseDTO {
    private String status;
    private Data data;

    // Getters e setters

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    @Override
    public String toString() {
        return "AuthorizationResponse{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        private Boolean authorization;

        public Boolean getAuthorization() {
            return authorization;
        }

        public void setAuthorization(Boolean authorization) {
            this.authorization = authorization;
        }
    }
}
