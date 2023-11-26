package com.example.gestorpedidoshibernate.controllers;

import com.example.gestorpedidoshibernate.App;
import com.example.gestorpedidoshibernate.Session;
import com.example.gestorpedidoshibernate.domain.HibernateUtil;
import com.example.gestorpedidoshibernate.domain.Pedido.Pedido;
import com.example.gestorpedidoshibernate.domain.Pedido.PedidoDAO;
import com.example.gestorpedidoshibernate.domain.Producto.ProductoDAO;
import com.example.gestorpedidoshibernate.domain.Usuario.Usuario;
import com.example.gestorpedidoshibernate.domain.Usuario.UsuarioDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.hibernate.Transaction;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controlador para la vista que muestra la lista de pedidos de un usuario.
 * Permite gestionar pedidos existentes y crear nuevos pedidos.
 *
 * @author Alejandro Álvarez Mérida
 * @version 26-11-2023
 */
public class ListPedidosController implements Initializable {
    @javafx.fxml.FXML
    private MenuItem menuLogout;
    @javafx.fxml.FXML
    private MenuItem menuCerrar;
    @javafx.fxml.FXML
    private Label usuarioActivo;
    @javafx.fxml.FXML
    private TableView<Pedido> tabla;
    @javafx.fxml.FXML
    private TableColumn<Pedido, String> CodColumn;
    @javafx.fxml.FXML
    private TableColumn<Pedido, String> FechaColumn;
    @javafx.fxml.FXML
    private TableColumn<Pedido, String> UsuarioIdColumn;
    @javafx.fxml.FXML
    private TableColumn<Pedido, String> TotalColumn;
    @javafx.fxml.FXML
    private TableColumn<Pedido, String> idColumn;

    private Pedido pedidoseleccionado;
    @javafx.fxml.FXML
    private TextField codPedidoSelected;
    @javafx.fxml.FXML
    private Button crearPedido;

    private ProductoDAO productoDAO = new ProductoDAO();
    private PedidoDAO pedidoDAO = new PedidoDAO();

    /**
     * Maneja el evento de clic en el botón "Logout".
     *
     * @param actionEvent El evento de acción asociado al clic del botón.
     */
    @javafx.fxml.FXML
    public void Logout(ActionEvent actionEvent) {
        try {
            App.changeScene("login-view.fxml", "Login");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Maneja el evento de clic en el botón "Cerrar".
     *
     * @param actionEvent El evento de acción asociado al clic del botón.
     */
    @javafx.fxml.FXML
    public void Cerrar(ActionEvent actionEvent) {
        System.exit(0);
    }

    /**
     * Inicializa la clase. Se ejecuta automáticamente al cargar la vista.
     *
     * @param url            La ubicación para la inicialización.
     * @param resourceBundle Los recursos para la inicialización.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usuarioActivo.setText("Hola de nuevo " + Session.getCurrentUser().getNombre());

        idColumn.setCellValueFactory((fila) -> {
            return new SimpleStringProperty(fila.getValue().getId() + "");
        });

        CodColumn.setCellValueFactory((fila) -> {
            return new SimpleStringProperty(fila.getValue().getCodigo() + "");
        });

        FechaColumn.setCellValueFactory((fila) -> {
            return new SimpleStringProperty(fila.getValue().getFecha() + "");
        });

        UsuarioIdColumn.setCellValueFactory((fila) -> {
            return new SimpleStringProperty(fila.getValue().getUsuario().getId() + "");
        });

        TotalColumn.setCellValueFactory((fila) -> {
            return new SimpleStringProperty(fila.getValue().getTotal() + "");
        });

        Session.setCurrentUser(new UsuarioDAO().get(Session.getCurrentUser().getId()));
        tabla.getItems().addAll(Session.getCurrentUser().getPedidos());

        mostrarVentanaItemPedido();

    }

    /**
     * Muestra la ventana de los ítems de un pedido cuando se selecciona un pedido en la tabla.
     */
    private void mostrarVentanaItemPedido() {
        tabla.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                Pedido pedidoSeleccionado = (Pedido) newValue;
                Pedido codigoPedido = pedidoSeleccionado;
                Session.setCurrentItemPedido(codigoPedido); // Guardar el código del pedido en la sesión
                try {
                    App.changeScene("listItemPedido-view.fxml", "Items del Pedido");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    /*
    public void onViewItemsClick(Event event) {
        Pedido pedidoSeleccionado = tabla.getSelectionModel().getSelectedItem();
        if (pedidoSeleccionado != null) {
            try {
                // Almacena el ItemPedido completo en la sesión
                Session.setItemPedido(pedidoSeleccionado);

                // Cambia a la escena de detalles del pedido
                App.changeScene("listItemPedido-view.fxml", "Items del pedido");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            // Mostrar mensaje de error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de Pedido");
            alert.setHeaderText(null);
            alert.setContentText("No has seleccionado ningún pedido.");
            alert.showAndWait();
        }
    }

     */

    /**
     * Actualiza la tabla de pedidos con los pedidos más recientes del usuario.
     */
    private void actualizarTablaPedidos() {
        List<Pedido> pedidosActualizados = pedidoDAO.getAllFromUser(Math.toIntExact(Session.getCurrentUser().getId()));
        tabla.setItems(FXCollections.observableArrayList(pedidosActualizados));
    }

    /**
     * Maneja el evento de clic en el botón "Crear Pedido".
     *
     * @param actionEvent El evento de acción asociado al clic del botón.
     */
    @javafx.fxml.FXML
    public void crearPedido(ActionEvent actionEvent) {
        String codigoPedido = codPedidoSelected.getText();

        if (codigoPedido.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Código de pedido vacío");
            alert.setHeaderText("Por favor, introduce un código para el pedido");
            alert.showAndWait();
            return;
        }

        Date fechaPedido = new Date(System.currentTimeMillis());
        Usuario usuario = Session.getCurrentUser();

        Pedido pedido = new Pedido();
        pedido.setCodigo(codigoPedido);
        pedido.setFecha(fechaPedido);
        pedido.setUsuario(usuario);
        pedido.setTotal(0.0);

        try (org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();

            session.save(pedido);

            // Commit y cerrar la transacción
            t.commit();

            // Almacena el pedido en la sesión (opcional)
            Session.setCurrentItemPedido(pedido);

            // Cambiar a la escena de los ítems directamente después de crear un pedido
            try {
                App.changeScene("listItemPedido-view.fxml", "Items del Pedido");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}