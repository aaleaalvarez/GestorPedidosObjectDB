package com.example.gestorpedidoshibernate.domain.Pedido;

import com.example.gestorpedidoshibernate.domain.ItemPedido.ItemPedido;
import com.example.gestorpedidoshibernate.domain.Usuario.Usuario;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Clase que representa un pedido en el sistema.
 * Contiene información sobre el identificador, código, fecha, usuario asociado y total del pedido.
 * También proporciona una lista de elementos del pedido asociados.
 * Implementa la interfaz Serializable para admitir la serialización.
 *
 * @author Alejandro Álvarez Mérida
 * @version 26-11-2023
 */
@Data
@Entity
@Table(name = "Pedido")
public class Pedido implements Serializable {
    /**
     * Identificador único del pedido.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Código del pedido.
     */
    @Column(name = "codigo")
    private String codigo;
    /**
     * Fecha en la que se realizó el pedido.
     */
    @Column(name = "fecha")
    private Date fecha;

    /**
     * Usuario asociado al pedido.
     */
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    /**
     * Total del pedido.
     */
    @Column(name = "total")
    private double total;

    /**
     * Representación de cadena del objeto Pedido.
     *
     * @return Una cadena que representa el objeto Pedido.
     */
    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", fecha=" + fecha +
                //     ", usuario=" + usuario +
                ", total=" + total +
                '}';
    }

    /**
     * Obtiene la lista de elementos del pedido asociados.
     *
     * @return La lista de elementos del pedido asociados.
     */
    public List<ItemPedido> getItems() {
        return null;
    }
}
