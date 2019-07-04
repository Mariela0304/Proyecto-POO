package bonilla.mariela.ui;

import bonilla.mariela.bl.oferta.Oferta;
import bonilla.mariela.bl.subasta.InfoSubasta;
import bonilla.mariela.tl.ControllerInfoSubasta;
import bonilla.mariela.tl.ControllerOferta;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.Rating;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

public class InfoSubastaControllerModerador implements Initializable {
    private double xSetOff;
    private double ySetOff;
    @FXML Label aviso_oferta;


    @FXML
    Label   posicion_precio,
            posicion_estado,
            posicion_fecha;


    @FXML Label annos;
    @FXML Label meses;
    @FXML Label dias;


    @FXML
    JFXButton btn_vencer_subasta,
            btn_ver_items;

    @FXML
    TableView<Oferta> tabla_ofertas;
    @FXML
    TableColumn<Oferta,String> columnOferta;
    @FXML TableColumn<Oferta,String> columnOferente;


    ObservableList<Oferta> listaOfertas;
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

        iniciarTablaOfertas();
        listarOfertas();
        ControllerOferta gestorOferta = new ControllerOferta();


        ControllerInfoSubasta gestorInfoSubasta = new ControllerInfoSubasta();

        String[] datosSubasta = gestorInfoSubasta.obtenerDatosSubasta();

        posicion_estado.setText(datosSubasta[5]);
        posicion_fecha.setText(datosSubasta[1]);
        posicion_precio.setText(datosSubasta[3]);
        calcularAnnos(LocalDate.parse(datosSubasta[1]));
        calcularMeses(LocalDate.parse(datosSubasta[1]));
        calcularDias(LocalDate.parse(datosSubasta[1]));

        if (calcularTiempo(LocalDate.parse(datosSubasta[1]))) {
            modificarEstadoSubasta();
            obtenerMejorOferta();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Subasta vencida");
            alert.setContentText("Esta subasta ya ha vencido");
            alert.show();
            aviso_oferta.setText("La subasta ha vencido");
            posicion_estado.setText("Subasta vencida");

        } else if (InfoSubasta.getEstado().equals("Vencida")) {
            btn_vencer_subasta.setDisable(false);
            posicion_estado.setText(InfoSubasta.getEstado());
            obtenerMejorOferta();
        }
    }

    public void obtenerMejorOferta() {
        ControllerSubasta gestorSubasta = new ControllerSubasta();
        double mejorOferta = 0;
        for (Oferta dato: listaOfertas) {
            dato.getIdentificacionOferente();
            if (mejorOferta < dato.getPrecio()){
                mejorOferta = dato.getPrecio();
                InfoSubasta.setIdentificacionGanador(dato.getIdentificacionOferente());
            }
        }
    }

    /**
     * @author Mariela Bonilla
     * @version 1.0
     */
    private void modificarEstadoSubasta() {
        try {
            ControllerSubasta gestorSubasta = new ControllerSubasta();
            gestorSubasta.vencerSubasta();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void ver_items_subasta() throws IOException {
        Stage escenaItemsSubasta = new Stage();
        Scene nuevaEscena;

        Parent ruta = FXMLLoader.load(getClass().getResource("items_subasta.fxml"));

        nuevaEscena = new Scene(ruta);

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
                escenaItemsSubasta.setX(event.getScreenX()-xSetOff);
                escenaItemsSubasta.setY(event.getScreenY()-ySetOff);
            }
        });

        escenaItemsSubasta.setScene(nuevaEscena);
        escenaItemsSubasta.show();
    }


    @FXML
    private void marcarSubastaVencida() throws IOException {
        ControllerSubasta gestorSubasta  = new ControllerSubasta();
        gestorSubasta.vencerSubasta();
        btn_vencer_subasta.setDisable(true);
        System.out.println("Subasta vencida");
        posicion_estado.setText("Vencida");
        obtenerMejorOferta();
        modificarEstadoSubasta();
    }

    /**
     * Inicia la tabla de las ofertas
     * @author Mariela Bonilla
     * @version 1.0
     */
    public void iniciarTablaOfertas() {

        iniciarColumnasOfertas();
    }

    /**
     * Inicializa las columnas de la tabla de ofertas
     * @author Mariela Bonilla
     * @version 1.0
     */
    public void iniciarColumnasOfertas() {
        columnOferta.setCellValueFactory(new PropertyValueFactory<>("precio"));
        columnOferente.setCellValueFactory(new PropertyValueFactory<>("nomOferente"));

        listaOfertas= FXCollections.observableArrayList();

        tabla_ofertas.setItems(listaOfertas);
    }

    /**
     * Hace la ejecucion para que se liste la informacion de las ofertas de esa subasta
     * @author Mariela Bonilla
     * @version 1.0
     */
    public void listarOfertas() {
        try {
            ControllerOferta gestorOferta = new ControllerOferta();
            for (Oferta dato: gestorOferta.listarOfertasSubasta()){
                listaOfertas.add(dato);
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
    private void calcularDias(LocalDate fechaVencimiento)  {
        LocalDate fechaActual = LocalDate.now();
        Period dia = Period.between(fechaActual,fechaVencimiento);
        if (dia.getDays() <= 0) {
            dias.setText("0 días");
        } else {
            dias.setText(dia.getDays()+" días");
        }
    }

    /**
     * @author Mariela Bonilla
     * @param1 fechaVencimiento: Fecha que recibe de la subasta actual para calcular los años
     *          que faltan para su vencimiento
     * @version 1.0
     */
    private void calcularMeses(LocalDate fechaVencimiento)  {
        LocalDate fechaActual = LocalDate.now();
        Period mes = Period.between(fechaActual,fechaVencimiento);
        if (mes.getMonths() <= 0) {
            meses.setText("0 meses");
        } else {
            meses.setText(mes.getMonths()+" meses");
        }


    }

    /**
     * @author Mariela Bonilla
     * @param1 fechaVencimiento: Fecha que recibe de la subasta actual para calcular los años
     *          que faltan para su vencimiento
     * @version 1.0
     */

    private void calcularAnnos(LocalDate fechaVencimiento)  {
        LocalDate fechaActual = LocalDate.now();
        Period anno = Period.between(fechaActual,fechaVencimiento);
        if (anno.getYears() <= 0) {
            annos.setText("0 años");
        } else {
            annos.setText(anno.getYears()+" años");
        }
    }



    /**
     * @author Mariela Bonilla
     * @param1 fechaVencimiento: Fecha que recibe de la subasta actual para saber calcular el tiempo
     * que falta para vencer
     * @version 1.0
     */
    private boolean calcularTiempo(LocalDate fechaVencimiento) {
        boolean tiempoAcabado=false;
        LocalDate fechaActual = LocalDate.now();
        Period fecha = Period.between(fechaActual, fechaVencimiento);

        if (fecha.getYears() <= 0 &&fecha.getMonths() <= 0 && fecha.getDays() <= 0) {
            tiempoAcabado=true;
        }

        return tiempoAcabado;
    }


    public void irMenu(ActionEvent event) throws IOException {
        Stage escenaPrincipal = (Stage)((Node) event.getSource()).getScene().getWindow();

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
                escenaPrincipal.setX(event.getScreenX()-xSetOff);
                escenaPrincipal.setY(event.getScreenY()-ySetOff);
            }
        });

        Scene nueva_escena = new Scene(ruta);
        escenaPrincipal.hide();
        escenaPrincipal.setScene(nueva_escena);
        escenaPrincipal.show();

    }

    public void irListasSubastas(ActionEvent event) throws IOException {
        Stage escenaPrincipal = (Stage)((Node) event.getSource()).getScene().getWindow();

        Parent ruta = FXMLLoader.load(getClass().getResource("listas_moderador_subastas.fxml"));

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
