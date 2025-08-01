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

    private final CentroDistribuicaoService centroDistribuicaoService;

    public CentroDistribuicaoController(CentroDistribuicaoService centroDistribuicaoService) {
        this.centroDistribuicaoService = centroDistribuicaoService;
    }

    @GetMapping
    public ResponseEntity<?> consultaCentroDistribuicao(@RequestParam Long produtoId) {
        try {
            List<DadosConsultaCentroDistribuicao> cds = centroDistribuicaoService.consultaCentroDistribuicao(produtoId);
            return ResponseEntity.ok(cds);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensagem", e.getMessage()));
        }
    }
}
