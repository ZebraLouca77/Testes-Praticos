package meli.pedidos.api.itempedido;

import jakarta.validation.constraints.NotNull;

public record DadosItemProcessa(
        @NotNull Long produtoId
) {
}
