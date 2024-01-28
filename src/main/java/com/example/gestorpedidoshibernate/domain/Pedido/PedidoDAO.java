package com.example.gestorpedidoshibernate.domain.Pedido;

import com.example.gestorpedidoshibernate.domain.DAO;
import com.example.gestorpedidoshibernate.domain.ItemPedido.ItemPedido;
import com.example.gestorpedidoshibernate.domain.ObjectDBUtil;
import com.example.gestorpedidoshibernate.domain.Producto.Producto;
import com.example.gestorpedidoshibernate.domain.Usuario.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

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
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        Pedido pedido = null;
        try {
            TypedQuery<Pedido> query = em.createQuery("SELECT p FROM Pedido p WHERE p.codigo = :codigo", Pedido.class);
            query.setParameter("codigo", codigo);
            pedido = query.getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return pedido;
    }

    /**
     * Obtiene todos los pedidos.
     *
     * @return Una lista de todos los pedidos, o null si no hay pedidos.
     */
    @Override
    public List<Producto> getAll() {
        return null;
    }

    /**
     * Obtiene todos los pedidos asociados a un usuario.
     *
     * @param u El identificador del usuario.
     * @return Una lista de pedidos asociados al usuario, o una lista vacía si no hay pedidos.
     */
    public List<Pedido> getAllFromUser(int u) {
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        List<Pedido> pedidos;
        try {
            TypedQuery<Pedido> query = em.createQuery("from Pedido where usuario=:usuario_id", Pedido.class);
            query.setParameter("usuario_id", u);
            pedidos = query.getResultList();
        } finally {
            em.close();
        }
        return pedidos;
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
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(data);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
        return data;
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

    @Override
    public boolean remove(ItemPedido item) {
        return false;
    }

    /**
     * Elimina un pedido y sus elementos asociados por su código.
     *
     * @param codigoPedido El código del pedido a eliminar.
     */
    public void deleteByCodigo(String codigoPedido) {
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            TypedQuery<Pedido> query = em.createQuery("SELECT p FROM Pedido p WHERE p.codigo = :codigo", Pedido.class);
            query.setParameter("codigoPedido", codigoPedido);
            Pedido pedido = query.getSingleResult();
            if (pedido != null) {
                // Elimina los ítems de pedido asociados antes de eliminar el pedido
                for (ItemPedido itemPedido : pedido.getItems()) {
                    em.remove(itemPedido);
                }
                em.remove(pedido);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void actualizarPrecioTotalPedido(Long pedidoId) {
        EntityManager entityManager = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();

            Pedido pedido = entityManager.find(Pedido.class, pedidoId);
            Double nuevoTotal = calcularNuevoTotalPedido(pedido);
            pedido.setTotal(nuevoTotal);

            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    private Double calcularNuevoTotalPedido(Pedido pedido) {
        // Calcula el nuevo total del pedido en función de los ItemPedido
        // Puedes iterar sobre los ItemPedido y sumar sus precioTotal aquí

        // Ejemplo de cómo calcular el nuevo total:
        double nuevoTotal = pedido.getItems().stream()
                .mapToDouble(ItemPedido::getPrecioTotal)
                .sum();

        return nuevoTotal;
    }
}
