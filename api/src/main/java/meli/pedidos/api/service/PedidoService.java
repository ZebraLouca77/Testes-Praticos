package meli.pedidos.api.service;

import meli.pedidos.api.centrodistribuicao.CentroDistribuicaoPedido;
import jakarta.persistence.EntityNotFoundException;
import meli.pedidos.api.repository.ProdutoRepository;
import meli.pedidos.api.pedido.DadosPedidoProcessa;
import meli.pedidos.api.itempedido.ItemPedidoCentroDistribuicao;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final ProdutoRepository produtoRepository;
    private final meli.pedidos.api.service.CentroDistribuicaoService centroDistribuicaoService;

    public List<ItemPedidoCentroDistribuicao> processarPedido(DadosPedidoProcessa dados) {
        return dados.itens().stream()
                .map(item -> {
                    var produto = produtoRepository.findById(item.produtoId())
                            .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado: " + item.produtoId()));

                    var cds = centroDistribuicaoService.consultaCentroDistribuicao(produto.getId());

                    var cdsDTO = cds.stream()
                            .map(cd -> new CentroDistribuicaoPedido(cd.nome(), cd.estoque()))
                            .toList();

                    return new ItemPedidoCentroDistribuicao(produto.getId(), produto.getNome(), cdsDTO);
                })
                .toList();
    }
}