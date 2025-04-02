# Doom or Moon API

A **Doom or Moon** é uma API projetada para analisar a tendência das criptomoedas e indicar se estão em um movimento de alta ou baixa. Utilizamos modelos matemáticos e estatísticos para prever padrões e ajudar na tomada de decisão de investimentos.

---

## 🚀 Como configurar o projeto

### 1. Clone o repositório:
```sh
 git clone https://github.com/mateusgomst/api_doom_or_moon.git
```

### 2. Acesse a pasta do projeto:
```sh
 cd api_doom_or_moon
```

### 3. Instale as dependências do projeto:
```sh
 mvn install
```

### 4. Configure o banco de dados
⚠ **Lembre-se de criar seu banco de dados!** Esta API utiliza **PostgreSQL**, portanto, certifique-se de que ele está instalado e rodando na sua máquina.

Crie um banco de dados chamado `doom_or_moon` (ou o nome que preferir).

No PostgreSQL, você pode criar o banco com:
```sql
CREATE DATABASE doom_or_moon;
```

### 5. Configure suas variáveis de ambiente
Crie um arquivo `.env` na raiz do projeto e configure as credenciais do banco de dados e outras variáveis necessárias:
```ini
DRIVER_CLASS_NAME=org.postgresql.Driver
DATABASE_URL=jdbc:postgresql://localhost:5432/doom_or_moon
DATABASE_USERNAME=seu_usuario
DATABASE_PASSWORD=sua_senha
HIBERNATE_DIALECT=org.hibernate.dialect.PostgreSQLDialect
DDL_AUTO=update
SHOW_SQL=true
```

### 6. Inicie a API
```sh
 mvn spring-boot:run
```

Se tudo estiver certo, a API estará rodando e pronta para ser utilizada! 🎉

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

---

