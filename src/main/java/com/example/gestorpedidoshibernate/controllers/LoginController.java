package com.example.gestorpedidoshibernate.controllers;

import com.example.gestorpedidoshibernate.App;
import com.example.gestorpedidoshibernate.Session;
import com.example.gestorpedidoshibernate.domain.Usuario.Usuario;
import com.example.gestorpedidoshibernate.domain.Usuario.UsuarioDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador para la vista de inicio de sesión.
 * Permite a los usuarios ingresar al sistema mediante la autenticación de usuario y contraseña.
 *
 * @author Alejandro Álvarez Mérida
 * @version 26-11-2023
 */
public class LoginController implements Initializable {
    @javafx.fxml.FXML
    private TextField userField;
    @javafx.fxml.FXML
    private PasswordField passField;
    @javafx.fxml.FXML
    private Button cancelarButton;
    @javafx.fxml.FXML
    private Button entrarButton;
    @javafx.fxml.FXML
    private Label info;

    /**
     * Maneja el evento de clic en el botón "Logout".
     *
     * @param actionEvent El evento de acción asociado al clic del botón.
     */
    @javafx.fxml.FXML
    public void logout(ActionEvent actionEvent) {
        System.exit(0);
    }

    /**
     * Maneja el evento de inicio de sesión.
     * Verifica las credenciales ingresadas y permite el acceso al sistema si son correctas.
     *
     * @param actionEvent El evento de acción asociado al clic del botón de inicio de sesión.
     */
    @FXML
    public void login(ActionEvent actionEvent) {
        String user = userField.getText();
        String pass = passField.getText();

        if (user.length() < 4 || pass.length() < 4) {
            info.setText("Introduce los datos");
            info.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        } else {
            Usuario u = (new UsuarioDAO()).validateUser(user, pass);
            if (u == null) {
                info.setText("Usuario no encontrado");
                info.setStyle("-fx-background-color: red; -fx-text-fill: white;");
            } else {
                info.setText("Usuario " + user + " y contraseña " + " correcto");
                info.setStyle("-fx-background-color:green; -fx-text-fill: white;");
                Session.setCurrentUser(u);
                try {
                    App.changeScene("listPedidos-view.fxml", "Colección de pedidos");

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * Inicializa la clase. Se ejecuta automáticamente al cargar la vista.
     *
     * @param url            La ubicación para la inicialización.
     * @param resourceBundle Los recursos para la inicialización.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}