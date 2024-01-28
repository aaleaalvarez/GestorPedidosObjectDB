package com.example.gestorpedidoshibernate.domain.ItemPedido;

import com.example.gestorpedidoshibernate.domain.DAO;
import com.example.gestorpedidoshibernate.domain.ObjectDBUtil;
import com.example.gestorpedidoshibernate.domain.Producto.Producto;
import com.example.gestorpedidoshibernate.domain.Usuario.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
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
    public List<Producto> getAll() {
        return null;
    }

    /**
     * Encuentra todos los elementos del pedido asociados a un pedido específico mediante su código.
     *
     * @param codigoPedido El código del pedido para el cual se buscan los elementos.
     * @return Una lista de elementos del pedido asociados al pedido con el código dado.
     */
    public List<ItemPedido> findItemsByPedidoCodigo(String codigoPedido) {
        javax.persistence.EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        List<ItemPedido> items = new ArrayList<>();

        try {
            TypedQuery<ItemPedido> query = em.createQuery(
                    "SELECT ip FROM ItemPedido ip JOIN ip.pedido p JOIN ip.producto prod WHERE p.codigo = :codigoPedido", ItemPedido.class);
            query.setParameter("codigoPedido", codigoPedido);
            items = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return items;
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
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(data);
            transaction.commit();
            return data;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw e;
        } finally {
            em.close();
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
    @Override
    public boolean remove(ItemPedido item) {
        javax.persistence.EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        javax.persistence.EntityTransaction transaction = em.getTransaction();
        boolean salida = false;
        try {
            transaction.begin();
            ItemPedido itemToRemove = em.find(ItemPedido.class, item.getId());
            if (itemToRemove != null) {
                em.remove(itemToRemove);
                salida = true;
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
        return salida;
    }
}
