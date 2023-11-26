package com.example.gestorpedidoshibernate.domain;

import lombok.extern.java.Log;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/**
 * Clase de utilidad para la gestión de la SessionFactory en Hibernate.
 * Proporciona un único punto de acceso para obtener la SessionFactory, que es crucial para interactuar con la base de datos.
 *
 * @author Alejandro Álvarez Mérida
 * @version 26-11-2023
 */
@Log
public class HibernateUtil {
    private static SessionFactory sf = null;

    static {
        try {
            Configuration cfg = new Configuration();
            cfg.configure();

            sf = cfg.buildSessionFactory();
            log.info("SessionFactory creada con éxito!");
        } catch (Exception ex) {
            log.severe("Error al crear SessionFactory(): " + ex.getMessage());
            ex.printStackTrace();  // Agregar esto para imprimir la traza de la excepción
        }
    }

    /**
     * Obtiene la instancia única de SessionFactory.
     *
     * @return La instancia de SessionFactory.
     */
    public static SessionFactory getSessionFactory() {
        return sf;
    }
}