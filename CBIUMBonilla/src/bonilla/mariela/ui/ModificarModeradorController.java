/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package bonilla.mariela.ui;

import bonilla.mariela.bl.usuario.UsuarioIniciado;
import bonilla.mariela.tl.ControllerUsuario;
import com.jfoenix.controls.*;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ModificarModeradorController implements Initializable {

    @FXML
    JFXToggleButton togglEditarPerfil;

    @FXML
    Label avisoNombre,
            avisoFecha,
            avisoEdad,
            avisoCorreo,
            avisoContrasenna;



    @FXML
    JFXTextField text_nombre, text_correo, text_edad;

    @FXML
    JFXPasswordField text_contrasenna;

    @FXML
    JFXDatePicker select_fecha;

    @FXML
    AnchorPane misDatos;

    @FXML JFXButton btn_modificar;

    private double xSetOff;
    private double ySetOff;


    @FXML
    public void cerrarApp() {
        Platform.exit();
        System.out.println("Se cerró la aplicación");
    }


    public void salirVentana(MouseEvent event) {
        Stage escenaPrincipal = (Stage) ((Node) event.getSource()).getScene().getWindow();
        escenaPrincipal.setIconified(true);
    }

    public void habilitarEditar(ActionEvent event) throws IOException {
        if (togglEditarPerfil.isSelected()) {
            misDatos.setDisable(false);
            btn_modificar.setDisable(false);
        } else if (!togglEditarPerfil.isSelected()) {
            misDatos.setDisable(true);
            btn_modificar.setDisable(true);
        }
    }

    @FXML
    private void irMenu(ActionEvent event) throws IOException {
        Stage escenaPrincipal = (Stage) ((Node) event.getSource()).getScene().getWindow();

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
                escenaPrincipal.setX(event.getScreenX() - xSetOff);
                escenaPrincipal.setY(event.getScreenY() - ySetOff);
            }
        });

        Scene nueva_escena = new Scene(ruta);
        escenaPrincipal.hide();

        escenaPrincipal.setScene(nueva_escena);
        escenaPrincipal.show();
    }

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

        UsuarioIniciado login = new UsuarioIniciado();



        text_nombre.setText(login.getNombre());
        select_fecha.getEditor().setText(String.valueOf(login.getFechaNacimiento()));
        text_edad.setText(String.valueOf(login.getEdad()));
        text_correo.setText(login.getCorreo());
        text_contrasenna.setText(login.getContrasenna());




    }



    @FXML
    public void calcularEdad(ActionEvent event) {
        int edad;
        int mes;
        int dia;
        LocalDate fechaNacimiento = LocalDate.parse(select_fecha.getValue().toString());
        LocalDate fechaActual = LocalDate.now();
        edad = fechaActual.getYear() - fechaNacimiento.getYear();
        mes = fechaActual.getMonthValue() - fechaNacimiento.getMonthValue();
        dia = fechaActual.getDayOfMonth() - fechaNacimiento.getDayOfMonth();
        if (mes < 0 || (mes == 0 && dia < 0)) {
            edad = edad - 1;
        }
        text_edad.setText(Integer.toString(edad));
    }


    /**
     * Va a permitir que al clickear el botón, se pueda modificar al usuario
     * @author Mariela Bonilla
     * @version 1.0
     */
    public void modificarUsuario(ActionEvent event) throws IOException, SQLException {
        ControllerUsuario ctUsuario = new ControllerUsuario();

        boolean correcto;
        if(validarEspacios()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error!!");
            alert.setContentText("Revise los espacios en rojo");
            alert.showAndWait();
        } else {
            boolean correoRepetido;

            correoRepetido = ctUsuario.validarCorreo(text_correo.getText());

            if (correoRepetido) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error!!");
                alert.setContentText("El correo ingresado ya se encuentra registrado en el sistema.\n" +
                        " Ingrese un nuevo correo electrónico");
                alert.showAndWait();
                avisoCorreo.setText("Este correo ya se encuentra registrado en el sistema");

            } else {
                ctUsuario.modificarModerador(text_nombre.getText(),
                        LocalDate.parse(select_fecha.getValue().toString()),
                        Integer.parseInt(text_edad.getText()), text_correo.getText(),
                        text_contrasenna.getText());

                irMenu(event);
            }
        }
    }



    private boolean validarEspacios() {
        UsuarioIniciado login = new UsuarioIniciado();
        boolean error=false;
        LocalDate fechaHoy = LocalDate.now();

        if(text_nombre.getText().isEmpty()) {
            avisoNombre.setText("Debe ingresar un nombre");
            text_nombre.setText(login.getNombre());

            error=true;
        } else if (!(text_nombre.getText().matches("^(((?<!^)\\s(?!$)|[-a-zA-Z])*)$"))) {
            avisoNombre.setText("Ingresa un nombre válido");
            text_nombre.setText(login.getNombre());
            error = true;
        } else {
            avisoNombre.setText("");
            error = false;
        }

        if(select_fecha.getValue()==null) {
            avisoFecha.setText("Debe ingresar una fecha");
            error = true;
            select_fecha.getEditor().setText(String.valueOf(login.getFechaNacimiento()));

        } else if (select_fecha.getValue().toString().matches("[a-zA-Z]")) {
            avisoFecha.setText("Debe ingresar una fecha válida");
            select_fecha.getEditor().setText(String.valueOf(login.getFechaNacimiento()));
            error = true;
        } else if (fechaHoy.isBefore(select_fecha.getValue())) {
            avisoFecha.setText("Debe ingresar una fecha anterior a la de hoy");
            select_fecha.getEditor().setText(String.valueOf(login.getFechaNacimiento()));
            error = true;
        }else {
            avisoFecha.setText("");
            error = false;
        }

        if(text_edad.getText().isEmpty()) {
            avisoEdad.setText("Debe ingresar una nueva fecha para poder volver a calcular calcular su edad");
            text_edad.setText(String.valueOf(login.getEdad()));
            select_fecha.getEditor().setText(String.valueOf(login.getFechaNacimiento()));
            error = true;
        }else if (Integer.parseInt(text_edad.getText())<18) {
            avisoEdad.setText("Su edad actual debe ser mayor de 18 años");
            select_fecha.getEditor().setText(String.valueOf(login.getFechaNacimiento()));
            text_edad.setText(String.valueOf(login.getEdad()));
            error = true;
        }else {
            avisoEdad.setText("");
            error = false;
        }

        if (text_correo.getText().isEmpty()) {
            avisoCorreo.setText("Debe ingresar un nuevo correo electrónico");
            text_correo.setText(login.getCorreo());

            error = true;
        } else if (text_correo.getText().matches("[^@]+@[^@]+\\\\.[a-zA-Z]{2,}")) {
            avisoCorreo.setText("Debe ingresar un correo electrónico válido");
            error = true;
        } else {
            avisoCorreo.setText("");
            error = false;
        }

        if (text_contrasenna.getText().isEmpty()) {
            avisoContrasenna.setText("Ingrese una nueva contraseña");
            text_contrasenna.setText(login.getContrasenna());



            error = true;
        }else if (text_contrasenna.getText().length()<4 && text_contrasenna.getText().length()> 16) {
            avisoContrasenna.setText("La cantidad de caractéres ingresados debe\n ser mayor a cuatro y menor de 16");
            error = true;
        } else {
            avisoContrasenna.setText("");
            error = false;
        }

        return error;
    }
}
