package bonilla.mariela.ui;

import bonilla.mariela.bl.item.Item;
import bonilla.mariela.bl.usuario.UsuarioIniciado;
import bonilla.mariela.tl.ControllerSubasta;
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

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class RegistrarSubastasVendedorController implements Initializable {


    @FXML Label avisoFechaVencimiento;
    @FXML Label avisoPrecio;
    @FXML Label avisoNombreItem;
    @FXML Label avisoDescripcionItem;
    @FXML Label avisoEstadoItem;
    @FXML Label avisoFechaCompra;
    @FXML Label avisoItems;



    @FXML JFXButton btn_agregar_item,
            btn_eliminar_item;

    @FXML TableView <Item> tabla_items_subastas;

    @FXML TableColumn<Item,String> nombre_itemS;
    @FXML TableColumn<Item,String> descripcion_itemS;
    @FXML TableColumn<Item,String> estado_itemS;
    @FXML TableColumn<Item,String> fechaCompra_itemS;
    @FXML TableColumn<Item,String> antiguedad_itemS;
    @FXML TableColumn<Item,String> column_anno;
    @FXML TableColumn<Item,String> column_mes;
    @FXML TableColumn<Item,String> column_dia;

    @FXML
    JFXComboBox item_estado_subasta;

    @FXML
    JFXButton ir_menu;

    @FXML  JFXTextField text_precio;



    @FXML JFXTextField get_nombre_item;

    @FXML JFXTextArea get_descripcion_item;

    @FXML JFXDatePicker select_fecha_vencimiento;
    @FXML JFXDatePicker get_fecha_compra_item;

     ObservableList<Item> items;


    private double xSetOff;
    private double ySetOff;



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
    private void irMenu(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();

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
                stage.setX(event.getScreenX()-xSetOff);
                stage.setY(event.getScreenY()-ySetOff);
            }
        });

        Scene nueva_escena = new Scene(ruta);
        stage.hide();
        stage.setScene(nueva_escena);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        llenarEstados();
        iniciarColumns();
    }

    public void mouseClicked(MouseEvent e)
    {
        int fila = -1;
         fila = tabla_items_subastas.getSelectionModel().selectedIndexProperty().get();

        if (fila > -1) {
           btn_eliminar_item.setDisable(false);
        } else if (fila == -1) {
            btn_eliminar_item.setDisable(true);
        }
    }

    @FXML
    private void eliminarItemSeleccionado(ActionEvent e) {
        Object object =  tabla_items_subastas.getSelectionModel().selectedItemProperty().get();
        int index = tabla_items_subastas.getSelectionModel().selectedIndexProperty().get();
        items.remove(index);
        btn_eliminar_item.setDisable(true);
    }

    public void iniciarColumns() {

        nombre_itemS.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        descripcion_itemS.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        estado_itemS.setCellValueFactory(new PropertyValueFactory<>("estado"));
        fechaCompra_itemS.setCellValueFactory(new PropertyValueFactory<>("fechaCompra"));
        column_anno.setCellValueFactory(new PropertyValueFactory<>("annoAntiguedad"));
        column_mes.setCellValueFactory(new PropertyValueFactory<>("mesAntiguedad"));
        column_dia.setCellValueFactory(new PropertyValueFactory<>("diaAntiguedad"));

        items = FXCollections.observableArrayList();
        tabla_items_subastas.setItems(items);

    }
    public void llenarEstados() {
        ObservableList<String> estados = FXCollections.observableArrayList(
                "Nuevo",
                "Usado",
                "Antigüo sin abrir"
        );

        item_estado_subasta.setItems(estados);


    }

    public void agregar_item_lista(ActionEvent event) throws IOException{

        if (validarEspaciosItems()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!!");
            alert.setContentText("Verifique los campos e inténtelo de nuevo");
            alert.show();
        } else {

            int anno = calcularAntiguedadAnnos(get_fecha_compra_item.getValue());
            int mes = calcularAntiguedadMeses(get_fecha_compra_item.getValue());
            int dia = calcularAntiguedadDias(get_fecha_compra_item.getValue());

            Item tmpItem = new Item();

            tmpItem.setNombre(get_nombre_item.getText());
            tmpItem.setDescripcion(get_descripcion_item.getText());
            tmpItem.setEstado(item_estado_subasta.getValue().toString());
            tmpItem.setFechaCompra(get_fecha_compra_item.getValue());
            tmpItem.setAnnoAntiguedad(anno);
            tmpItem.setMesAntiguedad(mes);
            tmpItem.setDiaAntiguedad(dia);
            items.add(tmpItem);

            get_nombre_item.clear();
            get_descripcion_item.clear();
            item_estado_subasta.getEditor().clear();
            item_estado_subasta.getEditor().setText("Estado");
            get_fecha_compra_item.getEditor().clear();
        }
       // select_fecha_vencimiento.setValue(LocalDate.now());
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

    private int calcularAntiguedadAnnos(LocalDate fechaCompra)  {
        LocalDate fechaActual = LocalDate.now();
        Period anno = Period.between(fechaCompra, fechaActual);
        return anno.getYears();
    }

    @FXML
    private void registrarSubasta(ActionEvent e) throws IOException{
        UsuarioIniciado usuarioIniciado = new UsuarioIniciado();
        ControllerSubasta gestorSubasta = new ControllerSubasta();
        ArrayList<Item> items_subastados = new ArrayList<>();
        boolean registrado;

        for (Item dato: tabla_items_subastas.getItems()) {
            items_subastados.add(dato);
        }

        if (validarEspacios()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Datos equivocados");
            alert.setContentText("Verifique que los datos ingresados son correctos");
            alert.showAndWait();
        } else {
            registrado = gestorSubasta.registrarSubastas(select_fecha_vencimiento.getValue(),
                    Double.parseDouble(text_precio.getText()),
                    items_subastados, items_subastados.size());

            if (registrado) {
                irMenu(e);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error!!");
                alert.setContentText("No se pudo registrar la subasta");
                alert.showAndWait();
            }
        }


    }



    public boolean validarEspacios() {
        boolean error = false;
        LocalDate fechaHoy = LocalDate.now();
        if (select_fecha_vencimiento.getEditor().getText().isEmpty()) {
            avisoFechaVencimiento.setText("Debe ingresar una fecha de vencimiento");
            error = true;
        }else if (fechaHoy.isAfter(select_fecha_vencimiento.getValue())) {
            avisoFechaVencimiento.setText("Debe ingresar una fecha posterior a la de hoy");
            error = true;
        }else {
            avisoFechaVencimiento.setText("");
            error = false;
        }

        if (text_precio.getText().equals("0")) {
            avisoPrecio.setText("Debe ingresar el precio mínimo de la subasta");
            error = true;
        }else if (!(text_precio.getText().matches("^[0-9]*$"))) {
            avisoPrecio.setText("Debe ingresar un precio válido");
            error = true;
        }else {
            avisoPrecio.setText("");
            error = false;

        }

        if (tabla_items_subastas.getItems().isEmpty()) {
            avisoItems.setText("Debe agregar ítems a la subasta antes de registrarla");
            error = true;
        }else {
            avisoItems.setText("");
            error = false;
        }



        return error;
    }

    public boolean validarEspaciosItems() throws IOException {
        boolean error = false;
        LocalDate fechaHoy = LocalDate.now();
        if (get_nombre_item.getText().isEmpty()) {
            avisoNombreItem.setText("Debe ingresar el nombre del ítem");
            error = true;
        }else if (get_nombre_item.getText().matches("^[0-9]*$")) {
            avisoNombreItem.setText("Debe ingresar un precio válido");
            error = true;
        }else {
            avisoNombreItem.setText("");
            error = false;

        }

        if (get_descripcion_item.getText().isEmpty()) {
            avisoDescripcionItem.setText("Debe ingresar la descripción del ítem");
            error = true;
        }else {
            avisoDescripcionItem.setText("");
            error = false;

        }



        if (item_estado_subasta.getValue() == null) {
            avisoEstadoItem.setText("Debe ingresar el estado del ítem");
            error = true;
        }else {
            avisoEstadoItem.setText("");
            error = false;

        }



        if (get_fecha_compra_item.getEditor().getText().isEmpty()) {
            avisoFechaCompra.setText("Debe ingresar la fecha de la compra del ítem");
            error = true;
        }else if (fechaHoy.isBefore(get_fecha_compra_item.getValue())) {
            avisoFechaCompra.setText("Debe ingresar una fecha anterior a la de hoy");
            error = true;
        }else {
            avisoFechaVencimiento.setText("");
            error = false;
        }
        return error;
    }
}
