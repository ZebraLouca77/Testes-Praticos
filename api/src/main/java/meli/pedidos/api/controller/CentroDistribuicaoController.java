package meli.pedidos.api.controller;

import meli.pedidos.api.centrodistribuicao.DadosConsultaCentroDistribuicao;
import org.springframework.beans.factory.annotation.Autowired;
import meli.pedidos.api.service.CentroDistribuicaoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/centrodistribuicao")
public class CentroDistribuicaoController {

    // Injeta a service responsável por consultar CDs
    private final CentroDistribuicaoService centroDistribuicaoService;

    // Construtor com injeção de dependência explícita (boa prática moderna)
    public CentroDistribuicaoController(CentroDistribuicaoService centroDistribuicaoService) {
        this.centroDistribuicaoService = centroDistribuicaoService;
    }

    // Endpoint GET que recebe um produtoId e retorna os CDs que têm esse produto em estoque
    @GetMapping
    public ResponseEntity<?> consultaCentroDistribuicao(@RequestParam Long produtoId) {
        try {
            // Chama a service para consultar os CDs com o produto disponível
            List<DadosConsultaCentroDistribuicao> cds = centroDistribuicaoService.consultaCentroDistribuicao(produtoId);
            return ResponseEntity.ok(cds); // Retorna a lista de CDs com status 200 OK
        } catch (IllegalStateException e) {
            // Caso não haja CDs disponíveis, retorna 404 com mensagem
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensagem", e.getMessage()));
        }
    }
}
