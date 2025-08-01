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

    // Serviço responsável por processar o pedido
    private final PedidoService pedidoService;
    // Serviço que consulta os itens e os CDs de um pedido
    private final ItemPedidoService itemPedidoService;

    // Endpoint POST que recebe os dados do pedido para ser processado
    @PostMapping("/processar")
    public ResponseEntity<List<ItemPedidoCentroDistribuicao>> processarPedido(@RequestBody DadosPedidoProcessa dados) {
        // Chama a service para processar o pedido e retorna os itens com os CDs disponíveis
        var resultado = pedidoService.processarPedido(dados);
        return ResponseEntity.ok(resultado);
    }

    // Endpoint GET que consulta os itens de um pedido já existente junto com os CDs disponíveis
    @GetMapping("/{id}/itens-cds")
    public ResponseEntity<List<ItemPedidoCentroDistribuicao>> consultarItensComCds(@PathVariable Long id) {
        // Chama a service para buscar os itens do pedido com os CDs vinculados
        var resposta = itemPedidoService.consultarItensECdsDoPedido(id);
        return ResponseEntity.ok(resposta);
    }
}

