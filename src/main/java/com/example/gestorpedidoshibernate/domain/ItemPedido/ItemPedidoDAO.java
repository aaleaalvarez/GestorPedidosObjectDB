package com.example.gestorpedidoshibernate.domain.ItemPedido;

import com.example.gestorpedidoshibernate.domain.DAO;
import com.example.gestorpedidoshibernate.domain.HibernateUtil;
import com.example.gestorpedidoshibernate.domain.Pedido.Pedido;
import com.example.gestorpedidoshibernate.domain.Usuario.Usuario;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz DAO para la entidad ItemPedido.
 * Proporciona métodos para acceder y manipular datos relacionados con los elementos del pedido en la base de datos.
 *
 * @author Alejandro Álvarez Mérida
 * @version 26-11-2023
 */
public class ItemPedidoDAO implements DAO<ItemPedido> {

    /**
     * Obtiene todos los elementos del pedido.
     *
     * @return Una lista de todos los elementos del pedido.
     */
    @Override
    public ArrayList<ItemPedido> getAll() {
        return null;
    }

    /**
     * Encuentra todos los elementos del pedido asociados a un pedido específico mediante su código.
     *
     * @param codigoPedido El código del pedido para el cual se buscan los elementos.
     * @return Una lista de elementos del pedido asociados al pedido con el código dado.
     */
    public List<ItemPedido> findItemsByPedidoCodigo(String codigoPedido) {
        List<ItemPedido> results = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Query<ItemPedido> query = session.createQuery(
                    "SELECT ip FROM ItemPedido ip JOIN ip.pedido p JOIN ip.producto prod WHERE p.codigo = :codigoPedido", ItemPedido.class);
            query.setParameter("codigoPedido", codigoPedido);
            results = query.getResultList();

            transaction.commit();
        } catch (Exception e) {
            // Maneja la excepción de manera adecuada para tu aplicación
            e.printStackTrace();
        }

        return results;
    }

    /**
     * Obtiene un elemento del pedido por su identificador.
     *
     * @param id El identificador del elemento del pedido.
     * @return El elemento del pedido con el identificador dado.
     */
    @Override
    public ItemPedido get(int id) {
        return null;
    }

    /**
     * Obtiene un usuario por su identificador.
     *
     * @param id El identificador del usuario.
     * @return El usuario con el identificador dado.
     */
    @Override
    public Usuario get(Long id) {
        return null;
    }

    /**
     * Guarda un nuevo elemento del pedido o actualiza uno existente en la base de datos.
     *
     * @param data El elemento del pedido a guardar.
     * @return El elemento del pedido guardado.
     */
    @Override
    public ItemPedido save(ItemPedido data) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(data);

            Pedido pedido = data.getPedido();
            if (pedido != null) {
                Query<Double> query = session.createQuery("SELECT SUM(ip.cantidad * prod.precio) FROM ItemPedido ip " + "JOIN ip.producto prod WHERE ip.pedido.id = :pedidoId", Double.class);
                query.setParameter("pedidoId", pedido.getId());
                Double nuevoTotal = query.getSingleResult();

                pedido.setTotal(nuevoTotal != null ? nuevoTotal : 0.0);
                session.update(pedido);
            }
            transaction.commit();
            return data;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    /**
     * Actualiza un elemento del pedido en la base de datos.
     *
     * @param data El elemento del pedido a actualizar.
     */
    @Override
    public void update(ItemPedido data) {
    }

    /**
     * Elimina un elemento del pedido de la base de datos.
     *
     * @param data El elemento del pedido a eliminar.
     */
    @Override
    public void delete(ItemPedido data) {
    }

    /**
     * Elimina un elemento del pedido de la base de datos y actualiza el total del pedido asociado.
     *
     * @param item El elemento del pedido a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     */
    public boolean remove(ItemPedido item) {
        boolean salida = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            ItemPedido itemRemove = session.get(ItemPedido.class, item.getId());
            if (itemRemove != null) {
                Pedido pedido = itemRemove.getPedido();
                session.remove(itemRemove);

                session.update(pedido);
                salida = true;
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return salida;
    }
}
