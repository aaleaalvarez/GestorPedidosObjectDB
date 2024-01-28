package com.example.gestorpedidoshibernate.domain.Usuario;

import com.example.gestorpedidoshibernate.domain.Pedido.Pedido;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa a un usuario en el sistema.
 *
 * @author Alejandro Álvarez Mérida
 * @version 26-11-2023
 */
@Entity
@Data
@Table(name = "Usuario")
public class Usuario implements Serializable {
    /**
     * Identificador único del usuario.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Nombre del usuario.
     */
    @Column(name = "nombre")
    private String nombre;
    /**
     * Contraseña del usuario.
     */
    @Column(name = "contraseña")
    private String contraseña;
    /**
     * Dirección de correo electrónico del usuario.
     */
    @Column(name = "email")
    private String email;
    /**
     * Lista de pedidos asociados al usuario.
     */
    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    private List<Pedido> pedidos = new ArrayList<>(0);

    public Usuario(Long id, String nombre, String contraseña, String email) {
        this.id = id;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.email = email;
    }

    /**
     * Devuelve una representación en cadena del objeto Usuario.
     *
     * @return Una cadena que representa el objeto Usuario.
     */
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", contraseña='" + contraseña + '\'' +
                ", email='" + email + '\'' +
                //      ", pedidos=" + pedidos +
                '}';
    }
}

