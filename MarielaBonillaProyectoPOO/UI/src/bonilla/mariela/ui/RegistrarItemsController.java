package bonilla.mariela.ui;

import bonilla.mariela.tl.ControllerItem;
import com.jfoenix.controls.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

public class RegistrarItemsController implements Initializable {
    private double xSetOff;
    private double ySetOff;

    @FXML JFXComboBox comboBoxEstados;
    @FXML JFXTextField textNombre;
    @FXML JFXTextArea textDescripcion;
    @FXML JFXDatePicker textFechaCompra;
    @FXML JFXTextField calculateAntiguedad;

    @FXML JFXButton btn_registrar_item;

    @FXML
    private void irMenu(ActionEvent event) throws IOException {
        Stage escenaPrincipal = (Stage)((Node) event.getSource()).getScene().getWindow();

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

    @FXML
    public void cerrarApp() {
        Platform.exit();
        System.out.println("Se cerró la aplicación");
    }


    public void salirVentana(MouseEvent event) {
        Stage escenaPrincipal = (Stage)((Node) event.getSource()).getScene().getWindow();
        escenaPrincipal.setIconified(true);
    }

    public void obtenerAntiguedad(ActionEvent event) {
        LocalDate fechaCompra = textFechaCompra.getValue();


        int anno = calcularAntiguedadAnnos(fechaCompra);
        int mes = calcularAntiguedadMeses(fechaCompra);
        int dia = calcularAntiguedadDias(fechaCompra);

        if (anno == 1 ) {
            if (mes == 1) {
                if (dia == 1) {
                    calculateAntiguedad.setText(calcularAntiguedadAnnos(fechaCompra) + " año, "+calcularAntiguedadMeses(fechaCompra) +
                            "mes " +
                            "y " +
                            calcularAntiguedadDias(fechaCompra) + " día");
                }  else {
                    calculateAntiguedad.setText(calcularAntiguedadAnnos(fechaCompra) + " año, "+calcularAntiguedadMeses(fechaCompra)
                            + "mes " +
                            "y " +
                            calcularAntiguedadDias(fechaCompra) + " días");
                }
            } else if (dia == 1){
                calculateAntiguedad.setText(calcularAntiguedadAnnos(fechaCompra) + " año, "+calcularAntiguedadMeses(fechaCompra) +
                        " meses " +
                        "y " +
                        calcularAntiguedadDias(fechaCompra) + " día");
            } else {
                calculateAntiguedad.setText(calcularAntiguedadAnnos(fechaCompra) + " año, "+calcularAntiguedadMeses(fechaCompra) +
                        " meses " +
                        "y " +
                        calcularAntiguedadDias(fechaCompra) + " días");
            }

        } else if (dia == 1) {
            calculateAntiguedad.setText(calcularAntiguedadAnnos(fechaCompra) + " años, "+calcularAntiguedadMeses(fechaCompra) +
                    " meses " +
                    "y " +
                    calcularAntiguedadDias(fechaCompra) + " día");
        } else {
            calculateAntiguedad.setText(calcularAntiguedadAnnos(fechaCompra) + " años, "+calcularAntiguedadMeses(fechaCompra) +
                    " meses " +
                    "y " +
                    calcularAntiguedadDias(fechaCompra) + " días");
        }


    }

    public void llenarEstados() {
        ObservableList<String> estados = FXCollections.observableArrayList(
                "Nuevo",
                "Usado",
                "Antigüo sin abrir"
        );

        comboBoxEstados.setItems(estados);


    }

    private int calcularAntiguedadDias(LocalDate fechaCompra)  {
        LocalDate fechaActual = LocalDate.now();
        Period dia = Period.between(fechaCompra, fechaActual);
        return dia.getDays();
    }
    private int calcularAntiguedadMeses(LocalDate fechaCompra)  {
        LocalDate fechaActual = LocalDate.now();
        Period mes = Period.between(fechaCompra, fechaActual);
        return mes.getMonths();
    }
    private int calcularAntiguedadAnnos(LocalDate fechaCompra) {
        LocalDate fechaActual = LocalDate.now();
        Period anno = Period.between(fechaCompra, fechaActual);
        return anno.getYears();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        llenarEstados();
    }

    @FXML
    private void registrarItems(ActionEvent e) {

        ControllerItem gestorItem = new ControllerItem();

        boolean registrado;

        registrado = gestorItem.registrarItem(textNombre.getText(), textDescripcion.getText(), comboBoxEstados.getValue().toString(),
                textFechaCompra.getValue());

        if (!registrado) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Registro incorrecto");
            alert.setContentText("No se pudo realizar su registro");
            alert.showAndWait();
        } else {
            irListaItems(e);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registro correcto");
            alert.setContentText("Su ítems se registró correctamente");
            alert.showAndWait();
        }


    }

    private void irListaItems(ActionEvent event) {
       try {
           Stage escenaPrincipal = (Stage)((Node) event.getSource()).getScene().getWindow();

           Parent ruta = FXMLLoader.load(getClass().getResource("mis_items.fxml"));

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

       }catch (Exception e) {
           System.out.println(e.getMessage());
       }
    }
}
