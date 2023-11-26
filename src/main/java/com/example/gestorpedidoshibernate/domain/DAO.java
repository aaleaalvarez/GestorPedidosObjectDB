package com.example.gestorpedidoshibernate.domain;

import com.example.gestorpedidoshibernate.domain.Usuario.Usuario;

import java.util.ArrayList;

public interface DAO<T> {

    public ArrayList<T> getAll();

    public T get(int id);

    Usuario get(Long id);

    public T save(T data);

    public void update(T data);

    public void delete(T data);

}
