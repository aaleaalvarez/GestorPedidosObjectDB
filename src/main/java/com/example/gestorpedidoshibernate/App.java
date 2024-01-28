package com.example.gestorpedidoshibernate;

import com.example.gestorpedidoshibernate.domain.Producto.Producto;
import com.example.gestorpedidoshibernate.domain.Producto.ProductoDAO;
import com.example.gestorpedidoshibernate.domain.Usuario.UsuarioDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase principal de la aplicación que extiende la clase `Application` de JavaFX.
 * Controla la inicialización y gestión de la interfaz gráfica de usuario.
 *
 * @author Alejandro Álvarez Mérida
 * @version 26-11-2023
 */
public class App extends Application {

    private static Stage stage;

    /**
     * Cambia la escena actual de la aplicación.
     *
     * @param fxml  La ruta al archivo FXML que define la nueva escena.
     * @param title El título de la nueva escena.
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    public static void changeScene(String fxml, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/" + fxml));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Método principal que inicia la aplicación.
     *
     * @param args Argumentos de la línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Método llamado cuando se inicia la aplicación.
     *
     * @param stage La ventana principal de la aplicación.
     * @throws IOException Si ocurre un error al cargar el archivo FXML de la escena inicial.
     */
    @Override
    public void start(Stage stage) throws IOException {
        try {
            // Si no hay nada en la base de datos, introduzco datos de prueba
            ProductoDAO productoDAO = new ProductoDAO();
            for (Producto producto : Data.getProductos()) {
                productoDAO.saveOrUpdate(producto);
            }

            UsuarioDAO usuariodao = new UsuarioDAO();
            usuariodao.saveAll(Data.getUsuarios());

        } catch (Exception e) {
            e.printStackTrace();
        }
        App.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }
}