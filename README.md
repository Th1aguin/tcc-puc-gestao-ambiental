# Prova de Conceito - Arquitetura de Software - PUC Minas
Este repositório mantém os códigos fontes da prova de conceito elaborada para o trabalho de conclusão de curso da pós graduação em Arquitetura de Sistemas Distribuídos.

## Escopo do projeto
O escopo escolhido para o TCC foi o de Sistema de Gestão Ambiental.

## Técnologias utilizadas


* [Java](https://java.com/en/download/)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [H2 batabase](http://www.h2database.com/html/main.html)
* [Maven](https://maven.apache.org/)
* [Bootstrap](https://getbootstrap.com/)



## Executando a aplicação

1. Como pré-requisito, possuir [docker](https://www.docker.com/).
2. Baixar o arquivo docker-compose.yml deste repositório e executar o comando: docker-compose up
3. A aplicação estará disponível na URL: http://localhost:8080/
4. Para o primeiro acesso ao sistema, o usuário de Administrador do sistema já é criado:
  * Usuário: admin
  * Senha: 12345678

## Projetos do repositório
* eureka: É o servidor de registros (toda api se registra nele).
* zull: É o servidor gateway (é a porta de entrada para acessar os serviços).
* monitoramento-service: Api de monitoramento.
* seguranca-comunicacao: Api de segurança e comunicação.
* monitoramento: Aplicaçao Web/mobile que consome os serviços feitos.

## Integração Contínua

[![Build Status](https://travis-ci.org/Th1aguin/tcc-puc-gestao-ambiental.svg?branch=master)](https://travis-ci.org/Th1aguin/tcc-puc-gestao-ambiental)
