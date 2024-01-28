package com.example.gestorpedidoshibernate;

import com.example.gestorpedidoshibernate.domain.Usuario.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Test {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("data.odb"); // Reemplaza "tuUnidadPersistencia" con el nombre de tu unidad de persistencia
        EntityManager em = emf.createEntityManager();

        try {
            // Realiza una consulta de prueba aquí
            // Por ejemplo, puedes intentar recuperar un usuario
            Usuario usuario = em.find(Usuario.class, 1L);

            if (usuario != null) {
                System.out.println("Conexión a la base de datos exitosa.");
            } else {
                System.out.println("No se pudo encontrar el usuario en la base de datos.");
            }
        } catch (Exception e) {
            System.out.println("Error de conexión a la base de datos: " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }
}
