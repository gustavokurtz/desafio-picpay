# Desafio Back-end PicPay

Este projeto simula uma versão simplificada do PicPay, a plataforma de pagamentos, focando no fluxo central de transferência de valores entre usuários. A aplicação diferencia entre usuários comuns — que podem enviar e receber dinheiro — e lojistas, que apenas recebem transferências.  

O sistema integra regras de negócio essenciais, como a verificação de saldo antes de efetivar uma transferência e a consulta a um serviço externo para autorização da operação. Caso a transferência seja aprovada, a aplicação também dispara uma notificação para o recebedor, simulando o envio de um e-mail ou SMS, mesmo diante da instabilidade de serviços terceiros.

Desenvolvido em Java com Spring Boot, o projeto adota uma abordagem RESTful e transacional, garantindo a integridade dos dados e a reversão de operações em caso de erro. A estrutura do código reflete boas práticas de organização e escalabilidade, demonstrando comprometimento com soluções limpas e eficientes.

Esta solução foi elaborada como parte de um desafio técnico e destaca competências em design de APIs, integração com serviços externos, e tratamento robusto de exceções, reafirmando meu foco em construir aplicações de alta qualidade.
