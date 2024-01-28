package com.example.gestorpedidoshibernate.domain.Producto;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Clase que representa la entidad Producto.
 * Esta clase se utiliza para mapear objetos Producto a registros en la base de datos.
 * Utiliza la anotación Lombok para generar automáticamente métodos como getters, setters y toString.
 *
 * @author Alejandro Álvarez Mérida
 * @version 26-11-2023
 */
@Data
@Entity
@Table(name = "Producto")
public class Producto implements Serializable {
    /**
     * Identificador único del producto.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Nombre del producto.
     */
    @Column(name = "nombre")
    private String nombre;
    /**
     * Precio del producto.
     */
    @Column(name = "precio")
    private double precio;
    /**
     * Cantidad disponible del producto en el inventario.
     */
    @Column(name = "cantidad_disponible")
    private int cantidadDisponible;

    public Producto(Long id, String nombre, double precio, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadDisponible = cantidad;
    }

    /**
     * Devuelve una representación en cadena del objeto Producto.
     *
     * @return Una cadena que representa el objeto Producto.
     */
    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", cantidadDisponible=" + cantidadDisponible +
                '}';
    }
}

