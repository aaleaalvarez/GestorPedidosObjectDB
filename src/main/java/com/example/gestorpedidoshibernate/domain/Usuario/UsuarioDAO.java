package com.example.gestorpedidoshibernate.domain.Usuario;

import com.example.gestorpedidoshibernate.domain.DAO;
import com.example.gestorpedidoshibernate.domain.ItemPedido.ItemPedido;
import com.example.gestorpedidoshibernate.domain.ObjectDBUtil;
import com.example.gestorpedidoshibernate.domain.Producto.Producto;
import lombok.extern.java.Log;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

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
    public List<Producto> getAll() {
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
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        Usuario usuario = null;
        try {
            usuario = em.find(Usuario.class, id);
        } finally {
            em.close();
        }
        return usuario;
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

    @Override
    public boolean remove(ItemPedido item) {
        return false;
    }

    /**
     * Valida las credenciales de un usuario en la base de datos.
     *
     * @param username El nombre de usuario.
     * @param password La contraseña del usuario.
     * @return El usuario si las credenciales son válidas, de lo contrario, null.
     */
    public Usuario validateUser(String username, String password) {
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        Usuario result = null;
        try {
            TypedQuery<Usuario> q = em.createQuery("SELECT u FROM Usuario u WHERE u.nombre = :u AND u.contraseña = :p", Usuario.class);
            q.setParameter("u", username);
            q.setParameter("p", password);
            result = q.getSingleResult();
        } catch (Exception e) {
            System.out.println("Error al validar el usuario: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
        }
        return result;
    }

    public void saveAll(List<Usuario> usuarios) {
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            for (Usuario usuario : usuarios) {
                em.persist(usuario);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
