package bonilla.mariela.ui;

import bonilla.mariela.tl.ControllerUsuario;
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
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class RegistrarModeradorController implements Initializable {

    private double xSetOff;
    private double ySetOff;



    @FXML
    Label avisoNombre,
            avisoIdentificacion,
            avisoFecha,
            avisoEdad,
            avisoCorreo,
            avisoContrasenna,
            avisoConfirmacionContrasenna;

    @ FXML JFXComboBox tipoIdentificacion;


    @FXML JFXTextField text_nombre, text_identificacion, text_correo, text_edad;

    @FXML JFXPasswordField text_contrasenna,
                            text_confirmacion;

    @FXML JFXDatePicker text_fecha_nacimiento;


    @FXML
    public void cerrarApp() {
        Platform.exit();
        System.out.println("Se cerró la aplicación");
    }


    public void salirVentana(MouseEvent event) {
        Stage escenaPrincipal = (Stage)((Node) event.getSource()).getScene().getWindow();
        escenaPrincipal.setIconified(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //text_correo.addEventFilter(KeyEvent.ANY, validarCorreo);
        ObservableList<String> tipos = FXCollections.observableArrayList(
                "DNI", "Residencia", "Pasaporte"
        );

        tipoIdentificacion.setItems(tipos);
    }

    @FXML
    public void calcularEdad(ActionEvent event) {
        int edad;
        int mes;
        int dia;
        LocalDate fechaNacimiento = LocalDate.parse(text_fecha_nacimiento.getValue().toString());
        LocalDate fechaActual = LocalDate.now();
        edad = fechaActual.getYear() - fechaNacimiento.getYear();
        mes = fechaActual.getMonthValue() - fechaNacimiento.getMonthValue();
        dia = fechaActual.getDayOfMonth() - fechaNacimiento.getDayOfMonth();
        if (mes<0 || (mes==0 && dia < 0 )) {
            edad = edad - 1;
        }
        text_edad.setText(Integer.toString(edad));
    }



    private boolean validarTextos() {

        boolean error=false;
        LocalDate fechaHoy = LocalDate.now();

        if(text_nombre.getText().isEmpty()) {
            avisoNombre.setText("Debe ingresar un nombre");
            error=true;
        } else if (!(text_nombre.getText().matches("^(((?<!^)\\s(?!$)|[-a-zA-Z])*)$"))) {
            avisoNombre.setText("Ingresa un nombre válido");
            error = true;
        } else {
            avisoNombre.setText("");
            error = false;
        }


        if (tipoIdentificacion.getValue() == null) {
            avisoIdentificacion.setText("Primero debe de ingresar un tipo de identificación");
            error = true;
        } else {

            if(text_identificacion.getText().isEmpty()) {
                avisoIdentificacion.setText("Debe ingresar una identificación");
                error = true;
            } else {

                switch (tipoIdentificacion.getValue().toString()) {
                    case "DNI":
                        if (!(text_identificacion.getText().matches("\\d{9}"))) {
                            avisoIdentificacion.setText("La identificación debe tener 9 dígitos y sólo se admiten números");
                            error = true;
                        } else {
                            avisoIdentificacion.setText("");
                            error = false;
                        }
                        break;
                    case "Residencia":
                        if (!(text_identificacion.getText().matches("\\d{10}"))) {
                            avisoIdentificacion.setText("La identificación debe tener 10 dígitos y sólo se admiten números");
                            error = true;
                        } else {
                            avisoIdentificacion.setText("");
                            error = false;
                        }
                        break;
                    case "Pasaporte":

                        if (!(text_identificacion.getText().matches("[A-Z]{1}\\d{12}"))) {
                            avisoIdentificacion.setText("El pasaporte debe de contener 13 dígitos");
                            error = true;
                        } else {
                            avisoIdentificacion.setText("");
                            error = false;
                        }
                        break;
                }
            }
        }

        if(text_fecha_nacimiento.getValue()==null) {
            avisoFecha.setText("Debe ingresar una fecha");
            error = true;
        } else if (text_fecha_nacimiento.getValue().toString().matches("[a-zA-Z]")) {
            avisoFecha.setText("Debe ingresar una fecha válida");
            error = true;
        } else if (fechaHoy.isBefore(text_fecha_nacimiento.getValue())) {
            avisoFecha.setText("Debe ingresar una fecha anterior a la de hoy");
            error = true;
        }else {
            avisoFecha.setText("");
            error = false;
        }

        if(text_edad.getText().isEmpty()) {
            avisoEdad.setText("Debe ingresar una fecha para poder calcular su edad");
            error = true;
        }else if (Integer.parseInt(text_edad.getText())<18) {
            avisoEdad.setText("Su edad debe ser mayor de 18 años");
            error = true;
        }else {
            avisoEdad.setText("");
            error = false;
        }




        if (text_correo.getText().isEmpty()) {
            avisoCorreo.setText("Debe ingresar su correo electrónico");
            error = true;
        } else if (!text_correo.getText().matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$")) {
            avisoCorreo.setText("Debe ingresar un correo electrónico válido");
            error = true;
        } else {
            avisoCorreo.setText("");
            error = false;
        }

        if (text_contrasenna.getText().isEmpty()) {
            avisoContrasenna.setText("Ingrese una contraseña");
            error = true;
        }else if (text_contrasenna.getText().length()<4 && text_contrasenna.getText().length()> 16) {
            avisoContrasenna.setText("La cantidad de caractéres ingresados debe\n ser mayor a cuatro y menor de 16");
            error = true;
        } else {
            avisoContrasenna.setText("");
            error = false;
        }

        if (text_confirmacion.getText().isEmpty()) {
            avisoConfirmacionContrasenna.setText("Debe confirmar su contraseña");
            error = true;
        } else if (!text_confirmacion.getText().equals(text_contrasenna.getText())) {
            avisoConfirmacionContrasenna.setText("Error! Las contraseñas deben coincidir");
            text_contrasenna.clear();
            text_confirmacion.clear();
            error = true;
        }else {
            avisoConfirmacionContrasenna.setText("");
            error = false;
        }

        return error;
    }

    @FXML
    private void registrarModerador(ActionEvent e) throws IOException{
        ControllerUsuario gestorUsuario = new ControllerUsuario();
        boolean registroCorrecto;
        boolean errorTexto;
        errorTexto = validarTextos();
        try {
            if (errorTexto) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error!!");
                alert.setContentText("No se pudo realizar su registro.\n Revise los espacios en rojo");
                alert.showAndWait();
            } else {
                boolean identificacionRepetida;
                boolean correoRepetido;

                identificacionRepetida = gestorUsuario.validarIdentificacion(text_identificacion.getText());
                correoRepetido = gestorUsuario.validarCorreo(text_correo.getText());

                if (identificacionRepetida) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error!!");
                    alert.setContentText("La identificación ingresada ya se encuentra registrada en nuestro sistema.\n" +
                            " Ingrese una nueva identificación");
                    alert.showAndWait();

                    avisoIdentificacion.setText("Esta identificación ya se encuentra registrada");
                } else if (correoRepetido) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error!!");
                    alert.setContentText("El correo ingresado ya se encuentra registrado en el sistema.\n" +
                            "Ingrese un correo electrónico distinto");
                    alert.showAndWait();
                    avisoCorreo.setText("Este correo ya se encuentra registrado en el sistema");

                } else {
                    registroCorrecto = gestorUsuario.registrarModerador( text_nombre.getText(),
                            tipoIdentificacion.getValue().toString(), text_identificacion.getText()
                            , text_fecha_nacimiento.getValue(),
                            Integer.parseInt(text_edad.getText()), text_correo.getText(), text_contrasenna.getText());

                    if (registroCorrecto) {

                        irSitioPrincipal(e);
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Registro correcto");
                        alert.setContentText("Su registro se ha realizado correctamente.\nPuede continuar con el ingreso a CollectorsBazar");
                        alert.showAndWait();

                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Registro incorrecto");
                        alert.setContentText("No se pudo realizar su registro");
                        alert.showAndWait();
                    }
                }
            }
        } catch (SQLException event) {
            System.out.println(event.getMessage());
        }

    }



    @FXML
    private void irSitioPrincipal(ActionEvent event) throws IOException {
        Stage escenaPrincipal = (Stage)((Node) event.getSource()).getScene().getWindow();

        Parent ruta = null;
        ruta = FXMLLoader.load(getClass().getResource("sitio_inicio.fxml"));

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
