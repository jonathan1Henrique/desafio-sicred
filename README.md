# Desafio- SICREDI
Desafio SICREDI - Sistema de criação de votação de pautas

## Passo a passo para rodar a API 
  ### Kafka
   * Baixar e rodar o Kafka
   * Criar um topico com o nome de VOTO_PAUTA
   
  ### Banco de dados
  * Baixar e instalar o banco de dados postgres
  * Criar um usuario e senha para o seu banco no processo de instalação
  * Mudar a senha e usuário no application.yml para o usuário e senha que foi criado no processo de isntalação do banco (Mudar: linha 16 campo username e linha 17 campo password)
  * Criar um databese chamado agenda
  * Rodar o conteúdo do arquivo **banco_dados.sql**

  ## Lombok 
   * Será necessário instalar o Jar do Lombok dependendo da sua IDE

## Tecnologias usada
 * Spring boot
 * Spring Data JPA
 * Junit ( Para teste unitários )
 * Lombok
 * Postgresql
 * Swagger ( para documentação -- Ao rodar o sistema acesse o link http://localhost:8080/swagger-ui.html#/ para ter acesso o Swagger )
 * Kafka
 * Java 11
