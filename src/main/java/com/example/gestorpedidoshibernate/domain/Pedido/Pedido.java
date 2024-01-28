package com.example.gestorpedidoshibernate.domain.Pedido;

import com.example.gestorpedidoshibernate.domain.ItemPedido.ItemPedido;
import com.example.gestorpedidoshibernate.domain.Usuario.Usuario;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "fecha")
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @Column(name = "total")
    private double total;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> items = new ArrayList<>();

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", fecha=" + fecha +
                ", total=" + total +
                '}';
    }

    public List<ItemPedido> getItems() {
        return items;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codigo, fecha, total);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pedido pedido = (Pedido) o;

        return id != null && id.equals(pedido.id) &&
                Objects.equals(codigo, pedido.codigo) &&
                Objects.equals(fecha, pedido.fecha) &&
                Objects.equals(total, pedido.total);
    }
}
