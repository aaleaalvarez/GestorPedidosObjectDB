package com.example.gestorpedidoshibernate.domain.Producto;

import com.example.gestorpedidoshibernate.domain.DAO;
import com.example.gestorpedidoshibernate.domain.ItemPedido.ItemPedido;
import com.example.gestorpedidoshibernate.domain.ObjectDBUtil;
import com.example.gestorpedidoshibernate.domain.Usuario.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que proporciona métodos de acceso a datos para la entidad Producto.
 * Implementa la interfaz DAO para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en la base de datos.
 *
 * @author Alejandro Álvarez Mérida
 * @version 26-11-2023
 */
public class ProductoDAO implements DAO<Producto> {

    /**
     * Recupera todos los productos de la base de datos.
     *
     * @return Una lista de todos los productos.
     */
    @Override
    public List<Producto> getAll() {
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        List<Producto> productos;
        try {
            TypedQuery<Producto> query = em.createQuery("SELECT p FROM Producto p", Producto.class);
            productos = query.getResultList();
        } finally {
            em.close();
        }
        return productos;
    }

    /**
     * Recupera un producto por su identificador de la base de datos.
     *
     * @param id El identificador del producto a recuperar.
     * @return El producto encontrado o null si no se encuentra.
     */
    @Override
    public Producto get(int id) {
        return null;
    }

    /**
     * Recupera un producto por su identificador de la base de datos.
     *
     * @param id El identificador del producto a recuperar.
     * @return El producto encontrado o null si no se encuentra.
     */
    @Override
    public Usuario get(Long id) {
        return null;
    }

    /**
     * Guarda un nuevo producto en la base de datos.
     *
     * @param data El producto a guardar.
     * @return El producto guardado.
     */
    @Override
    public Producto save(Producto data) {
        return null;
    }

    /**
     * Actualiza la información de un producto en la base de datos.
     *
     * @param data El producto con la información actualizada.
     */
    @Override
    public void update(Producto data) {

    }

    /**
     * Elimina un producto de la base de datos.
     *
     * @param data El producto a eliminar.
     */
    @Override
    public void delete(Producto data) {

    }

    @Override
    public boolean remove(ItemPedido item) {
        return false;
    }

    /**
     * Busca un producto por su nombre en la base de datos.
     *
     * @param nombre El nombre del producto a buscar.
     * @return El producto encontrado o null si no se encuentra.
     */
    public List<Producto> findByName(String nombre) {
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        List<Producto> productos = new ArrayList<>();
        try {
            TypedQuery<Producto> query = em.createQuery("SELECT p FROM Producto p WHERE p.nombre = :nombre", Producto.class);
            query.setParameter("nombre", nombre);
            productos = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return productos;
    }

    public void saveOrUpdate(Producto producto) {
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
            Producto existingProducto = em.find(Producto.class, producto.getId());
            if (existingProducto != null) {
                // Producto existe, actualiza sus datos
                existingProducto.setNombre(producto.getNombre());
                existingProducto.setPrecio(producto.getPrecio());
                existingProducto.setCantidadDisponible(producto.getCantidadDisponible());
                // Cualquier otra propiedad que necesites actualizar
            } else {
                // Producto no existe, crea uno nuevo
                em.persist(producto);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            // Manejo de excepciones
        } finally {
            em.close();
        }
    }
}
