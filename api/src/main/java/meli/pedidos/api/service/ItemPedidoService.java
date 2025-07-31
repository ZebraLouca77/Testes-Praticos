package meli.pedidos.api.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import meli.pedidos.api.centrodistribuicao.CentroDistribuicaoPedido;
import meli.pedidos.api.itempedido.ItemPedidoCentroDistribuicao;
import meli.pedidos.api.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemPedidoService {

    private final PedidoRepository pedidoRepository;
    private final CentroDistribuicaoService centroDistribuicaoService; // sua service aqui

    public List<ItemPedidoCentroDistribuicao> consultarItensECdsDoPedido(Long pedidoId) {
        var pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));

        return pedido.getItens().stream() // aqui vem somente os itens do pedido 1
                .map(item -> {
                    var cds = centroDistribuicaoService.consultaCentroDistribuicao(item.getProduto().getId());
                    var cdsDTO = cds.stream()
                            .map(cd -> new CentroDistribuicaoPedido(cd.nome(), cd.estoque()))
                            .toList();

                    return new ItemPedidoCentroDistribuicao(
                            item.getProduto().getId(),
                            item.getProduto().getNome(),
                            cdsDTO
                    );
                })
                .toList();
    }
}