package com.example.gestorpedidoshibernate.domain;

import lombok.Getter;
import lombok.extern.java.Log;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Clase utilitaria que proporciona una instancia de EntityManagerFactory para interactuar con la base de datos ObjectDB.
 *
 * @author Alejandro Álvarez Mérida
 * @version 28-01-2024
 */
@Log
public class ObjectDBUtil {
    /**
     * Instancia única de EntityManagerFactory para interactuar con la base de datos ObjectDB.
     */
    @Getter
    private final static EntityManagerFactory entityManagerFactory;

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("data.odb");
    }
}
