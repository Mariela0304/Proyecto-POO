/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package bonilla.mariela.ui;

import bonilla.mariela.bl.item.Item;
import bonilla.mariela.bl.subasta.InfoSubasta;
import bonilla.mariela.bl.subasta.Subasta;
import bonilla.mariela.bl.usuario.UsuarioIniciado;
import bonilla.mariela.tl.*;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * Esta clase va ser el controlador para que escena de la orden de compra pueda funcionar
 * @author Mariela Bonilla
 * @param1 posicion_fecha
 * @param2 posicion_nombre
 * @param3
 * @param4
 * @param5
 * @param6
 * @param6
 * @param7
 * @param8
 * @param9
 * @param10
 * @param11
 * @param12
 * @param13
 * @version 1.0
 */
public class OrdenCompraController implements Initializable {

    @FXML
    Label posicion_fecha;
    @FXML
    Label posicion_nombre;
    @FXML
    Label posicion_nombre_vendedor;
    @FXML
    Label posicion_id_vendedor;
    @FXML
    Label posicion_identificacion;


    @FXML
    Label posicion_num_orden;
    @FXML
    JFXTextField tarifa_total;

    @FXML
    TableView<Item> tabla_detalle_items;

    @FXML
    TableColumn<Item, String> columnDescripcion;
    @FXML
    TableColumn<Item, String> columnNombre;

    ObservableList<Item> listaItemsOrden;


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

        traerDatos();
        iniciarTabla();
        listarItemsSubasta();


    }


    public void traerDatos() {
        ControllerOferta gestorOferta = new ControllerOferta();
        String [] mejorOferta = gestorOferta.obtenerMejorOferta();
        UsuarioIniciado login = new UsuarioIniciado();
        posicion_nombre.setText(InfoSubasta.getNomVendedor());
        //obtener nombre del vendedor

        //traer fecha de orden
        posicion_fecha.setText(String.valueOf(LocalDate.now()));

        //traer numero de orden

        posicion_nombre.setText(login.getNombre());
        posicion_identificacion.setText(login.getIdentificacion());
        posicion_id_vendedor.setText(InfoSubasta.getIdentificacionVendedor());
        posicion_nombre_vendedor.setText(InfoSubasta.getNomVendedor());
        tarifa_total.setText(mejorOferta[1]);
    }




    private void iniciarTabla() {
        iniciarColumnas();
    }

    private void iniciarColumnas() {
        columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));


        listaItemsOrden = FXCollections.observableArrayList();
        tabla_detalle_items.setItems(listaItemsOrden);
    }

    @FXML
    private void listarItemsSubasta() {
        int cont = 0;
        ControllerItem gestorItems = new ControllerItem();

        try {
            for (Item dato: gestorItems.listarItemsSubasta(InfoSubasta.getId())){
                listaItemsOrden.add(dato);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}




