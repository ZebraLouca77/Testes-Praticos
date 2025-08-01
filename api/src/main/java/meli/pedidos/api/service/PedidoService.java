package meli.pedidos.api.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import meli.pedidos.api.centrodistribuicao.CentroDistribuicaoPedido;
import meli.pedidos.api.itempedido.ItemPedidoCentroDistribuicao;
import meli.pedidos.api.pedido.DadosPedidoProcessa;
import meli.pedidos.api.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final ProdutoRepository produtoRepository;
    private final CentroDistribuicaoService centroDistribuicaoService;

    public List<ItemPedidoCentroDistribuicao> processarPedido(DadosPedidoProcessa dados) {
        return dados.itens().stream()
                .map(item -> montarItemComCds(item.produtoId()))
                .toList();
    }

    private ItemPedidoCentroDistribuicao montarItemComCds(Long produtoId) {
        var produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado: " + produtoId));

        var cds = centroDistribuicaoService.consultaCentroDistribuicao(produto.getId());

        var cdsDTO = cds.stream()
                .map(cd -> new CentroDistribuicaoPedido(cd.nome(), cd.estoque()))
                .toList();

        return new ItemPedidoCentroDistribuicao(produto.getId(), produto.getNome(), cdsDTO);
    }
}