package meli.pedidos.api.controller;

import meli.pedidos.api.pedido.DadosPedidoProcessa;
import meli.pedidos.api.itempedido.ItemPedidoCentroDistribuicao;
import meli.pedidos.api.service.ItemPedidoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import meli.pedidos.api.service.PedidoService;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;
    private final ItemPedidoService itemPedidoService;

    @PostMapping("/processar")
    public ResponseEntity<List<ItemPedidoCentroDistribuicao>> processarPedido(@RequestBody DadosPedidoProcessa dados) {
        var resultado = pedidoService.processarPedido(dados);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/{id}/itens-cds")
    public ResponseEntity<List<ItemPedidoCentroDistribuicao>> consultarItensComCds(@PathVariable Long id) {
        var resposta = itemPedidoService.consultarItensECdsDoPedido(id);
        return ResponseEntity.ok(resposta);
    }
}

