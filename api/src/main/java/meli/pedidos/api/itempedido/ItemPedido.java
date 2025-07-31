package meli.pedidos.api.itempedido;

import jakarta.persistence.*;
import lombok.*;
import meli.pedidos.api.pedido.Pedido;
import meli.pedidos.api.produto.Produto;
import meli.pedidos.api.centrodistribuicao.CentroDistribuicao;

@Entity(name = "ItemPedido")
@Table(name = "itens_pedido")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "cd_id")
    private CentroDistribuicao cd;

    private Integer quantidade;
}