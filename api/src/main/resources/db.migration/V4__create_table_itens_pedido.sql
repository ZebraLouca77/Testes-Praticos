CREATE TABLE itens_pedido
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    pedido_id  BIGINT NOT NULL,
    produto_id BIGINT NOT NULL,
    quantidade INT    NOT NULL,
    cd_id      BIGINT,
    FOREIGN KEY (pedido_id) REFERENCES pedidos (id),
    FOREIGN KEY (produto_id) REFERENCES produtos (id),
    FOREIGN KEY (cd_id) REFERENCES centros_distribuicao (id)
);