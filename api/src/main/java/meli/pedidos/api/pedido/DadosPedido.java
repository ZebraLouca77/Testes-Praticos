package meli.pedidos.api.pedido;

import com.fasterxml.jackson.annotation.JsonProperty;
import meli.pedidos.api.cliente.DadosCliente;

public record DadosPedido(

        @JsonProperty("cliente_id")
        DadosCliente cliente_id,

        @JsonProperty("status")
        Status status) {
}

