package com.example.gestorpedidoshibernate;

import com.example.gestorpedidoshibernate.domain.Pedido.Pedido;
import com.example.gestorpedidoshibernate.domain.Usuario.Usuario;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase de utilidad para gestionar la información de sesión de la aplicación.
 * Almacena información sobre el usuario actual y el pedido actual.
 * Utiliza las anotaciones de Lombok para generar automáticamente los métodos getter y setter.
 *
 * @author Alejandro Álvarez Mérida
 * @version 26-11-2023
 */
public class Session {
    /**
     * Usuario actual en sesión.
     */
    @Getter
    @Setter
    private static Usuario currentUser;
    /**
     * Pedido actual en sesión.
     */
    @Getter
    @Setter
    private static Pedido currentItemPedido; // Agregamos una variable para almacenar el ItemPedido

}
