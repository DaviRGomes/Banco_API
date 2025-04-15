# Santander_API

Este é um projeto de API desenvolvido para gerenciar operações bancárias, como criação de usuários, contas, cartões e transações financeiras. A API foi construída utilizando **Java** e **Spring Boot**, com suporte a documentação via **Swagger**.

## 🚀 Funcionalidades

- **Gerenciamento de Usuários**: Criação e consulta de usuários.
- **Gerenciamento de Contas Bancárias**: Criação e consulta de contas.
- **Gerenciamento de Cartões**: Criação e consulta de cartões.
- **Gerenciamento de Transações**: Realização e listagem de transações financeiras.
- **Documentação da API**: Configurada com Swagger/OpenAPI para facilitar o consumo e testes dos endpoints.

## 🛠️ Tecnologias Utilizadas

- Java (versão 17 ou superior recomendada)
- Spring Boot
- Spring Data JPA
- Swagger/OpenAPI
- Banco de Dados H2 (pode ser substituído por outro, se necessário)
- Maven

## ▶️ Como Executar o Projeto

### 1. Clone o repositório

```bash
git clone https://github.com/DaviRGomes/desafio-dio
cd Santander_API
```

### 2. Configure o Banco de Dados

No arquivo `application.properties` (localizado em `src/main/resources`), ajuste a configuração do banco de dados se necessário. Por padrão, ele está configurado para usar o H2.

### 3. Execute o Projeto

Utilize o Maven para executar o projeto:

```bash
mvn spring-boot:run
```

### 4. Acesse a Documentação Swagger

Abra no navegador:

```
http://localhost:8080/swagger-ui.html
```

## 💻 Exemplos de Código

### 📌 Criação de Cartão (`CardService.java`)

```java
public Card createCard(Long userId, Card card) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    card.setUser(user);
    user.setCard(card);
    cardRepository.save(card);
    userRepository.save(user);
    return card;
}
```

### 💳 Realização de Transação (`TransacaoService.java`)

```java
public Transacao createTransaction(TransacaoDTO dto) {
    User cliente = userRepository.findById(dto.getClienteId())
        .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

    switch (dto.getTipo()) {
        case PIX:
            if (cliente.getAccount().getBalance().compareTo(dto.getValor()) < 0) {
                throw new RuntimeException("Saldo insuficiente para PIX");
            }
            cliente.getAccount().setBalance(
                cliente.getAccount().getBalance().subtract(dto.getValor())
            );
            break;
        case CARTAO:
            Card cartao = cardRepository.findById(dto.getCartaoId())
                .orElseThrow(() -> new RuntimeException("Cartão não encontrado"));
            if (cartao.getSaldo().compareTo(dto.getValor()) < 0) {
                throw new RuntimeException("Limite do cartão insuficiente");
            }
            cartao.setSaldo(cartao.getSaldo().subtract(dto.getValor()));
            break;
        default:
            throw new IllegalArgumentException("Tipo de transação inválido");
    }
    return transactionRepository.save(transaction);
}
```

## 📡 Endpoints Disponíveis

### 👤 Usuários (`/users`)
- `POST /users`: Cria um novo usuário.
- `GET /users/{id}`: Retorna os detalhes de um usuário específico.

### 💼 Contas Bancárias (`/accounts`)
- `POST /accounts/{userId}`: Cria uma conta para um usuário existente.
- `GET /accounts/{id}`: Retorna os detalhes de uma conta específica.

### 💳 Cartões (`/cards`)
- `POST /cards/{userId}`: Cria um cartão associado a um usuário.
- `GET /cards/{id}`: Retorna os detalhes de um cartão específico.

### 💸 Transações (`/transacoes`)
- `POST /transacoes`: Realiza uma transação financeira.
- `GET /transacoes`: Lista todas as transações realizadas.

## 📖 Configuração do Swagger

A configuração do Swagger está localizada no arquivo `SwaggerConfig.java`, permitindo uma documentação rica e interativa da API.

```java
@Bean
public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .servers(List.of(
            new Server().url("http://localhost:8080").description("Servidor local")
        ))
        .info(new Info()
            .title("API de aplicativo de Banco")
            .version("1.0")
            .description("API desenvolvida por Davi Rosa Gomes!")
            .contact(new Contact()
                .name("Davi")
                .url("https://github.com/DaviRGomes")
                .email("davirosagomes.eng@gmail.com")));
}
```

## 🧠 Autor

Desenvolvido com 💻 por **Davi Rosa Gomes**  
- GitHub: [@DaviRGomes](https://github.com/DaviRGomes)  
- Email: davirosagomes.eng@gmail.com

## 📄 Licença

Este projeto está licenciado sob a licença **MIT**. Veja o arquivo `LICENSE` para mais detalhes.

---

⭐️ Não esqueça de deixar uma estrela no repositório se este projeto te ajudou!  
🚀 Bons estudos e sucesso na sua jornada como dev!
