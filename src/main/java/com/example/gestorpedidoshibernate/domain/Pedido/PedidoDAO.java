package com.example.gestorpedidoshibernate.domain.Pedido;

import com.example.gestorpedidoshibernate.domain.DAO;
import com.example.gestorpedidoshibernate.domain.HibernateUtil;
import com.example.gestorpedidoshibernate.domain.Usuario.Usuario;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;

/**
 * Clase que proporciona operaciones de acceso a datos para la entidad Pedido.
 * Implementa la interfaz DAO para realizar operaciones CRUD (Create, Read, Update, Delete).
 *
 * @author Alejandro Álvarez Mérida
 * @version 26-11-2023
 */
public class PedidoDAO implements DAO<Pedido> {

    /**
     * Encuentra un pedido por su código.
     *
     * @param codigo El código del pedido a buscar.
     * @return El pedido con el código proporcionado, o null si no se encuentra.
     */
    public static Pedido findByCodigo(String codigo) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Pedido pedido = session.createQuery("FROM Pedido WHERE codigo = :codigo", Pedido.class)
                    .setParameter("codigo", codigo)
                    .uniqueResult();
            Hibernate.initialize(pedido.getItems());
            return pedido;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Obtiene todos los pedidos.
     *
     * @return Una lista de todos los pedidos, o null si no hay pedidos.
     */
    @Override
    public ArrayList<Pedido> getAll() {
        return null;
    }

    /**
     * Obtiene todos los pedidos asociados a un usuario.
     *
     * @param u El identificador del usuario.
     * @return Una lista de pedidos asociados al usuario, o una lista vacía si no hay pedidos.
     */
    public ArrayList<Pedido> getAllFromUser(int u) {
        ArrayList<Pedido> results = new ArrayList<>(0);
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Query<Pedido> q = s.createQuery("from Pedido where usuario=:usuario_id", Pedido.class);
            q.setParameter("usuario_id", u);
            results = (ArrayList<Pedido>) q.getResultList();
        }
        return results;
    }

    /**
     * Obtiene un pedido por su identificador.
     *
     * @param id El identificador del pedido.
     * @return El pedido con el identificador proporcionado, o null si no se encuentra.
     */
    @Override
    public Pedido get(int id) {
        return null;
    }

    /**
     * Obtiene un usuario por su identificador.
     *
     * @param id El identificador del usuario.
     * @return El usuario con el identificador proporcionado, o null si no se encuentra.
     */
    @Override
    public Usuario get(Long id) {
        return null;
    }

    /**
     * Guarda un nuevo pedido en la base de datos.
     *
     * @param data El pedido a guardar.
     * @return El pedido guardado.
     */
    @Override
    public Pedido save(Pedido data) {
        Pedido salida = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = s.beginTransaction();
            s.persist(data);
            t.commit();
            salida = data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return salida;
    }

    /**
     * Actualiza un pedido en la base de datos.
     *
     * @param data El pedido a actualizar.
     */
    @Override
    public void update(Pedido data) {

    }

    /**
     * Elimina un pedido de la base de datos.
     *
     * @param data El pedido a eliminar.
     */
    @Override
    public void delete(Pedido data) {

    }

    /**
     * Elimina un pedido y sus elementos asociados por su código.
     *
     * @param codigoPedido El código del pedido a eliminar.
     */
    public void deleteByCodigo(String codigoPedido) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            // Elimina los ItemPedido asociados al Pedido con el código dado
            Query itemPedidoDeleteQuery = session.createQuery("DELETE FROM ItemPedido ip WHERE ip.pedido.codigo = :codigoPedido");
            itemPedidoDeleteQuery.setParameter("codigoPedido", codigoPedido);
            itemPedidoDeleteQuery.executeUpdate();

            // Obtiene y elimina el Pedido con el código dado
            Query<Pedido> pedidoQuery = session.createQuery("FROM Pedido WHERE codigo = :codigo", Pedido.class);
            pedidoQuery.setParameter("codigo", codigoPedido);
            Pedido pedido = pedidoQuery.uniqueResult();
            if (pedido != null) {
                session.remove(pedido);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
