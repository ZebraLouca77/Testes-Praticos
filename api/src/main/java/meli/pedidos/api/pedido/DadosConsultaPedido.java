package meli.pedidos.api.pedido;

import java.util.List;

public record DadosConsultaPedido(
        Long id,
        String status,
        List<ItemDTO> itens
) {
    public record ItemDTO(
            String nomeProduto,
            String nomeCentroDistribuicao
    ) {}
}
