package meli.pedidos.api.centrodistribuicao;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "centros_distribuicao")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class CentroDistribuicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private Status status;

    public CentroDistribuicao(DadosCentroDistribuicao dados) {
        this.nome = dados.nome();
        this.status = dados.status();
    }
}