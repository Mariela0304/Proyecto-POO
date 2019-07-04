package bonilla.mariela.ui;

import bonilla.mariela.tl.ControllerUsuario;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;

public class SitioInicioController implements Initializable {

    private double xSetOff;
    private double ySetOff;
    @FXML
    Button btnComenzar;


    @FXML
    public void cerrarApp() {
        Platform.exit();
        System.out.println("Se cerró la aplicación");
    }


    public void salirVentana(MouseEvent event) {
        Stage escenaPrincipal = (Stage)((Node) event.getSource()).getScene().getWindow();
        escenaPrincipal.setIconified(true);
    }

    @FXML
    private void irMenuInicio(ActionEvent event) throws IOException {
        Stage escenaPrincipal = (Stage)((Node) event.getSource()).getScene().getWindow();

        Parent ruta = FXMLLoader.load(getClass().getResource("menu_inicio.fxml"));

        ruta.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xSetOff = event.getSceneX();
                ySetOff = event.getScreenY();
            }
        });
        ruta.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                escenaPrincipal.setX(event.getScreenX()-xSetOff);
                escenaPrincipal.setY(event.getScreenY()-ySetOff);
            }
        });

        Scene nueva_escena = new Scene(ruta);
        escenaPrincipal.hide();
        escenaPrincipal.setScene(nueva_escena);
        escenaPrincipal.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources)  {

    }

    @FXML
    private void verificarModerador(ActionEvent event) {
        ControllerUsuario gestorUsuario = new ControllerUsuario();
        boolean moderadorExite;

        try {
            moderadorExite = gestorUsuario.validarExistenciaModerador();
            if (!moderadorExite) {


                registrarModerador(event);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("No hay moderador");
                alert.setContentText("No hay ningún moderador registrado\nDebe de haber un moderador\nSe le solicita al moderador que se registre en el siguiente formulario" +
                        "\n Se recuerda que solo debe de haber un moderador");
                alert.show();
            } else {
                irMenuInicio(event);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }



    @FXML
    private void registrarModerador(ActionEvent event) throws IOException {
        Stage escenaPrincipal = (Stage)((Node) event.getSource()).getScene().getWindow();

        Parent ruta = null;
        ruta = FXMLLoader.load(getClass().getResource("registrar_moderador.fxml"));

        ruta.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xSetOff = event.getSceneX();
                ySetOff = event.getScreenY();
            }
        });
        ruta.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                escenaPrincipal.setX(event.getScreenX()-xSetOff);
                escenaPrincipal.setY(event.getScreenY()-ySetOff);
            }
        });

        Scene nueva_escena = new Scene(ruta);
        escenaPrincipal.hide();
        escenaPrincipal.setScene(nueva_escena);
        escenaPrincipal.show();

    }
}
