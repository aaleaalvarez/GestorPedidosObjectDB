package com.example.gestorpedidoshibernate.domain;

import com.example.gestorpedidoshibernate.domain.ItemPedido.ItemPedido;
import com.example.gestorpedidoshibernate.domain.Producto.Producto;
import com.example.gestorpedidoshibernate.domain.Usuario.Usuario;

import java.util.List;

public interface DAO<T> {

    public List<Producto> getAll();

    public T get(int id);

    Usuario get(Long id);

    public T save(T data);

    public void update(T data);

    public void delete(T data);

    boolean remove(ItemPedido item);
}
