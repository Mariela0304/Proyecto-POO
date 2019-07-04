package bonilla.mariela.ui;

import bonilla.mariela.bl.item.Item;
import bonilla.mariela.bl.usuario.UsuarioIniciado;
import bonilla.mariela.tl.ControllerInicioSesion;
import bonilla.mariela.tl.ControllerItem;
import bonilla.mariela.tl.ControllerSubasta;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RegistrarSubastaColeccionistaController implements Initializable {

    @FXML
    Label avisoFecha,
            avisoPrecio,
            aviso_items_coleccion,
            aviso_items_subasta;
    @FXML
    JFXButton btn_agregar_item,
            btn_quitar_item;


    @FXML
    DatePicker text_fecha_vencimiento;
    @FXML
    TextField text_precio;

    @FXML
    TableView<Item> tabla_items_coleccion;

    @FXML
    TableColumn<Item, String> nombreItemColeccion;
    @FXML
    TableColumn<Item, String> descripcionItemColeccion;
    @FXML
    TableColumn<Item, String> estadoItemColeccion;
    @FXML
    TableColumn<Item, String> fechaItemColeccion;
    @FXML
    TableColumn<Item, String> annoItemColeccion;
    @FXML
    TableColumn<Item, String> mesItemColeccion;
    @FXML
    TableColumn<Item, String> diaItemColeccion;


    @FXML
    TableView<Item> tabla_items_subasta;

    @FXML
    TableColumn<Item, String> nombreItemSubasta;
    @FXML
    TableColumn<Item, String> descripcionItemSubasta;
    @FXML
    TableColumn<Item, String> estadoItemSubasta;
    @FXML
    TableColumn<Item, String> fechaItemSubasta;
    @FXML
    TableColumn<Item, String> annoItemSubasta;
    @FXML
    TableColumn<Item, String> mesItemSubasta;
    @FXML
    TableColumn<Item, String> diaItemSubasta;

    private ObservableList<Item> itemsSubasta;
    private ObservableList<Item> itemsColeccion;


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



    @FXML
    private void irMenu(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

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
                stage.setX(event.getScreenX() - xSetOff);
                stage.setY(event.getScreenY() - ySetOff);
            }
        });

        Scene nueva_escena = new Scene(ruta);
        stage.hide();
        stage.setScene(nueva_escena);
        stage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iniciarTablaMisItems();
        iniciarTablaItemsASubastar();

        text_precio.addEventHandler(KeyEvent.KEY_TYPED, event -> sinLetras(event));
    }

    private void sinLetras(KeyEvent keyEvent) {

        try {
            char key = keyEvent.getCharacter().charAt(0);
            if (!Character.isDigit(key))
                keyEvent.consume();

        } catch (Exception ex) {
        }

    }


    private void iniciarTablaMisItems() {
        iniciarColumnasMisItems();
    }

    private void iniciarColumnasMisItems() {
        nombreItemColeccion.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        descripcionItemColeccion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        estadoItemColeccion.setCellValueFactory(new PropertyValueFactory<>("estado"));
        fechaItemColeccion.setCellValueFactory(new PropertyValueFactory<>("fechaCompra"));
        annoItemColeccion.setCellValueFactory(new PropertyValueFactory<>("annoAntiguedad"));
        mesItemColeccion.setCellValueFactory(new PropertyValueFactory<>("mesAntiguedad"));
        annoItemColeccion.setCellValueFactory(new PropertyValueFactory<>("diaAntiguedad"));

        itemsColeccion = FXCollections.observableArrayList();
        tabla_items_coleccion.setItems(itemsColeccion);
        listarMisItems();

    }

    private void iniciarTablaItemsASubastar() {
        iniciarColumnasItemsASubastar();
    }

    private void iniciarColumnasItemsASubastar() {
        nombreItemSubasta.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        descripcionItemSubasta.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        estadoItemSubasta.setCellValueFactory(new PropertyValueFactory<>("estado"));
        fechaItemSubasta.setCellValueFactory(new PropertyValueFactory<>("fechaCompra"));
        annoItemSubasta.setCellValueFactory(new PropertyValueFactory<>("annoAntiguedad"));
        mesItemSubasta.setCellValueFactory(new PropertyValueFactory<>("mesAntiguedad"));
        diaItemSubasta.setCellValueFactory(new PropertyValueFactory<>("diaAntiguedad"));


        itemsSubasta = FXCollections.observableArrayList();
        tabla_items_subasta.setItems(itemsSubasta);
    }


    private void listarMisItems() {
        ControllerItem gestorItem = new ControllerItem();
        for (Item dato : gestorItem.listarItemsColeccionista()) {
            itemsColeccion.add(dato);
        }
    }


    @FXML
    private void registrarSubasta(ActionEvent e) throws IOException {
        ControllerItem gestorItem = new ControllerItem();
        ControllerSubasta gestorSubasta = new ControllerSubasta();
        ArrayList<Item> items_subastados = new ArrayList<>();
        boolean errorBlancos;
        errorBlancos = validarCampos();

        boolean registrado;


        if (!(tabla_items_coleccion.getItems() == null)) {
            gestorItem.eliminarTablaItems();
            for (Item dato : tabla_items_coleccion.getItems()) {
                gestorItem.registrarItem(dato.getNombre(), dato.getDescripcion(), dato.getDescripcion(), dato.getFechaCompra());
            }
        }

        for (Item dato : tabla_items_subasta.getItems()) {
            items_subastados.add(dato);
        }


        registrado = gestorSubasta.registrarSubastas(text_fecha_vencimiento.getValue(),
                Double.parseDouble(text_precio.getText()),
                items_subastados, items_subastados.size());

        if (registrado) {
            irMenu(e);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error!!");
            alert.setContentText("Revise los espacios en rojo");
            alert.showAndWait();
        }
    }


    @FXML
    public void mouseClickedColeccion(MouseEvent e)
    {
        int fila = -1;
        fila = tabla_items_coleccion.getSelectionModel().selectedIndexProperty().get();

        if (fila > -1) {
            btn_agregar_item.setDisable(false);
        } else if (fila == -1) {
            btn_agregar_item.setDisable(true);
        }
    }


    @FXML
    public void mouseClickedSubasta(MouseEvent e)
    {
        int fila = -1;
        fila = tabla_items_subasta.getSelectionModel().selectedIndexProperty().get();

        if (fila > -1) {
            btn_quitar_item.setDisable(false);
        } else if (fila == -1) {
            btn_quitar_item.setDisable(true);
        }
    }


    @FXML
    private void eliminarItemSeleccionado(ActionEvent e) {
        Object object =  tabla_items_subasta.getSelectionModel().selectedItemProperty().get();

        int index = tabla_items_subasta.getSelectionModel().selectedIndexProperty().get();
        itemsColeccion.add(itemsSubasta.get(index));

        itemsSubasta.remove(index);
        btn_quitar_item.setDisable(true);

    }


    @FXML
    private void pasarItemSubasta(ActionEvent e) {
        Object object =  tabla_items_coleccion.getSelectionModel().selectedItemProperty().get();
        int index = tabla_items_coleccion.getSelectionModel().selectedIndexProperty().get();
        itemsSubasta.add(itemsColeccion.get(index));

        itemsColeccion.remove(index);
        btn_agregar_item.setDisable(true);

    }


    public boolean validarCampos(){
        boolean error = false;
        LocalDate fechaHoy = LocalDate.now();

        if(text_fecha_vencimiento.getValue()==null) {
            avisoFecha.setText("Debe ingresar una fecha");
            error = true;
        } else if (text_fecha_vencimiento.getValue().toString().matches("[a-zA-Z]")) {
            avisoFecha.setText("Debe ingresar una fecha válida");
            error = true;
        } else if (fechaHoy.isAfter(text_fecha_vencimiento.getValue())) {
            avisoFecha.setText("Debe ingresar una fecha superior a la de hoy");
            error = true;
        }else {
            avisoFecha.setText("");
            error = false;
        }

        if(text_precio.getText().isEmpty()) {
            avisoPrecio.setText("Debe ingresarle un valor a la subasta");
            error = true;
        }else {
            avisoPrecio.setText("");
            error = false;
        }
        return error;
    }

}
