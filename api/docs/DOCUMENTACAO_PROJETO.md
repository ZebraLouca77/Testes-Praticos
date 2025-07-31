# Documentação Técnica - Projeto API de Pedidos

## ✅ Visão Geral
Este projeto tem como objetivo construir uma API que permite:
- Processar pedidos e determinar os Centros de Distribuição com estoque para cada item com limite de 100 intens por pedido.
- Consultar os itens de um pedido e os CDs associados.
- Consultar CDs por tens e derterminar o CD de um item.

Baseado no desafio proposto para liderança técnica no Mercado Livre.

---

## 🎓 Requisitos do Desafio

### Funcionais:
- Um pedido pode conter até **100 itens**.
- Deve existir uma API para **consultar pedidos** com itens e CDs.
- Deve existir uma API para **processar pedidos** e retornar os CDs com estoque.
- Deve-se usar a API de consulta de CDs (mockada).

### Não Funcionais:
- Pode-se usar qualquer linguagem/framework.
- Não requer frontend.
- API externa pode ser mockada internamente.

---

## 🚀 Tecnologias Utilizadas
- Java 17
- Spring Boot 3.x
- Maven
- Lombok
- JPA (Hibernate)
- Flyway (migrations)
- MySQL 8+
- Insomnia / curl para testes de API

---

## 📁 Estrutura de Pacotes (principais)
```
meli.pedidos.api
├── cliente
├── produto
├── centrodistribuicao
├── pedido
├── itempedido
├── estoque
```

---

## 🗃️ Modelo de Dados (Entidades)

### Cliente
- id (PK)
- nome
- email

### Produto
- id (PK)
- nome
- sku

### CentroDistribuicao
- id (PK)
- nome
- estoque

### Pedido
- id (PK)
- cliente_id (FK)
- status

### ItemPedido
- id (PK)
- pedido_id (FK)
- produto_id (FK)
- cd_id (FK)
- quantidade

---

## 🧬 Migrations - Flyway (SQL)
Scripts:
- V1__create_talbe_clientes.sql
- V1__create_table_produtos.sql
- V1__create_table_pedido.sql
- V1__create_table_itens_pedido.sql
- V1__create_table_centros_distribuicao.sql
- V1__create_table_estoque_cd.sql

---

## ⚙️ application.properties
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/meli_api
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migration
server.port=8080
```

---

## 📊 Endpoints REST

### 1. Consultar itens e CDs de um pedido
**GET** `/pedidos/{id}/itens-cds`

### 2. Processar pedido com lista de itens
**POST** `/pedidos/processar`

**Request:**
```json
{
  "itens": [
    { "produtoId": 1 },
    { "produtoId": 2 }
  ]
}
```

### 3. Mock da API externa (CDs por item)
**GET** `/centros-distribuicao?produtoId=1`

---

## 🧪 Testes via curl

```bash
curl -X GET http://localhost:8080/pedidos/1/itens-cds

curl -X POST http://localhost:8080/pedidos/processar \
  -H "Content-Type: application/json" \
  -d '{ "itens": [ {"produtoId": 1}, {"produtoId": 2} ] }'
```

---

## 🛠️ Execução do Projeto

```bash
./mvnw clean spring-boot:run
```

---

## 📌 Observações Finais
- Arquitetura limpa com controllers, services, DTOs e repositórios
- Uso de Lombok reduz boilerplate
- Flyway permite versionamento seguro

---

## 🛢️ Create das tabelas para o banco de dados "pedidos_api" (MySQL)

```sql
- 1. Tabela de clientes (opcional)
CREATE TABLE clientes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    criado_em DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 2. Tabela de pedidos
CREATE TABLE pedidos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cliente_id BIGINT,
    data_criacao DATETIME DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50) DEFAULT 'NOVO',
    FOREIGN KEY (cliente_id) REFERENCES clientes(id)
);

-- 3. Tabela de produtos
CREATE TABLE produtos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    sku VARCHAR(50) UNIQUE NOT NULL
);

-- 4. Tabela de centros de distribuição (CDs)
CREATE TABLE centros_distribuicao (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    endereco VARCHAR(200),
    status VARCHAR(20) DEFAULT 'ATIVO'
);

-- 5. Tabela de estoque por CD (opcional, mas útil pra simular o cenário da API)
CREATE TABLE estoque_cd (
    cd_id BIGINT,
    produto_id BIGINT,
    quantidade INT DEFAULT 0,
    PRIMARY KEY (cd_id, produto_id),
    FOREIGN KEY (cd_id) REFERENCES centros_distribuicao(id),
    FOREIGN KEY (produto_id) REFERENCES produtos(id)
);

-- 6. Tabela de itens do pedido
CREATE TABLE itens_pedido (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    pedido_id BIGINT NOT NULL,
    produto_id BIGINT NOT NULL,
    quantidade INT NOT NULL,
    cd_id BIGINT,
    FOREIGN KEY (pedido_id) REFERENCES pedidos(id),
    FOREIGN KEY (produto_id) REFERENCES produtos(id),
    FOREIGN KEY (cd_id) REFERENCES centros_distribuicao(id)
);

```

## 🧾 Inserts para popular o banco de dados (MySQL)

```sql
-- CLIENTES
INSERT INTO clientes (nome, email) VALUES 
('João Silva', 'joao.silva@email.com'),
('Maria Oliveira', 'maria.oliveira@email.com');

-- PRODUTOS
INSERT INTO produtos (nome, sku) VALUES
('Notebook Dell Inspiron', 'SKU-NTB-001'),
('Mouse Logitech', 'SKU-MSE-002'),
('Teclado Mecânico Redragon', 'SKU-KBD-003');

-- CENTROS DE DISTRIBUIÇÃO (CDs)
INSERT INTO centros_distribuicao (nome, endereco, status) VALUES
('CD São Paulo', 'Rua A, SP', 'ATIVO'),
('CD Rio de Janeiro', 'Rua B, RJ', 'ATIVO'),
('CD Recife', 'Rua C, PE', 'INATIVO');

-- ESTOQUE POR CD
INSERT INTO estoque_cd (cd_id, produto_id, quantidade) VALUES
(1, 1, 15),  -- SP - Notebook
(1, 2, 30),  -- SP - Mouse
(2, 2, 10),  -- RJ - Mouse
(2, 3, 50);  -- RJ - Teclado

-- PEDIDOS
INSERT INTO pedidos (cliente_id, status) VALUES
(1, 'NOVO'),
(2, 'NOVO');

-- ITENS DO PEDIDO
-- Pedido 1: João comprou 1 notebook (vai do CD SP) e 1 mouse (vai do CD RJ)
INSERT INTO itens_pedido (pedido_id, produto_id, quantidade, cd_id) VALUES
(1, 1, 1, 1),
(1, 2, 1, 2);

-- Pedido 2: Maria comprou 2 teclados (vai do CD RJ)
INSERT INTO itens_pedido (pedido_id, produto_id, quantidade, cd_id) VALUES
(2, 3, 2, 2);
```
