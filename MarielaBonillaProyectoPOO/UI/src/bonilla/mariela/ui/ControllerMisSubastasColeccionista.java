package bonilla.mariela.ui;

import bonilla.mariela.bl.dao.DaoFactory;
import bonilla.mariela.bl.item.Item;
import bonilla.mariela.bl.subasta.ISubastaDao;
import bonilla.mariela.bl.subasta.InfoSubasta;
import bonilla.mariela.bl.subasta.Subasta;
import bonilla.mariela.bl.usuario.IUsuarioDao;
import bonilla.mariela.bl.usuario.Usuario;
import bonilla.mariela.bl.usuario.UsuarioIniciado;
import bonilla.mariela.tl.ControllerInfoSubasta;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

public class ControllerMisSubastasColeccionista implements Initializable {

    private double xSetOff;
    private double ySetOff;

    @FXML JFXButton verItemsSubastaC;
    @FXML JFXButton verInfoSubastaC;
    @FXML JFXButton verItemsSubastaP;
    @FXML JFXButton verInfoSubastaP;


    @FXML TableColumn<Subasta,String> columnFechaInicioC;
    @FXML TableColumn<Subasta,String> columnFechaVencimientoC;
    @FXML TableColumn<Subasta,String> columnTiempoFaltanteC;
    @FXML TableColumn<Subasta,String> columnCantidadItemsC;
    @FXML TableColumn<Subasta,String>  columnPrecioMinimoC;
    @FXML TableColumn <Subasta,String> columnPuntuacionC;


    @FXML TableColumn<Subasta,String> columnFechaInicioP;
    @FXML TableColumn<Subasta,String> columnFechaVencimientoP;
    @FXML TableColumn<Subasta,String> columnTiempoFaltanteP;
    @FXML TableColumn<Subasta,String> columnCantidadItemsP;
    @FXML TableColumn<Subasta,String>  columnPrecioMinimoP;
    @FXML TableColumn <Subasta,String> columnVendedorP;

    @FXML TableView<Subasta> tabla_mis_subastas_creadas;
    @FXML TableView<Subasta> tabla_mis_subastas_participadas;


    ObservableList<Subasta> listaSubastasCreadas ;
    ObservableList<Subasta> listaSubastasParticipadas ;

    @FXML
    public void cerrarApp() {
        Platform.exit();
        System.out.println("Se cerró la aplicación");
    }


    public void salirVentana(MouseEvent event) {
        Stage escenaPrincipal = (Stage)((Node) event.getSource()).getScene().getWindow();
        escenaPrincipal.setIconified(true);
    }

    /**
     * @author Mariela Bonilla
     * @param event : Obtiene el evento del botón al que se hace click para poder pasar a la escena
     *              de ir al menú del coleccionista
     * @throws IOException
     */
    public void irMenu(ActionEvent event) throws IOException {
        Stage escenaPrincipal = (Stage) ((Node) event.getSource()).getScene().getWindow();

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


    public void mouseClickedCreadas(MouseEvent e)
    {
        int fila = -1;
        fila = tabla_mis_subastas_creadas.getSelectionModel().selectedIndexProperty().get();

        if (fila > -1) {
            verInfoSubastaC.setDisable(false);
            verItemsSubastaC.setDisable(false);
            verInfoSubastaP.setDisable(true);
            verItemsSubastaP.setDisable(true);
        } else if (fila == -1) {
            verInfoSubastaC.setDisable(true);
            verItemsSubastaC.setDisable(true);
            verInfoSubastaP.setDisable(true);
            verItemsSubastaP.setDisable(true);
        }
    }

    public void mouseClickedParticipadas(MouseEvent e)
    {
        int fila = -1;
        fila = tabla_mis_subastas_participadas.getSelectionModel().selectedIndexProperty().get();

        if (fila > -1) {
            verInfoSubastaP.setDisable(false);
            verItemsSubastaP.setDisable(false);
            verInfoSubastaC.setDisable(true);
            verItemsSubastaC.setDisable(true);
        } else if (fila == -1) {
            verInfoSubastaP.setDisable(true);
            verItemsSubastaP.setDisable(true);
            verInfoSubastaC.setDisable(true);
            verItemsSubastaC.setDisable(true);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources){
        iniciarTablaCreadas();
        iniciarTablaParticipadas();
    }

    private void iniciarTablaCreadas() {
        iniciarColsCreadas();
    }
    private void iniciarTablaParticipadas() {
        iniciarColsParticipadas();
    }

    private void iniciarColsCreadas() {
        columnFechaInicioC.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
        columnFechaVencimientoC.setCellValueFactory(new PropertyValueFactory<>("fechaVencimiento"));
        columnTiempoFaltanteC.setCellValueFactory(new PropertyValueFactory<>("tiempoFaltante"));
        columnCantidadItemsC.setCellValueFactory(new PropertyValueFactory<>("cantidad_items"));
        columnPrecioMinimoC.setCellValueFactory(new PropertyValueFactory<>("precioMinimo"));
        listaSubastasCreadas = FXCollections.observableArrayList();
        listarSubastasCreadas();

        tabla_mis_subastas_creadas.setItems(listaSubastasCreadas);
    }

    private void iniciarColsParticipadas() {

        columnFechaInicioP.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
        columnFechaVencimientoP.setCellValueFactory(new PropertyValueFactory<>("fechaVencimiento"));
        columnTiempoFaltanteP.setCellValueFactory(new PropertyValueFactory<>("tiempoFaltante"));
        columnCantidadItemsP.setCellValueFactory(new PropertyValueFactory<>("cantidad_items"));
        columnPrecioMinimoP.setCellValueFactory(new PropertyValueFactory<>("precioMinimo"));
        columnVendedorP.setCellValueFactory(new PropertyValueFactory<>("nomVendedor"));

        listaSubastasParticipadas = FXCollections.observableArrayList();
        listarSubastasParticipadas();
        tabla_mis_subastas_participadas.setItems(listaSubastasParticipadas);

    }

    public void listarSubastasCreadas() {
        int cont = 0;

        try {
            ControllerSubasta gestorSuabsta = new ControllerSubasta();
            for (Subasta dato: gestorSuabsta.listarSubastasCreadas().values()){
                dato.setTiempoFaltante(calcularTiempoFaltante(dato.getFechaVencimiento()));
                listaSubastasCreadas.add(dato);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void listarSubastasParticipadas() {
        try {
            ControllerSubasta gestorSubasta = new ControllerSubasta();
            for (Subasta dato: gestorSubasta.listarSubastasParticipadas()){
                dato.setTiempoFaltante(calcularTiempoFaltante(dato.getFechaVencimiento()));
                listaSubastasParticipadas.add(dato);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * @author Mariela Bonilla
     * @param1 fechaVencimiento: Fecha que recibe de la subasta actual para calcular los años
     *          que faltan para su vencimiento
     * @version 1.0
     */
    private String calcularTiempoFaltante(LocalDate fechaVencimiento)  {
        LocalDate fechaActual = LocalDate.now();
        Period tiempo = Period.between(fechaActual,fechaVencimiento);
        String faltante = "";
        if (tiempo.getDays() <= 0 && tiempo.getMonths() <= 0 && tiempo.getYears() <= 0) {
            faltante = "0 años, 0 meses, 0 días";
        } if (tiempo.getDays() > 0 && tiempo.getMonths() <= 0 && tiempo.getYears() <= 0) {
            faltante = "0 años, 0 meses, " + tiempo.getDays() + " días";
        } else if (tiempo.getDays() > 0 && tiempo.getMonths() > 0 && tiempo.getYears() <= 0) {
            faltante = "0 años," + tiempo.getMonths() + " meses, "+tiempo.getDays()+" días";
        }
        else if (tiempo.getDays() > 0 && tiempo.getMonths() > 0 && tiempo.getYears() > 0) {
            faltante = tiempo.getYears()+" años," + tiempo.getMonths() + " meses, "+tiempo.getDays()+" días";
        }
       return faltante;
    }


    /**
     * @author Mariela Bonilla
     * @param event : Obtiene el evento al que el botón reacciona para poder obtener la
     *              escena actual
     * @throws IOException
     * @version 1.0
     */
    public void verInfoSubastaC(ActionEvent event) throws IOException {
        Stage escenaPrincipal = (Stage)((Node) event.getSource()).getScene().getWindow();

        int fila = -1;
        fila = tabla_mis_subastas_creadas.getSelectionModel().selectedIndexProperty().get();

        Subasta subasta = tabla_mis_subastas_creadas.getSelectionModel().getSelectedItem();

        ControllerInfoSubasta gestorInfoSubasta = new ControllerInfoSubasta();

        gestorInfoSubasta.guardarDatos(subasta.getId(),subasta.getFechaVencimiento(), subasta.getIdentificacionVendedor(),
                subasta.getPrecioMinimo(), subasta.getCantidad_items(), subasta.getEstado());



        Parent ruta = FXMLLoader.load(getClass().getResource("info_subasta_coleccionista.fxml"));

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


    public void verInfoSubastaP(ActionEvent event) throws IOException {
        Stage escenaPrincipal = (Stage)((Node) event.getSource()).getScene().getWindow();

        int fila = -1;
        fila = tabla_mis_subastas_participadas.getSelectionModel().selectedIndexProperty().get();

        Subasta subasta = tabla_mis_subastas_participadas.getSelectionModel().getSelectedItem();

        ControllerInfoSubasta gestorInfoSubasta = new ControllerInfoSubasta();

        gestorInfoSubasta.guardarDatos(subasta.getId(),subasta.getFechaVencimiento(), subasta.getIdentificacionVendedor(),
                subasta.getPrecioMinimo(), subasta.getCantidad_items(), subasta.getEstado());



        Parent ruta = FXMLLoader.load(getClass().getResource("info_subasta_coleccionista.fxml"));

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


    public void verItemsSubastaC(ActionEvent event) throws IOException {
        Stage escenaPrincipal = new Stage();

        int fila = -1;
        fila = tabla_mis_subastas_creadas.getSelectionModel().selectedIndexProperty().get();

        Subasta subasta = tabla_mis_subastas_creadas.getSelectionModel().getSelectedItem();

        ControllerInfoSubasta gestorInfoSubasta = new ControllerInfoSubasta();

        gestorInfoSubasta.guardarDatos(subasta.getId(),subasta.getFechaVencimiento(), subasta.getIdentificacionVendedor(),
                subasta.getPrecioMinimo(), subasta.getCantidad_items() ,subasta.getEstado());



        Parent ruta = FXMLLoader.load(getClass().getResource("items_subasta.fxml"));


        Scene nueva_escena = new Scene(ruta);

        escenaPrincipal.setScene(nueva_escena);
        escenaPrincipal.show();

    }


    public void verItemsSubastaP(ActionEvent event) throws IOException {
        Stage escenaPrincipal = new Stage();

        int fila = -1;
        fila = tabla_mis_subastas_participadas.getSelectionModel().selectedIndexProperty().get();

        Subasta subasta = tabla_mis_subastas_participadas.getSelectionModel().getSelectedItem();

        ControllerInfoSubasta gestorInfoSubasta = new ControllerInfoSubasta();

        gestorInfoSubasta.guardarDatos(subasta.getId(),subasta.getFechaVencimiento(), subasta.getIdentificacionVendedor(),
                subasta.getPrecioMinimo(), subasta.getCantidad_items() ,subasta.getEstado());



        Parent ruta = FXMLLoader.load(getClass().getResource("items_subasta.fxml"));


        Scene nueva_escena = new Scene(ruta);

        escenaPrincipal.setScene(nueva_escena);
        escenaPrincipal.show();

    }
}
