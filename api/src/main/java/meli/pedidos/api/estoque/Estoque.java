package meli.pedidos.api.estoque;

import meli.pedidos.api.centrodistribuicao.CentroDistribuicao;
import com.fasterxml.jackson.annotation.JsonProperty;
import meli.pedidos.api.produto.Produto;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import jakarta.persistence.*;
import lombok.Getter;

@Table(name = "estoque_cd")
@Entity(name = "Estoque")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cd_id")
    private CentroDistribuicao cd;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @JsonProperty("quantidade")
    private Integer quantidade;
}