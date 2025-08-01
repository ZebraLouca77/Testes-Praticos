package meli.pedidos.api.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import meli.pedidos.api.centrodistribuicao.CentroDistribuicaoPedido;
import meli.pedidos.api.itempedido.ItemPedido;
import meli.pedidos.api.itempedido.ItemPedidoCentroDistribuicao;
import meli.pedidos.api.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import meli.pedidos.api.pedido.Pedido;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemPedidoService {

    private final PedidoRepository pedidoRepository;
    private final CentroDistribuicaoService centroDistribuicaoService;

    public List<ItemPedidoCentroDistribuicao> consultarItensECdsDoPedido(Long pedidoId) {
        var pedido = buscarPedidoOuFalhar(pedidoId);

        return pedido.getItens().stream()
                .map(this::mapearParaItemPedidoCentroDistribuicao)
                .toList();
    }

    private Pedido buscarPedidoOuFalhar(Long pedidoId) {
        return pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));
    }

    private ItemPedidoCentroDistribuicao mapearParaItemPedidoCentroDistribuicao(ItemPedido item) {
        var centros = centroDistribuicaoService.consultaCentroDistribuicao(item.getProduto().getId());

        var centrosDTO = centros.stream()
                .map(cd -> new CentroDistribuicaoPedido(cd.nome(), cd.estoque()))
                .toList();

        return new ItemPedidoCentroDistribuicao(
                item.getProduto().getId(),
                item.getProduto().getNome(),
                centrosDTO
        );
    }
}