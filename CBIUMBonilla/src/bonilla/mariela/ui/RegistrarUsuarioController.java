package bonilla.mariela.ui;

import bonilla.mariela.tl.ControllerUsuario;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RegistrarUsuarioController implements Initializable {

    private Provincias administradorProvincias;

    private double xSetOff;
    private double ySetOff;

    /*
    Variables que funcionan para poder esconder y aparecer una parte del registro
     */
    @FXML ImageView nextPaso2;//boton que direcciona a paso 2
    @FXML ImageView nextPaso3;//boton que direcciona a paso 3
    @FXML ImageView backPaso1;//boton que devuelve a paso 1
    @FXML ImageView backPaso2;//boton que devuelve a paso2

    @FXML
    AnchorPane paso1; //espacio del primer paso
    @FXML AnchorPane paso2;//espacio del segundo paso
    @FXML AnchorPane paso3;//espacio del tercer paso
    @FXML AnchorPane intereses;

    /*
    Se llaman estas variables para poder ingresarles datos
     */

    @FXML Label avisoNombre,
                avisoIdentificacion,
                avisoFecha,
                avisoEdad,
                avisoProvincia,
                avisoCanton,
                avisoDistrito,
                avisoDireccion,
                avisoIntereses,
                avisoCorreo,
                avisoContrasenna;


    @FXML CheckBox ckbArtesanales,
            ckbArtesPlasticas,
            ckbAmbientales,
            ckbAnimales,
            ckbArtesEscenicas,
            ckbEscritura,
            ckbFotografia,
            ckbHogar,
            ckbLectura,
            ckbMedicina,
            ckbMusica,
            ckbReligion,
            ckbRompecabezas,
            ckbTecnologia;



    @ FXML ComboBox provincias,
            cantones,
            distritos ,
            tipoIdentificacion;


    @FXML RadioButton rbVendedor,
            rbColeccionista;

    /*
    Se llama estas variables para poder validar sus datos
     */
    @FXML TextField text_nombre, text_id, text_correo, text_edad;

    @FXML PasswordField text_contrasenna;

    @FXML TextArea text_direccion;

    @FXML DatePicker select_fecha;



    @Override
    public void initialize(URL location, ResourceBundle resources) {


        //text_correo.addEventFilter(KeyEvent.ANY, validarCorreo);
        ObservableList<String> tipos = FXCollections.observableArrayList(
          "DNI", "Residencia", "Pasaporte"
        );

        tipoIdentificacion.setItems(tipos);
        llenarProvincias();


        ToggleGroup grupo = new ToggleGroup();
        rbVendedor.setToggleGroup(grupo);
        rbColeccionista.setToggleGroup(grupo);

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



    /*
    Metodos que realizan las funciones de ocultar o mostrar un Anchor Pane,
    dependiendo del paso en el que se encuentre.
     */
    public void onPrimerPaso(javafx.scene.input.MouseEvent event) {
        paso1.setVisible(true);
        paso2.setVisible(false);
        paso3.setVisible(false);
    }

    public void onSegundoPaso(MouseEvent event) {
        paso1.setVisible(false);
        paso2.setVisible(true);
        paso3.setVisible(false);
    }

    public void onTercerPaso(MouseEvent event) {
        paso1.setVisible(false);
        paso2.setVisible(false);
        paso3.setVisible(true);
    }


    public void  llenarProvincias() {
        administradorProvincias = new Provincias();
        provincias.setItems(administradorProvincias.getListaProvincias());
    }


    public void onComboProvinciaCambio(ActionEvent event) {
        cantones.setDisable(false);

        for (int i = 0; i< administradorProvincias.getTamannoP(); i++) {
            if (provincias.getValue().equals(administradorProvincias.getProvincia(i))) {
                cantones.setItems(administradorProvincias.getItemsCantones(i));
            }
        }
    }

    public void onComboCantonCambio(ActionEvent event) {
        distritos.setDisable(false);
            int cont = 0;
            for (int i = 0; i< administradorProvincias.getTamannoC(); i++) {
                ArrayList<String> canton = new ArrayList<>();
                for (String dato: administradorProvincias.getCanton(i) ) {
                    canton.add(dato);
                }
                for (String dato: canton) {
                    if (cantones.getValue().equals(dato)) {
                        distritos.setItems(administradorProvincias.getItemsDistritos(cont));
                    }
                    cont++;
                }
            }
    }

    public void onRadioUsuarioCambio(ActionEvent event) {
        if(rbVendedor.isSelected()) {
            intereses.setVisible(false);
        } else  if (rbColeccionista.isSelected()) {
            intereses.setVisible(true);
        }
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
        if (mes<0 || (mes==0 && dia < 0 )) {
            edad = edad - 1;
        }
        text_edad.setText(Integer.toString(edad));
    }

    @FXML
    private void guardarUsuario(ActionEvent event) throws IOException, SQLException {
        ControllerUsuario ctUsuario = new ControllerUsuario();

        boolean correcto;
        if(validarEspacios()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error!!");
            alert.setContentText("Revise los espacios en rojo");
            alert.showAndWait();
        } else {
            boolean identificacionRepetida;
            boolean correoRepetido;

            identificacionRepetida = ctUsuario.validarIdentificacion(text_id.getText());
            correoRepetido = ctUsuario.validarCorreo(text_correo.getText());

            if (identificacionRepetida) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error!!");
                alert.setContentText("La identificación ingresada ya se encuentra registrada en nuestro sistema.\n" +
                        " Ingrese una nueva identificación");
                alert.showAndWait();
                paso1.setVisible(false);
                paso2.setVisible(true);
                paso3.setVisible(false);
                avisoIdentificacion.setText("Esta identificación ya se encuentra registrada");
            } else if (correoRepetido) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error!!");
                alert.setContentText("El correo ingresado ya se encuentra registrado en el sistema.\n" +
                        " Ingrese un nuevo correo electrónico");
                alert.showAndWait();
                avisoCorreo.setText("Este correo ya se encuentra registrado en el sistema");

            } else {
                if (rbVendedor.isSelected() && !rbColeccionista.isSelected()) {


                    correcto = ctUsuario.registrarVendedor(text_nombre.getText(),
                            tipoIdentificacion.getValue().toString(),text_id.getText(),
                            LocalDate.parse(select_fecha.getValue().toString()),
                            Integer.parseInt(text_edad.getText()),text_correo.getText(),
                            text_contrasenna.getText(), provincias.getValue().toString(), cantones.getValue().toString(),
                            distritos.getValue().toString(),
                            text_direccion.getText());
                    if (!correcto) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Registro incorrecto");
                        alert.showAndWait();

                    }
                } else if(!rbVendedor.isSelected() && rbColeccionista.isSelected()){
                    ArrayList<String> listaIntereses;
                    listaIntereses = obtenerValoresIntereses();

                    ctUsuario.registrarColeccionista(text_nombre.getText(),
                            tipoIdentificacion.getValue().toString(),text_id.getText(),
                            LocalDate.parse(select_fecha.getValue().toString()),
                            Integer.parseInt(text_edad.getText()),text_correo.getText(),
                            text_contrasenna.getText(), provincias.getValue().toString(), cantones.getValue().toString(),
                            distritos.getValue().toString(),
                            text_direccion.getText(), listaIntereses);
                }
                irMenuInicio(event);
            }


        }
    }

    private ArrayList<String> obtenerValoresIntereses() {
        ArrayList<String> valores = new ArrayList<>();

        if (ckbAmbientales.isSelected()) {
            valores.add(ckbAmbientales.getText());
        }

        if(ckbAnimales.isSelected()) {
            valores.add(ckbAnimales.getText());
        }

        if (ckbArtesEscenicas.isSelected()) {
            valores.add(ckbArtesEscenicas.getText());
        }

        if (ckbArtesPlasticas.isSelected()) {
            valores.add(ckbArtesPlasticas.getText());
        }

        if (ckbArtesanales.isSelected()) {
            valores.add(ckbArtesanales.getText());
        }

        if (ckbEscritura.isSelected()) {
            valores.add(ckbEscritura.getText());
        }

        if (ckbFotografia.isSelected()) {
            valores.add(ckbHogar.getText());
        }

        if (ckbHogar.isSelected()) {
            valores.add(ckbHogar.getText());
        }

        if (ckbLectura.isSelected()) {
            valores.add(ckbLectura.getText());
        }

        if (ckbMedicina.isSelected()) {
            valores.add(ckbMedicina.getText());
        }

        if (ckbMusica.isSelected()) {
            valores.add(ckbMusica.getText());
        }

        if (ckbReligion.isSelected()) {
            valores.add(ckbReligion.getText());
        }

        if (ckbRompecabezas.isSelected()) {
            valores.add(ckbRompecabezas.getText());
        }

        if (ckbTecnologia.isSelected()) {
            valores.add(ckbTecnologia.getText());
        }
        return valores;
    }


    private boolean validarEspacios() {

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

            if(text_id.getText().isEmpty()) {
                avisoIdentificacion.setText("Debe ingresar una identificación");
                error = true;
            } else {

                switch (tipoIdentificacion.getValue().toString()) {
                    case "DNI":
                        if (!(text_id.getText().matches("\\d{9}"))) {
                            avisoIdentificacion.setText("La identificación debe tener 9 dígitos y sólo se admiten números");
                            error = true;
                        } else {
                            avisoIdentificacion.setText("");
                            error = false;
                        }
                        break;
                    case "Residencia":
                        if (!(text_id.getText().matches("\\d{10}"))) {
                            avisoIdentificacion.setText("La identificación debe tener 10 dígitos y sólo se admiten números");
                            error = true;
                        } else {
                            avisoIdentificacion.setText("");
                            error = false;
                        }
                        break;
                    case "Pasaporte":

                        if (!(text_id.getText().matches("[A-Z]{1}\\d{12}"))) {
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

        if(select_fecha.getValue()==null) {
            avisoFecha.setText("Debe ingresar una fecha");
            error = true;
        } else if (select_fecha.getValue().toString().matches("[a-zA-Z]")) {
            avisoFecha.setText("Debe ingresar una fecha válida");
            error = true;
        } else if (fechaHoy.isBefore(select_fecha.getValue())) {
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

        if (provincias.getValue()==null) {
            avisoProvincia.setText("Debe seleccionar una provincia");
            error = true;
        }else {
            avisoProvincia.setText("");
            if (cantones.getValue() == null) {
                avisoCanton.setText("Debe seleccionar un cantón");
                error = true;
            } else {
                avisoCanton.setText("");
                if (distritos.getValue() == null) {
                    avisoDireccion.setText("Debe seleccionar un distrito");
                    error = true;
                } else {
                    avisoDistrito.setText("");
                    error = false;
                }
            }
        }


        if (text_direccion.getText().isEmpty()) {
            avisoDireccion.setText("Debe ingresar la direccion exacta para continuar");
            error = true;
        } else {
            avisoDireccion.setText("");
            error = false;
        }

        if(!(rbVendedor.isSelected()) && rbColeccionista.isSelected()) {

            if (obtenerValoresIntereses().size()==0) {
                avisoIntereses.setText("Debe seleccionar al menos una de las casillas de los interéses");
                error = true;
            }
        }

        if (text_correo.getText().isEmpty()) {
            avisoCorreo.setText("Debe ingresar su correo electrónico");
            error = true;
        } else if (text_correo.getText().matches("[^@]+@[^@]+\\\\.[a-zA-Z]{2,}")) {
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

        return error;
    }
}

