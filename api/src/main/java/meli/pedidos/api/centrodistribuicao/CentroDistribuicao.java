package meli.pedidos.api.centrodistribuicao;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import lombok.Getter;

@Table(name = "centros_distribuicao")
@Entity(name = "CentroDistribuicao")
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
