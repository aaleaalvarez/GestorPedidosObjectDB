package com.example.gestorpedidoshibernate.domain.Producto;

import com.example.gestorpedidoshibernate.domain.DAO;
import com.example.gestorpedidoshibernate.domain.HibernateUtil;
import com.example.gestorpedidoshibernate.domain.Usuario.Usuario;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;

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
    public ArrayList<Producto> getAll() {
        Session session = null;
        Transaction tx = null;
        ArrayList<Producto> productos = new ArrayList<>();

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            Query<Producto> query = session.createQuery("FROM Producto", Producto.class);
            productos = new ArrayList<>(query.list());

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
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

    /**
     * Busca un producto por su nombre en la base de datos.
     *
     * @param nombre El nombre del producto a buscar.
     * @return El producto encontrado o null si no se encuentra.
     */
    public Producto findByName(String nombre) {
        Producto producto = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Producto> query = session.createQuery("FROM Producto WHERE nombre =: nombre", Producto.class);
            query.setParameter("nombre", nombre);
            producto = query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return producto;
    }
}
