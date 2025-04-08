# Doom or Moon API

A **Doom or Moon** é uma API projetada para analisar a tendência das criptomoedas e indicar se estão em um movimento de alta ou baixa. Utilizamos modelos matemáticos e estatísticos para prever padrões e ajudar na tomada de decisão de investimentos.

---

## 📖 Sobre o Projeto

Projeto em desenvolvimento – API para análise de comportamento do Bitcoin

Atualmente estou desenvolvendo uma API focada na análise de valorização e desvalorização do Bitcoin em diferentes horizontes de tempo (curto e longo prazo). A proposta não é fornecer recomendações financeiras, mas sim uma ferramenta de apoio à decisão baseada em modelos matemáticos e probabilísticos.

Um dos principais destaques do projeto é o controle eficiente de requisições à API externa. Isso permite um uso sustentável dos dados mesmo em planos gratuitos, evitando custos desnecessários com planos comerciais que podem chegar a valores entre R$ 1.000 e R$ 6.000 mensais. Com essa estratégia, a aplicação pode operar de forma estável e econômica por tempo indeterminado.

O projeto é desenvolvido com Java, usando Spring Boot, JPA e PostgreSQL, e tem sido um excelente desafio técnico. Tenho aprendido muito sobre consumo inteligente de APIs, boas práticas no desenvolvimento backend e aplicação de matemática para avaliação de dados do mercado financeiro.

---

## 🚀 Como configurar o projeto

### 1. Clone o repositório:
```bash
git clone https://github.com/mateusgomst/api_doom_or_moon.git
```

### 2. Acesse a pasta do projeto:
```bash
cd api_doom_or_moon
```

### 3. Instale as dependências do projeto:
```bash
mvn install
```

### 4. Configure o banco de dados
🛒 **Lembre-se de criar seu banco de dados!** Esta API utiliza **PostgreSQL**, portanto, certifique-se de que ele está instalado e rodando na sua máquina.

Crie um banco de dados chamado `doom_or_moon` (ou outro nome de sua preferência):

```sql
CREATE DATABASE doom_or_moon;
```

### 5. Configure suas variáveis de ambiente

Crie um arquivo `.env` na raiz do projeto com o seguinte conteúdo:

```ini
DRIVER_CLASS_NAME=org.postgresql.Driver
DATABASE_URL=jdbc:postgresql://localhost:5432/doom_or_moon
DATABASE_USERNAME=seu_usuario
DATABASE_PASSWORD=sua_senha
HIBERNATE_DIALECT=org.hibernate.dialect.PostgreSQLDialect
DDL_AUTO=update
SHOW_SQL=true
KEY_API_COIN=sua_key_do_CoinGecko
```

📈 **Obtenha sua chave de API no CoinGecko:**

Acesse o site [CoinGecko API](https://www.coingecko.com/en/api) para criar uma chave de API gratuita e substitua `sua_key_do_CoinGecko` no `.env`.

> 💡 **Obs:** Certifique-se de que o Spring Boot está carregando as variáveis do `.env`. Se não estiver usando alguma biblioteca como `dotenv`, configure essas variáveis diretamente no `application.properties` ou `application.yml`.

### 6. Inicie a API
```bash
mvn spring-boot:run
```

Se tudo estiver certo, a API estará rodando em:
```
http://localhost:8080
```

---

## Endpoints

- 🔍 Tendência de longo prazo:
  ```
  GET http://localhost:8080/api/bitcoin/longo-prazo
  ```

- 🔎 Tendência de curto prazo:
  ```
  GET http://localhost:8080/api/bitcoin/curto-prazo
  ```

### Exemplo de resposta:
```json
{
  "tendencia": "BAIXA",
  "motivo": "EMA9 (81556,52) < EMA21 (83169,59) e RSI (40,62) < 50",
  "forca": 40.61872419351869
}
```

---

## 🛠 Tecnologias Utilizadas
- **Java 17**
- **Spring Boot**
- **PostgreSQL**
- **Hibernate**
- **Maven**

---

## 📌 Contribuição
Fique à vontade para contribuir com melhorias para a API! Basta abrir um Pull Request ou relatar problemas na aba de **Issues**.