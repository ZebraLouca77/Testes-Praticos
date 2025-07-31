package meli.pedidos.api.service;

import meli.pedidos.api.centrodistribuicao.DadosConsultaCentroDistribuicao;
import meli.pedidos.api.repository.CentroDistribuicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CentroDistribuicaoService {

    @Autowired
    private CentroDistribuicaoRepository consultarCentroDistribuicaoRepository;

    public List<DadosConsultaCentroDistribuicao> consultaCentroDistribuicao(Long produtoId) {
        List<DadosConsultaCentroDistribuicao> resultado =
                consultarCentroDistribuicaoRepository.listarCentroDistribuicaoComItens(produtoId);

        if (resultado.isEmpty()) {
            throw new IllegalStateException("Nenhum centro de distribuição disponível com estoque para o produto ID: " + produtoId);
        }

        return resultado;
    }
}

