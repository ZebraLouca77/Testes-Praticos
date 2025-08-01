package meli.pedidos.api.estoque;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import meli.pedidos.api.centrodistribuicao.CentroDistribuicao;
import meli.pedidos.api.produto.Produto;

@Entity
@Table(name = "estoque_cd")
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