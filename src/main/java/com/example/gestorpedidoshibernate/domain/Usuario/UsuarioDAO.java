package com.example.gestorpedidoshibernate.domain.Usuario;

import com.example.gestorpedidoshibernate.domain.DAO;
import com.example.gestorpedidoshibernate.domain.HibernateUtil;
import lombok.extern.java.Log;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;

/**
 * Clase que proporciona métodos de acceso a datos para la entidad Usuario.
 * Implementa la interfaz DAO para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en la base de datos.
 *
 * @author Alejandro Álvarez Mérida
 * @version 26-11-2023
 */
@Log
public class UsuarioDAO implements DAO<Usuario> {

    /**
     * Recupera todos los usuarios de la base de datos (no implementado).
     *
     * @return Una lista de todos los usuarios.
     */
    @Override
    public ArrayList<Usuario> getAll() {
        return null;
    }

    /**
     * Obtiene un usuario por su identificador (no implementado).
     *
     * @param id El identificador del usuario.
     * @return El usuario correspondiente al identificador.
     */
    @Override
    public Usuario get(int id) {
        return null;
    }

    /**
     * Obtiene un usuario por su identificador.
     *
     * @param id El identificador del usuario.
     * @return El usuario correspondiente al identificador.
     */
    @Override
    public Usuario get(Long id) {
        var salida = new Usuario();
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            salida = s.get(Usuario.class, id);
        }
        return salida;
    }

    /**
     * Guarda un nuevo usuario en la base de datos (no implementado).
     *
     * @param data El usuario a guardar.
     * @return El usuario guardado.
     */
    @Override
    public Usuario save(Usuario data) {
        return null;
    }

    /**
     * Actualiza la información de un usuario en la base de datos (no implementado).
     *
     * @param data El usuario a actualizar.
     */
    @Override
    public void update(Usuario data) {

    }

    /**
     * Elimina un usuario de la base de datos (no implementado).
     *
     * @param data El usuario a eliminar.
     */
    @Override
    public void delete(Usuario data) {

    }

    /**
     * Valida las credenciales de un usuario en la base de datos.
     *
     * @param username El nombre de usuario.
     * @param password La contraseña del usuario.
     * @return El usuario si las credenciales son válidas, de lo contrario, null.
     */
    public Usuario validateUser(String username, String password) {
        Usuario result = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Usuario> q = session.createQuery("from Usuario where nombre=:u and contraseña=:p", Usuario.class);
            q.setParameter("u", username);
            q.setParameter("p", password);

            try {
                result = q.getSingleResult();
            } catch (Exception e) {
                log.severe("Error al abrir la sesión: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return result;
    }
}
