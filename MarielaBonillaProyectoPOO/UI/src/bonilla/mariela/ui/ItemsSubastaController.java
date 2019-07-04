package bonilla.mariela.ui;

import bonilla.mariela.bl.item.Item;
import bonilla.mariela.bl.oferta.Oferta;
import bonilla.mariela.bl.subasta.InfoSubasta;
import bonilla.mariela.bl.subasta.Subasta;
import bonilla.mariela.tl.Controller;
import bonilla.mariela.tl.ControllerItem;
import bonilla.mariela.tl.ControllerSubasta;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemsSubastaController implements Initializable {

    @FXML
    TableView<Item> tabla_items_subasta;


    @FXML
    TableColumn<Item,String> nombre_itemS;
    @FXML TableColumn<Item,String> descripcion_itemS;
    @FXML TableColumn<Item,String> estado_itemS;
    @FXML TableColumn<Item,String> fechaCompra_itemS;
    @FXML TableColumn<Item,String> antiguedad_itemS;
    @FXML TableColumn<Item,String> column_anno;
    @FXML TableColumn<Item,String> column_mes;
    @FXML TableColumn<Item,String> column_dia;

    ObservableList<Item> listaItemsSubasta;




    public void salirVentana(MouseEvent event) {
        Stage escenaPrincipal = (Stage)((Node) event.getSource()).getScene().getWindow();
        escenaPrincipal.setIconified(true);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iniciarTabla();
        listarItemsSubasta();
    }


    private void iniciarTabla() {
        iniciarColumnas();
    }

    private void iniciarColumnas() {
        nombre_itemS.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        descripcion_itemS.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        estado_itemS.setCellValueFactory(new PropertyValueFactory<>("estado"));
        fechaCompra_itemS.setCellValueFactory(new PropertyValueFactory<>("fechaCompra"));
        column_anno.setCellValueFactory(new PropertyValueFactory<>("annoAntiguedad"));
        column_mes.setCellValueFactory(new PropertyValueFactory<>("mesAntiguedad"));
        column_dia.setCellValueFactory(new PropertyValueFactory<>("diaAntiguedad"));

        listaItemsSubasta = FXCollections.observableArrayList();
        tabla_items_subasta.setItems(listaItemsSubasta);
    }

    @FXML
    private void listarItemsSubasta() {
        int cont = 0;
        ControllerItem gestorItems = new ControllerItem();

        try {
            for (Item dato: gestorItems.listarItemsSubasta(InfoSubasta.getId())){
                listaItemsSubasta.add(dato);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
