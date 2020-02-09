Projeto de avaliação. Valida e armazena agendamentos financeiros, seguindo regras de taxação pré-definidas.

### Recursos

- Interface com críticas amigáveis;
- API REST com endpoints para cadastro e listagem;
- Documentação da API;
- Testes unitários;

# Agenda Financeira

![](https://img.shields.io/github/stars/dpf-denilson/AgendaFinanceira.svg) ![](https://img.shields.io/github/forks/dpf-denilson/AgendaFinanceira.svg) ![](https://img.shields.io/github/last-commit/dpf-denilson/AgendaFinanceira.svg) ![](https://img.shields.io/github/issues/dpf-denilson/AgendaFinanceira.svg)

**Tabela de Conteúdo**

[TOCM]

[TOC]

#Tecnologia
- [Java 13](https://www.oracle.com/technetwork/java/javase/downloads/index.html)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Maven](https://maven.apache.org/)
- [H2 Database engine](https://www.h2database.com/)
- [Swagger](https://swagger.io/)
- [Thymeleaf](https://www.thymeleaf.org/)

###Java 13
A versão 13 foi escolhida por ser a versão mais recente disponível.
###Spring Boot
Spring Boot foi utilizado pela praticidade e por ter sido sitado como tecnologia utilizada pela empresa.
###Maven
Escolhido pela praticidade, e integração com Spring Boot
###H2 Database Engine
Escolhido pela pertinência ideal aos requisitos do projeto.
###Swagger
Incluído para documentação da API, como diferencial.
Foi utilizada a versão [3.0.0-SNAPSHOT](http://oss.jfrog.org/artifactory/oss-snapshot-local/) para melhor integração com Spring Boot.
###Thymeleaf
Selecionado para atender os objetivos extendidos da proposta.
###Ferramentas extras
- [Spring Initializr](https://start.spring.io/)
- [IDE IntelliJ IDEA](https://www.jetbrains.com/idea/)

#Arquitetura

##Estrutura
O projeto é um servidor REST. Possui uma API para comunicação RESTFul via subdiretório /api e uma UI ativa diretamente na raiz /. Adicionamente a documentação Swagger pode ser encontrada em /swagger-ui.html e, para via de demonstração, existe visível, o console da base de dados H2 no subdiretório /h2.

##Decisões
Ações  | Motivações
------------- | -------------
Descarte da ideia de comunicação direta com o BD para demonstração de desempenho.  | Ganho de agilidade com facilidade encontrada com utilização de JPA (Hibernate)
Descarte da classe para logging e controle de strings de erro. | Aprendizado sobre métodos de validação mais práticos via annotations.
Controle de usuários não foi implementado. | Fora do escopo.
Não foram consideradas variações de timezone durante registro dos agendamentos. | Simplificação e objetividade.
Não foram consideradas conversões de moeda. | Fora do escopo, manutenção de objetividade.
Criação de dois Controllers, um para API e outro para UI. | Thymeleaft necessitava de retornos String para indicação de qual recurso carregar. Organização e clareza do código.
Criação de um Model separado do Entity para utilização pelos Controllers. | Segurança (ocultar estruturas internas), adaptabilidade (campos de um Model não necessáriamente representam valores em Entities), modularidade.
Criação de classe abstrata para conversão entre Model e Entity. | Evitar interdependência entre códigos de Model e Entity.

#Funcionamento

###API
A API possui duas chamadas.

####/api/agendar
Atende verbos POST. Recebe um agendamento JSON para validação, taxação e armazenamento.
```JSON
{
	"cOrigem": [Conta_de_Origem],
	"cDestino": [Conta_de_Destino],
	"vTransf": [Valor_da_Transferência],
	"dtEfeito": [Data_para_agendamento]
}
```
####/api/listar
Lista um array JSON com todos os agendamentos feitos.