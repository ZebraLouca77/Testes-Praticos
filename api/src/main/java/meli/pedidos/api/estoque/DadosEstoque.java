package meli.pedidos.api.estoque;

import meli.pedidos.api.centrodistribuicao.DadosCentroDistribuicao;
import meli.pedidos.api.produto.DadosProduto;

public record DadosEstoque(
        DadosCentroDistribuicao cd_id,
        DadosProduto produto_id,
        Integer quantidade) {
}
