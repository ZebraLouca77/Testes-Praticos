package meli.pedidos.api.pedido;

import jakarta.persistence.*;
import lombok.*;
import meli.pedidos.api.cliente.Cliente;
import meli.pedidos.api.itempedido.ItemPedido;

import java.util.List;

@Entity
@Table(name = "pedidos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @OneToMany(
            mappedBy = "pedido",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<ItemPedido> itens;

    public Pedido(DadosPedido dados) {
        this.cliente = new Cliente(dados.cliente_id()); // Cliente precisa ter esse construtor
        this.status = dados.status();
    }
}