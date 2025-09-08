package meli.pedidos.api.service;

import meli.pedidos.api.centrodistribuicao.CentroDistribuicaoPedido;
import meli.pedidos.api.centrodistribuicao.DadosConsultaCentroDistribuicao;
import meli.pedidos.api.itempedido.DadosItemProcessa;
import meli.pedidos.api.pedido.DadosPedidoProcessa;
import meli.pedidos.api.produto.Produto;
import meli.pedidos.api.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private CentroDistribuicaoService centroDistribuicaoService;

    @InjectMocks
    private PedidoService pedidoService;

    @Test
    void shouldProcessOrderWithInStockAndOutOfStockItems() {
        var produto1 = new Produto(1L, "Notebook", "SKU-001");
        var produto2 = new Produto(2L, "Mouse", "SKU-002");

        var dadosPedido = new DadosPedidoProcessa(List.of(
                new DadosItemProcessa(1L),
                new DadosItemProcessa(2L)
        ));

        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto1));
        when(produtoRepository.findById(2L)).thenReturn(Optional.of(produto2));

        when(centroDistribuicaoService.consultaCentroDistribuicao(1L)).thenReturn(List.of(
                new DadosConsultaCentroDistribuicao("CD-SP", 10)
        ));
        when(centroDistribuicaoService.consultaCentroDistribuicao(2L)).thenReturn(Collections.emptyList());

        var resultado = pedidoService.processarPedido(dadosPedido);

        assertEquals(2, resultado.size());

        var item1 = resultado.get(0);
        assertEquals(1L, item1.produtoId());
        assertEquals("Notebook", item1.nomeProduto());
        assertEquals(1, item1.cds().size());
        assertEquals("CD-SP", item1.cds().get(0).nome());

        var item2 = resultado.get(1);
        assertEquals(2L, item2.produtoId());
        assertEquals("Mouse", item2.nomeProduto());
        assertEquals(0, item2.cds().size());
    }
}
