package meli.pedidos.api.pedido;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import meli.pedidos.api.itempedido.DadosItemProcessa;

import java.util.List;

public record DadosPedidoProcessa(
        @NotNull
        @Size(min = 1, max = 100, message = "O pedido deve conter entre 1 e 100 itens.")
        List<@Valid DadosItemProcessa> itens
) {
}
