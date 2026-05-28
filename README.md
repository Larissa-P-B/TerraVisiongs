# 🌎 TerraVision API

API REST desenvolvida em Java 21 + Spring Boot para monitoramento climático inteligente, utilizando integração com a API OpenWeather e persistência de dados em MySQL.

---

# 📌 Objetivo do Projeto

O TerraVision foi criado para monitorar condições climáticas em diferentes cidades e identificar possíveis riscos ambientais como:

* Tempestades severas
* Vendavais
* Tornados
* Ondas de calor
* Alagamentos
* Secas extremas
* Instabilidade climática

O sistema realiza consultas climáticas em tempo real utilizando a API OpenWeather e salva o histórico no banco de dados MySQL.

---

# 🚀 Tecnologias Utilizadas

* Java 21
* Spring Boot 3
* Spring Web
* Spring Data JPA
* MySQL
* Lombok
* Swagger/OpenAPI
* Maven
* OpenWeather API

---

# 📂 Estrutura do Projeto

```text
src/main/java/br/com/api/terravision

├── client
├── controller
├── dto
├── entity
├── exception
├── interfaces
├── repository
├── service
└── config
```

---

# ⚙️ Configuração do Banco de Dados

## 1. Criar banco MySQL

Execute no MySQL:

```sql
CREATE DATABASE terravision;
```

---

# ⚙️ Configuração da API Key

O projeto utiliza variável de ambiente para proteger a chave da OpenWeather API.

## Criar variável de ambiente no Windows

Nome:

```text
OPENWEATHER_API_KEY
```

Valor:

```text
SUA_CHAVE_OPENWEATHER
```

---

# ⚙️ application.properties

```properties
spring.application.name=TerraVision

spring.datasource.url=jdbc:mysql://localhost:3306/terravision
spring.datasource.username=root
spring.datasource.password=123456

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

openweather.api.url=https://api.openweathermap.org/data/2.5/weather
openweather.api.key=${OPENWEATHER_API_KEY}
```

---

# ▶️ Como Executar o Projeto

## 1. Clonar repositório

```bash
git clone https://github.com/SEU-USUARIO/TerraVision.git
```

---

## 2. Entrar na pasta

```bash
cd TerraVision
```

---

## 3. Instalar dependências

```bash
mvn clean install
```

---

## 4. Executar aplicação

```bash
mvn spring-boot:run
```

Ou executar pela IDE:

* IntelliJ IDEA
* VSCode
* Eclipse

---

# 🌐 Swagger

Após iniciar o projeto:

```text
http://localhost:8080/swagger-ui/index.html
```

---

# 📡 Endpoints Disponíveis

## Buscar clima atual

```http
GET /clima/{cidade}
```

Exemplo:

```http
GET /clima/manaus
```

---

## Histórico climático

```http
GET /clima/historico
```

---

## Buscar histórico por ID

```http
GET /clima/historico/{id}
```

---

## Buscar por nível de risco

```http
GET /clima/risco/{risco}
```

Exemplo:

```http
GET /clima/risco/VENDAVAL
```

---

# 🧠 Regras Inteligentes de Risco

O sistema possui regras automáticas para identificação de riscos climáticos.

Exemplos:

| Condição                         | Resultado             |
| -------------------------------- | --------------------- |
| vento ≥ 120 km/h + baixa pressão | RISCO DE TORNADO      |
| chuva ≥ 100 mm                   | ALAGAMENTO EXTREMO    |
| temperatura ≥ 42°C               | ONDA DE CALOR EXTREMA |
| umidade baixa + calor intenso    | SECA EXTREMA          |
| vento forte                      | VENDAVAL              |

---

# 📊 Exemplo de Resposta

```json
{
  "cidade": "manaus",
  "temperatura": 30.27,
  "umidade": 70,
  "velocidadeVento": 15.2,
  "direcaoVento": 180,
  "volumeChuva": 0,
  "pressao": 1009,
  "nuvens": 75,
  "risco": "NORMAL",
  "nivelRisco": "NORMAL",
  "tipoAlerta": "NORMAL",
  "severidade": "BAIXO"
}
```

---

# 🛡️ Tratamento de Exceções

O projeto possui tratamento global de exceções utilizando:

```java
@RestControllerAdvice
```

Tratamentos implementados:

* Cidade inválida
* API externa indisponível
* Registro não encontrado
* Erros internos

---

# 📌 Funcionalidades Implementadas

✅ Integração com OpenWeather
✅ Persistência em MySQL
✅ Histórico climático
✅ DTOs e Entities
✅ Arquitetura em camadas
✅ POO
✅ Injeção de Dependência
✅ Logs da aplicação
✅ Swagger/OpenAPI
✅ Tratamento de exceções
✅ Regras inteligentes de risco climático
✅ Severidade de alertas

---

# 👨‍💻 Desenvolvido para

Global Solution — SOA & WebServices

FIAP — 2026
