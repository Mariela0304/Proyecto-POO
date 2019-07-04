/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package bonilla.mariela.ui;

import bonilla.mariela.bl.dao.DaoFactory;
import bonilla.mariela.bl.subasta.ISubastaDao;
import bonilla.mariela.bl.subasta.InfoSubasta;
import bonilla.mariela.bl.subasta.Subasta;
import bonilla.mariela.bl.usuario.Usuario;
import bonilla.mariela.bl.usuario.UsuarioIniciado;
import bonilla.mariela.tl.ControllerInfoSubasta;
import bonilla.mariela.tl.ControllerOrdenCompra;
import bonilla.mariela.tl.ControllerSubasta;
import bonilla.mariela.tl.ControllerUsuario;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class AdjudicacionesPendientesController implements Initializable {

    @FXML
    TableColumn<Subasta,String> columnDetalle;
    @FXML TableColumn<Subasta,String> columnSubasta;

    @FXML
    TableView<Subasta> tabla_adjudicaciones;
    ObservableList<Subasta> listaAdjudicaciones;

    @FXML
    JFXButton verItemsSubasta;
    @FXML JFXButton verOfertasSubasta;
    @FXML JFXButton aceptarAdjudicacion;
    @FXML JFXButton verInfoSubasta;

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iniciarCols();
        listasAdjudicaciones();
    }

    private void iniciarTabla() {
        iniciarCols();
    }

    private void iniciarCols() {

        columnDetalle.setCellValueFactory(new PropertyValueFactory<>("detalleAdjudicacion"));
        columnSubasta.setCellValueFactory(new PropertyValueFactory<>("id"));


        listaAdjudicaciones = FXCollections.observableArrayList();
        tabla_adjudicaciones.setItems(listaAdjudicaciones);
    }


    public void listasAdjudicaciones() {
        try {
            UsuarioIniciado login = new UsuarioIniciado();
            DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.SQLSERVER);
            ISubastaDao dao = factory.getSubastaDao();

            for (Subasta dato:  dao.listarAdjudicacionesSubastas(login.getIdentificacion())) {

                dato.setDetalleAdjudicacion("En hora buena! Ha ganado en una subasta, seleccione esta fila y cliquée " +
                        "al botón de 'Aceptar' para ver la orden de compra");

               listaAdjudicaciones.add(dato);
            }
        }catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void mouseClickedDisponibles(MouseEvent e)
    {
        int fila = -1;
        fila = tabla_adjudicaciones.getSelectionModel().selectedIndexProperty().get();

        if (fila > -1) {
            aceptarAdjudicacion.setDisable(false);

        } else if (fila == -1) {
            aceptarAdjudicacion.setDisable(true);

        }
    }

    public void aceptarAdjudicacion(ActionEvent event) throws IOException {
        ControllerOrdenCompra gestorOrdenCompra = new ControllerOrdenCompra();
        ControllerSubasta gestorSubasta = new ControllerSubasta();
        ControllerInfoSubasta gestorInfoSubasta = new ControllerInfoSubasta();

        Stage escenaPrincipal = new Stage();

        int fila = -1;
        fila = tabla_adjudicaciones.getSelectionModel().selectedIndexProperty().get();

        Subasta subasta = tabla_adjudicaciones.getSelectionModel().getSelectedItem();


        InfoSubasta.setId(subasta.getId());
        gestorInfoSubasta.guardarDatos(subasta.getId(),subasta.getFechaVencimiento(), subasta.getIdentificacionVendedor(),
                subasta.getPrecioMinimo(), subasta.getCantidad_items() ,subasta.getEstado());

        InfoSubasta.setId(subasta.getId());
        InfoSubasta.setNum_orden_compra(18236);
        InfoSubasta.setNomVendedor(subasta.getNomVendedor());
        InfoSubasta.setIdentificacionVendedor(subasta.getIdentificacionVendedor());


        Parent ruta = FXMLLoader.load(getClass().getResource("orden_compra.fxml"));


        Scene nueva_escena = new Scene(ruta);

        escenaPrincipal.setScene(nueva_escena);
        escenaPrincipal.show();

    }

}
