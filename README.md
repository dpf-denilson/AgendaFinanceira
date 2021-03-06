Projeto de avaliação. Valida e armazena agendamentos financeiros, seguindo regras de taxação pré-definidas.

### Recursos

- Interface com críticas amigáveis;
- API REST com endpoints para cadastro e listagem;
- Documentação da API;
- Testes unitários;

# Agenda Financeira

![](https://img.shields.io/github/stars/dpf-denilson/AgendaFinanceira.svg) ![](https://img.shields.io/github/forks/dpf-denilson/AgendaFinanceira.svg) ![](https://img.shields.io/github/last-commit/dpf-denilson/AgendaFinanceira.svg) ![](https://img.shields.io/github/issues/dpf-denilson/AgendaFinanceira.svg)

# Tabela de Conteúdo

 1. [Tecnologia](#tecnologia)
 2. [Arquitetura](#arquitetura)
 3. [Funcionamento](#funcionamento)
 4. [Docker](#docker)

## Tecnologia
- [Java 13](https://www.oracle.com/technetwork/java/javase/downloads/index.html)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Maven](https://maven.apache.org/)
- [H2 Database engine](https://www.h2database.com/)
- [Swagger](https://swagger.io/)
- [Thymeleaf](https://www.thymeleaf.org/)

### Java 13
A versão 13 foi escolhida por ser a versão mais recente disponível.
### Spring Boot
Spring Boot foi utilizado pela praticidade e por ter sido citado como tecnologia utilizada pela empresa.
### Maven
Escolhido pela praticidade, e integração com Spring Boot.
### H2 Database Engine
Escolhido pela pertinência ideal aos requisitos do projeto.
### Swagger
Incluído para documentação da API como diferencial.
Foi utilizada a versão [3.0.0-SNAPSHOT](http://oss.jfrog.org/artifactory/oss-snapshot-local/) para melhor integração com Spring Boot.
### Thymeleaf
Selecionado para atender os objetivos extendidos da proposta.
### Ferramentas extras
- [Spring Initializr](https://start.spring.io/)
- [IDE IntelliJ IDEA](https://www.jetbrains.com/idea/)

## Arquitetura

O projeto consiste de um servidor REST.

### Pattern

O pattern escolhido foi o MVC pelos benefícios de escalabilidade e integração com demais ferramentas. 

### Estrutura

Estão incluídos:
 - API para comunicação RESTFul via subdiretório /api;
 - UI vinculada diretamente a raiz /;
 - Documentação Swagger pode ser encontrada em /swagger-ui.html;
 - Para via de demonstração, o console da base de dados H2 no subdiretório /h2.

### Decisões
| Ações | Motivações |
| --- | --- |
| Descarte da ideia de comunicação direta com o BD para demonstração de desempenho. | Ganho de agilidade pela facilidade encontrada na utilização de JPA (Hibernate). |
| Descarte da classe para logging e controle de strings de erro. | Aprendizado sobre métodos de validação mais práticos via annotations. |
| Controle de usuários não foi implementado. | Fora do escopo. |
| Não foram consideradas variações de timezone durante registro dos agendamentos. | Simplificação e objetividade. |
| Não foram consideradas conversões de moeda. | Fora do escopo, manutenção de objetividade. |
| Criação de dois Controllers, um para API e outro para UI. | Thymeleaf necessitava de retornos String para indicação de qual recurso carregar. Organização e clareza do código. |
| Criação de um Model separado do Entity, para utilização pelos Controllers. | Segurança (ocultar estruturas internas), adaptabilidade (campos de um Model não necessáriamente representam valores em Entities), modularidade. |
| Criação de classe abstrata para conversão entre Model e Entity. | Evitar interdependência entre códigos de Model e Entity.|
| Separação do agendamento e taxação em services independentes. | Modularidade. |

## Funcionamento

O servidor se por padrão se atachará à porta 8080.

### API 
A API possui duas chamadas.

#### /api/agendar
Atende verbos POST. Recebe um agendamento JSON para validação, taxação e armazenamento.
```JSON
{
	"cOrigem": "[Conta_de_Origem]",
	"cDestino": "[Conta_de_Destino]",
	"vTransf": "[Valor_da_Transferência]",
	"dtEfeito": "[Data_para_agendamento]"
}
```
#### /api/listar
Atende a verbos GET. Lista um array JSON com todos os agendamentos feitos.
```JSON
[
    {
        "id": 1,
        "cOrigem": "000001",
        "cDestino": "000002",
        "vTransf": 100.00,
        "vTaxa": 8.00,
        "dtInclusao": "2020-02-10",
        "dtEfeito": "2020-02-28"
    }
]
```

### UI
Contém formulário com quatro campos com as informações pertinentes ao agendamento e um botão para envio do conteúdo dos mesmos, para validação e eventual cadastro.
Abaixo do formulário são listados erros de validação e ao fim da página são listados todos os agendamentos já realizados.

## Docker

Uma imagem docker está disponível:
```
docker pull dpfdenilson/agendafinanceira:latest
```

Execução como:
```
docker run -p 8080:8080/tcp dpfdenilson/agendafinanceira:latest
```