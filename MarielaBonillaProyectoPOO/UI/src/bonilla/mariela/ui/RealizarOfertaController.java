package bonilla.mariela.ui;

import bonilla.mariela.bl.subasta.Subasta;
import bonilla.mariela.tl.ControllerInfoSubasta;
import bonilla.mariela.tl.ControllerOferta;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RealizarOfertaController implements Initializable {

    private double xSetOff;
    private double ySetOff;

    @FXML
    Label posicion_precio;

    @FXML
    JFXTextField text_precio;

    @FXML
    JFXButton btn_cancelar,
            btn_ofertar;




    public void salirVentana(MouseEvent event) {
        Stage escenaPrincipal = (Stage)((Node) event.getSource()).getScene().getWindow();
        escenaPrincipal.setIconified(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        ControllerInfoSubasta gestorInfoSubasta = new ControllerInfoSubasta();

        String [] datosSubasta = gestorInfoSubasta.obtenerDatosSubasta();

        posicion_precio.setText(datosSubasta[3]);
    }

    @FXML
    private void cancelar (ActionEvent event) {
        Stage escenaOferta = (Stage)((Node) event.getSource()).getScene().getWindow();
        escenaOferta.close();
    }

    @FXML
    private void realizarOferta(ActionEvent event) throws IOException {

        Stage escenaOferta = (Stage)((Node) event.getSource()).getScene().getWindow();
        ControllerOferta gestorOferta = new  ControllerOferta();

        if (Double.parseDouble(text_precio.getText())<=Double.parseDouble(posicion_precio.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Debe digitar un precio mayor al de la subasta");
        }else {
            gestorOferta.registrarOferta(Double.parseDouble(text_precio.getText()));
        }

        System.out.println("Usted ha realizado una oferta de: " + text_precio.getText());


        escenaOferta.close();
        volverCargarInfoSubasta(event);

    }

    public void volverCargarInfoSubasta(ActionEvent event) throws IOException {
        Stage escenaPrincipal = (Stage)((Node) event.getSource()).getScene().getWindow();


        Parent ruta = FXMLLoader.load(getClass().getResource("info_subasta_coleccionista.fxml"));

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
