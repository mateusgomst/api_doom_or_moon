Com base nas alterações para rodar a API em container, vou atualizar o README:

# Doom or Moon API

A **Doom or Moon** é uma API projetada para analisar a tendência das criptomoedas e indicar se estão em um movimento de alta ou baixa. Utilizamos modelos matemáticos e estatísticos para prever padrões e ajudar na tomada de decisão de investimentos.

---

## 📖 Sobre o Projeto

Projeto em desenvolvimento – API para análise de comportamento do Bitcoin

Atualmente estou desenvolvendo uma API focada na análise de valorização e desvalorização do Bitcoin em diferentes horizontes de tempo (curto e longo prazo). A proposta não é fornecer recomendações financeiras, mas sim uma ferramenta de apoio à decisão baseada em modelos matemáticos e probabilísticos.

Um dos principais destaques do projeto é o controle eficiente de requisições à API externa. Isso permite um uso sustentável dos dados mesmo em planos gratuitos, evitando custos desnecessários com planos comerciais que podem chegar a valores entre R$ 1.000 e R$ 6.000 mensais.

O projeto é desenvolvido com Java, usando Spring Boot, JPA e PostgreSQL, rodando em containers Docker.

---

## 🚀 Como configurar o projeto

### Requisitos
- Docker
- Docker Compose

### 1. Clone o repositório:
```bash
git clone https://github.com/mateusgomst/api_doom_or_moon.git
cd api_doom_or_moon
```

### 2. Configure o ambiente
Crie um arquivo `.env` na raiz do projeto baseado no `.env.example`:

```dotenv
DATABASE_USERNAME=seu_usuario_postgres
DATABASE_PASSWORD=sua_senha_postgres
KEY_API_COIN=sua_chave_coingecko
```

📈 **Obtenha sua chave de API no CoinGecko:**
Acesse o [CoinGecko API](https://www.coingecko.com/en/api) para criar uma chave gratuita.

### 3. Inicie a aplicação
```bash
docker-compose up -d
```

A API estará disponível em `http://localhost:8080`

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

- 📊 Histórico de valores:
  ```
  GET http://localhost:8080/api/bitcoin/historico
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
<<<<<<< HEAD
- **Hibernate**
- **Maven**
=======
- **Docker**
- **Docker Compose**

>>>>>>> bad054e (ajustes nas configurações para deploy em Docker)
---

## 📌 Contribuição
Fique à vontade para contribuir com melhorias para a API! Basta abrir um Pull Request ou relatar problemas na aba de **Issues**.
<<<<<<< HEAD
=======
```

Principais mudanças:
- Simplificado processo de instalação
- Removidas etapas de configuração manual do banco
- Atualizada seção de requisitos
- Adicionadas instruções para Docker
- Removidas configurações desnecessárias do `.env`
- Adicionado endpoint de histórico
>>>>>>> bad054e (ajustes nas configurações para deploy em Docker)
