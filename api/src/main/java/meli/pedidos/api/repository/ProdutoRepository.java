package meli.pedidos.api.repository;

import meli.pedidos.api.produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // Caso precise futuramente buscar por SKU (campo único comum em e-commerce)
    Optional<Produto> findBySku(String sku);

    // Evita null ao buscar pelo nome
    Optional<Produto> findByNome(String nome);
}