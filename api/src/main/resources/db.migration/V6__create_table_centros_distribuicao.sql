CREATE TABLE centros_distribuicao
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome     VARCHAR(100) NOT NULL,
    endereco VARCHAR(200),
    status   VARCHAR(20) DEFAULT 'ATIVO'
);
