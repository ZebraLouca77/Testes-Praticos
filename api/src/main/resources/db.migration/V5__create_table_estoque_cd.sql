CREATE TABLE estoque_cd
(
    cd_id      BIGINT,
    produto_id BIGINT,
    quantidade INT DEFAULT 0,
    PRIMARY KEY (cd_id, produto_id),
    FOREIGN KEY (cd_id) REFERENCES centros_distribuicao (id),
    FOREIGN KEY (produto_id) REFERENCES produtos (id)
);