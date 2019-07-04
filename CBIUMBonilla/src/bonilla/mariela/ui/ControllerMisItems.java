package bonilla.mariela.ui;

import bonilla.mariela.bl.item.Item;
import bonilla.mariela.tl.ControllerItem;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMisItems implements Initializable {

    private double xSetOff;
    private double ySetOff;

    @FXML
    TableView<Item> table_mis_items;

    @FXML
    TableColumn<Item,String> column_nombre;
    @FXML TableColumn<Item,String> column_descripcion;
    @FXML TableColumn<Item,String> column_estado;
    @FXML TableColumn<Item,String> column_fecha;
    @FXML TableColumn<Item,String> column_anno;
    @FXML TableColumn<Item,String> column_mes;
    @FXML TableColumn<Item,String> column_dia;

    ObservableList<Item> listaItems;

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
            iniciarTabla();
    }

    public void iniciarTabla() {
        iniciarColumns();
    }

    public void iniciarColumns() {

        column_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        column_descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        column_estado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        column_fecha.setCellValueFactory(new PropertyValueFactory<>("fechaCompra"));
        column_anno.setCellValueFactory(new PropertyValueFactory<>("annoAntiguedad"));
        column_mes.setCellValueFactory(new PropertyValueFactory<>("mesAntiguedad"));
        column_dia.setCellValueFactory(new PropertyValueFactory<>("diaAntiguedad"));

        listaItems = FXCollections.observableArrayList();
        listarItems();
        table_mis_items.setItems(listaItems);
    }

    public void listarItems() {
        ControllerItem gestorItem = new ControllerItem();
        for (Item dato: gestorItem.listarItemsColeccionista()){
            listaItems.add(dato);
        }
    }


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
    private void irRegistrarItem(ActionEvent event) throws IOException {
        Stage escenaPrincipal = (Stage)((Node) event.getSource()).getScene().getWindow();

        Parent ruta = FXMLLoader.load(getClass().getResource("registrar_items.fxml"));

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
