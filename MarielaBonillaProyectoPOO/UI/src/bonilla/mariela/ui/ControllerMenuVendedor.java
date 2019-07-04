package bonilla.mariela.ui;

import bonilla.mariela.bl.usuario.UsuarioIniciado;
import bonilla.mariela.tl.ControllerUsuario;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerMenuVendedor implements Initializable {

    private double xSetOff;
    private double ySetOff;
    @FXML
    Label posicion_nombre_usuario;



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
    private void irRegistrarSubasta(MouseEvent event) throws IOException {
        Stage escenaPrincipal = (Stage)((Node) event.getSource()).getScene().getWindow();

        Parent ruta = FXMLLoader.load(getClass().getResource("registrar_subastas_vendedor.fxml"));

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

    @FXML
    private void modificarUsuario(MouseEvent e) throws IOException {
        System.out.println("modificar usuario");

        Stage escenaPrincipal = (Stage)((Node) e.getSource()).getScene().getWindow();

        System.out.println("Ingresa al modificar el usuario");
        Parent ruta = FXMLLoader.load(getClass().getResource("modificar_vendedor.fxml"));

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

    @FXML
    private void irMisSubastas(MouseEvent event) throws IOException {
        Stage escenaPrincipal = (Stage)((Node) event.getSource()).getScene().getWindow();

        Parent ruta = FXMLLoader.load(getClass().getResource("mis_subastas_vendedor.fxml"));

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

    @FXML
    private void cerrarSesion(MouseEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "¿Desea cerrar sesión?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Parent ruta = FXMLLoader.load(getClass().getResource("sitio_inicio.fxml"));

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
                    stage.setX(event.getScreenX() - xSetOff);
                    stage.setY(event.getScreenY() - ySetOff);
                }
            });
            ArrayList<String> dato = new ArrayList<>();
            UsuarioIniciado gestorIniciar = new UsuarioIniciado();
            gestorIniciar.setNombre("");
            gestorIniciar.setIdentificacion("");
            gestorIniciar.setTipoIdentificacion("");
            gestorIniciar.setFechaNacimiento(LocalDate.now());
            gestorIniciar.setEdad(0);
            gestorIniciar.setCorreo("");
            gestorIniciar.setContrasenna("");
            gestorIniciar.setProvincia("");
            gestorIniciar.setCanton("");
            gestorIniciar.setDistrito("");
            gestorIniciar.setPuntuacion(0);
            gestorIniciar.setIntereses(dato);


            Scene nueva_escena = new Scene(ruta);
            stage.hide();
            stage.setScene(nueva_escena);
            stage.show();        }
    }

    @FXML
    private void desactivarCuenta(MouseEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Está apunto de eliminar su cuenta.\n Recuerde que no podrá recuperar sus datos" +
                        "\n¿Está seguro/a que desea eliminarla?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Eliminar cuenta");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            ControllerUsuario gestorUsuario = new ControllerUsuario();
            gestorUsuario.desactivarUsuario();

            Stage escenaPrincipal = (Stage)((Node) event.getSource()).getScene().getWindow();

            Parent ruta = FXMLLoader.load(getClass().getResource("sitio_inicio.fxml"));

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

            Alert alert2 = new Alert(Alert.AlertType.INFORMATION,(
                    "Se ha eliminado su cuenta\n No podrá volver a hacer uso de sus datos"),
                    ButtonType.OK);
            alert.setTitle("Cuenta eliminada");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UsuarioIniciado login = new UsuarioIniciado();
        posicion_nombre_usuario.setText("!Hola! "+ login.getNombre());
    }
}
