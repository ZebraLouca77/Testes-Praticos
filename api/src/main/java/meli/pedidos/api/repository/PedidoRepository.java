package meli.pedidos.api.repository;

import meli.pedidos.api.pedido.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    // Buscar todos os pedidos de um cliente
    List<Pedido> findByClienteId(Long clienteId);

    // Buscar por status
    List<Pedido> findByStatus(String status);
}