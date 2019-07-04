/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package bonilla.mariela.ui;

import bonilla.mariela.tl.ControllerSubasta;
import bonilla.mariela.tl.ControllerUsuario;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class VerGanadorController implements Initializable {

    @FXML Label posicion_nombre;
    @FXML Label posicion_provincia;
    @FXML Label posicion_canton;
    @FXML Label posicion_distrito;
    @FXML Label posicion_direccion;
    @FXML Label aviso_calificacion;

    @FXML Rating rating;

    @FXML JFXButton btn_calificar;



    private double xSetOff;
    private double ySetOff;

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ControllerSubasta gestorSubasta = new ControllerSubasta();

        String[] ganador = gestorSubasta.obtenerGanador();

        posicion_nombre.setText(ganador[0]);
        posicion_provincia.setText(ganador[2]);
        posicion_canton.setText(ganador[3]);
        posicion_distrito.setText(ganador[4]);
        posicion_direccion.setText(ganador[5]);




    }

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
    private void volverInfoSubasta(ActionEvent event) throws IOException {
        Stage escenaPrincipal = (Stage)((Node) event.getSource()).getScene().getWindow();

        System.out.println("Ingresa a la lista de 'Mis subastas'");
        Parent ruta = FXMLLoader.load(getClass().getResource("info_subasta_vendedor.fxml"));

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




    public void calificarGanador(ActionEvent event){
        if (rating.getRating()==0) {
            aviso_calificacion.setText("Primero debe de seleccionar una de las estrellas");
        } else {
            double valor;
            valor = rating.getRating();

            ControllerUsuario gestorUsuario = new ControllerUsuario();

            gestorUsuario.realizarPuntuacionGanador(valor);

            btn_calificar.setDisable(true);
            aviso_calificacion.setText("Ya realizó la calificación al ganador");

            rating.setDisable(true);
        }

    }
}
