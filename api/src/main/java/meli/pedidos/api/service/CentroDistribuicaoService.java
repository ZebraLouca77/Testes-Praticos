package meli.pedidos.api.service;

import meli.pedidos.api.centrodistribuicao.DadosConsultaCentroDistribuicao;
import meli.pedidos.api.repository.CentroDistribuicaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CentroDistribuicaoService {

    private CentroDistribuicaoRepository centroDistribuicaoRepository;

    public CentroDistribuicaoService(CentroDistribuicaoRepository centroDistribuicaoRepository) {
        this.centroDistribuicaoRepository = centroDistribuicaoRepository;
    }

    @Transactional(readOnly = true)
    public List<DadosConsultaCentroDistribuicao> consultaCentroDistribuicao(Long produtoId) {
        List<DadosConsultaCentroDistribuicao> resultado =
                centroDistribuicaoRepository.listarCentroDistribuicaoComItens(produtoId);

        return resultado;
    }
}

