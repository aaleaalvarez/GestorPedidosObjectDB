package com.example.gestorpedidoshibernate.domain.ItemPedido;

import com.example.gestorpedidoshibernate.domain.Pedido.Pedido;
import com.example.gestorpedidoshibernate.domain.Producto.Producto;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

/**
 * Clase que representa un elemento del pedido.
 * Cada elemento del pedido contiene información sobre el pedido al que pertenece, la cantidad del producto y el producto asociado.
 *
 * @author Alejandro Álvarez Mérida
 * @version 26-11-2023
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Item_pedido")
public class ItemPedido implements Serializable {
    /**
     * Identificador único del elemento del pedido.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Identificador del pedido al que pertenece el elemento.
     */
    @ManyToOne
    @JoinColumn(name = "pedido_cod", referencedColumnName = "codigo")
    private Pedido pedido; // Cambio de nombre
    /**
     * Cantidad del producto asociado al elemento del pedido.
     */
    @Column(name = "cantidad")
    private int cantidad;
    /**
     * Identificador del producto asociado al elemento del pedido.
     */
    @OneToOne
    @JoinColumn(name = "producto_id", referencedColumnName = "id")
    private Producto producto; // Cambio de nombre

    /**
     * Representación en cadena del elemento del pedido.
     *
     * @return Cadena que representa el elemento del pedido.
     */
    @Override
    public String toString() {
        return "ItemPedido{" +
                "id=" + id +
                ", pedido=" + pedido +
                ", cantidad=" + cantidad +
                ", producto=" + producto +
                '}';
    }
}
