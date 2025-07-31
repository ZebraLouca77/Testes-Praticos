package meli.pedidos.api.itempedido;

import meli.pedidos.api.centrodistribuicao.CentroDistribuicaoPedido;

import java.util.List;

public record ItemPedidoCentroDistribuicao(
        Long produtoId,
        String nomeProduto,
        List<CentroDistribuicaoPedido> cdsDisponiveis
) {}
