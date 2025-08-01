package meli.pedidos.api.itempedido;

import meli.pedidos.api.centrodistribuicao.CentroDistribuicaoPedido;

import java.util.List;

/**
 * DTO de resposta com os CDs disponíveis para um determinado item do pedido.
 */
public record ItemPedidoCentroDistribuicao(
        Long produtoId,
        String nomeProduto,
        List<CentroDistribuicaoPedido> cdsDisponiveis
) {}