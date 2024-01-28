package com.example.gestorpedidoshibernate.domain;

import com.example.gestorpedidoshibernate.domain.ItemPedido.ItemPedido;
import com.example.gestorpedidoshibernate.domain.Pedido.Pedido;
import com.example.gestorpedidoshibernate.domain.Producto.Producto;
import com.example.gestorpedidoshibernate.domain.Usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class Data {
    public static List<Usuario> getUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario(1L, "Juan Perez", "clave123", "juan.perez@example.com"));
        usuarios.add(new Usuario(2L, "María Rodríguez", "password456", "maria.rodriguez@example.com"));
        usuarios.add(new Usuario(3L, "Carlos Gómez", "qwerty789", "carlos.gomez@example.com"));
        usuarios.add(new Usuario(4L, "Laura Martínez", "abcdef012", "laura.martinez@example.com"));
        return usuarios;
    }

    public static List<Producto> getProductos() {
        List<Producto> productos = new ArrayList<>();
        productos.add(new Producto(1L, "Laptop Lenovo ThinkPad X1 Carbon", 1699.99, 50));
        productos.add(new Producto (2L, "Smartphone Samsung Galaxy S21", 999.99, 100));
        productos.add(new Producto(3L, "TV Sony Bravia OLED A8H", 1999.99, 30));
        productos.add(new Producto(4L, "Auriculares Bose QuietComfort 35 II", 299.99, 80));
        productos.add(new Producto(5L, "Cámara Canon EOS R5", 3799.99, 20));
        productos.add(new Producto(6L, "Tableta Apple iPad Pro", 799.99, 60));
        productos.add(new Producto (7L, "Refrigerador LG InstaView Door-in-Door", 2499.99, 10));
        productos.add(new Producto(8L, "Consola de Videojuegos Sony PlayStation 5", 499.99, 50));
        productos.add(new Producto (9L, "Drone DJI Mavic Air 2", 799.99, 40));
        productos.add(new Producto(10L, "Impresora HP LaserJet Pro MFP M281fdw", 399.99, 25));
        return productos;
    }

    public static List<Pedido> getPedidos() {
        List<Pedido> pedidos = new ArrayList<>();
        return pedidos;
    }

    public static List<ItemPedido> getItemsPedidos() {
        List<ItemPedido> itemsPedidos = new ArrayList<>();
        return itemsPedidos;
    }
}
