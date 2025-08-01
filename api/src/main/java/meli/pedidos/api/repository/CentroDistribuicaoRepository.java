package meli.pedidos.api.repository;

import meli.pedidos.api.centrodistribuicao.CentroDistribuicao;
import meli.pedidos.api.centrodistribuicao.DadosConsultaCentroDistribuicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CentroDistribuicaoRepository extends JpaRepository<CentroDistribuicao, Long> {

    @Query("""
        SELECT new meli.pedidos.api.centrodistribuicao.DadosConsultaCentroDistribuicao(
            cd.nome,
            e.quantidade
        )
        FROM Estoque e
        JOIN e.cd cd
        WHERE cd.status = 'ATIVO'
          AND e.produto.id = :produtoId
          AND e.quantidade > 0
        ORDER BY e.quantidade DESC
    """)
    List<DadosConsultaCentroDistribuicao> listarCentroDistribuicaoComItens(@Param("produtoId") Long produtoId);
}