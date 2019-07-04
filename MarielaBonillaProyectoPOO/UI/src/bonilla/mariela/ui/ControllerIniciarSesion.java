package bonilla.mariela.ui;

import bonilla.mariela.tl.ControllerInicioSesion;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ControllerIniciarSesion implements Initializable {

    private double xSetOff;
    private double ySetOff;

    @FXML
    Button boton_inicio;

    @FXML
    TextField usuario_inicio,
            contrasenna_inicio;

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

    @FXML
    private void irRegistroUsuario(ActionEvent event) throws IOException {
        Stage escenaPrincipal = (Stage)((Node) event.getSource()).getScene().getWindow();
        Parent ruta = FXMLLoader.load(getClass().getResource("registrar_usuario.fxml"));

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
    public void initialize(URL location, ResourceBundle resources) {


    }

    @FXML
    public boolean validarCredenciales() {
        boolean exitoso = false;
        ControllerUsuario gestorUsuario = new  ControllerUsuario();
        ControllerInicioSesion gestorInicioSesion = new ControllerInicioSesion();
        String tipoUsuario;
        String pcorreo = usuario_inicio.getText();

       try {
           Alert alert = new Alert(Alert.AlertType.INFORMATION);

           switch (gestorUsuario.validarCredenciales(usuario_inicio.getText(),contrasenna_inicio.getText())) {
               case "1":
                   alert.setTitle("Ëxito!!");
                   alert.setContentText("Inicio de sesión exitoso");
                   alert.show();
                   tipoUsuario = gestorUsuario.obtenerTipoUsuario(pcorreo);
                   switch (tipoUsuario) {
                       case "Moderador":
                           gestorInicioSesion.usuarioModeradorIniciado(pcorreo);

                           irMenuModerador();
                           break;
                       case "Vendedor":
                           gestorInicioSesion.usuarioVendedorIniciado(pcorreo);

                           irMenuVendedor();
                           break;
                       case "Coleccionista":
                           gestorInicioSesion.usuarioColeccionistaIniciado(pcorreo);

                           irMenuColeccionista();
                           break;
                       default:
                           break;

                   }

                   break;
               case "2":
                   alert.setTitle("Error!!");
                   alert.setContentText("Esta contraseña no es correcta. \nInténtelo nuevamente.");
                   contrasenna_inicio.clear();
                   alert.show();
                   break;

               case "0":
                   alert.setTitle("Error!!");
                   alert.setContentText("Este correo no se encuentra registrado en nuestro servidor. \n" +
                           "Inténtelo nuevamente, o bien, proceda a crearse una nueva cuenta.");
                   alert.show();
                   contrasenna_inicio.clear();

                   break;
               default:
                   break;

           }


       } catch (Exception e) {
           //
           System.out.println(e.getMessage());
       }
        return exitoso;
    }


    @FXML
    private void iniciarSesion() {

    }

    @FXML
    private void irMenuModerador() throws IOException {

        Stage escenaPrincipal = (Stage) boton_inicio.getScene().getWindow();
        System.out.println("Ingresa al menu del vendedor");
        Parent ruta = FXMLLoader.load(getClass().getResource("menu_moderador.fxml"));

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
    private void irMenuVendedor() throws IOException {
        Stage escenaPrincipal = (Stage) boton_inicio.getScene().getWindow();

        System.out.println("Ingresa al menu del vendedor");
        Parent ruta = FXMLLoader.load(getClass().getResource("menu_vendedor.fxml"));

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
    private void irMenuColeccionista() throws IOException {
        //Stage escenaPrincipal = (Stage)((Node) event.getSource()).getScene().getWindow();
        Stage escenaPrincipal = (Stage) boton_inicio.getScene().getWindow();

        System.out.println("Ingresa al menu del inicio de la aplicacion");
        Parent ruta = FXMLLoader.load(getClass().getResource("menu_coleccionista.fxml"));

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
