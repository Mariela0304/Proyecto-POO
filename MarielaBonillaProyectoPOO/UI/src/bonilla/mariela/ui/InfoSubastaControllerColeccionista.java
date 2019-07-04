package bonilla.mariela.ui;

import bonilla.mariela.bl.oferta.Oferta;
import bonilla.mariela.bl.subasta.Subasta;
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
import java.sql.Time;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Este controlador va a permitir el uso de la escena de la información de la subasta seleccionada
 *
 * @author Mariela Bonilla
 * @param1 xSetOff : Valor que va a recibir la coordenada X de una escena
 * @param2 ySetOff : Valor que va a recibir la coordenada Y de una escena
 * @param3 annos : Label en donde irá la cantidad de años que faltan para que se venza la subasta
 * @param4 meses : Label en donde irá la cantidad de meses que faltan para que se venza la subasta
 * @param5 dias : Label en donde irá la cantidad de días que falta para que se venza la subasta
 * @param6 posicion_precio : Label en donde el precio de la subasta
 * @param7 posicion_estado : Label en donde el estado de la subasta
 * @param8 posicion_fecha Label en donde la fecha de vencimiento de la subasta
 * @param9 aviso_oferta : Label en va a avisar si ya se hizo o no la oferta
 * @param10 btn_ir_ofertar : Botón para abrir la escena donde ofertar
 * @param11 btn_ver_items : Botón para abrir la escena donde se ven los ítems de esa subasta
 * @param12 columnOferta : Columna donde se verán las ofertas realizadas
 * @param13 columnOferente : Columna donde se verán los nombres del coleccionista
 * @param14 tabla_ofertas : Tabla donde se situará la lista de las ofertas de esa subasta
 * @param15 listaOfertas : Lista observable que almacenará las subastas
 * @version 1.0
 */
public class InfoSubastaControllerColeccionista implements Initializable {

    private double xSetOff;
    private double ySetOff;


    @FXML Label annos;
    @FXML Label meses;
    @FXML Label dias;
    @FXML Label posicion_precio;
    @FXML Label posicion_estado;
    @FXML Label posicion_fecha;
    @FXML Label aviso_oferta;


    @FXML JFXButton btn_ir_ofertar;
    @FXML JFXButton  btn_ver_items;
    @FXML JFXButton btn_calificar_vendedor;

    @FXML TableColumn<Oferta,String> columnOferta;
    @FXML TableColumn<Oferta,String> columnOferente;
    @FXML TableView<Oferta> tabla_ofertas;

    ObservableList<Oferta> listaOfertas;


    /**
     * Permite que se cierre la plataforma
     * @author Mariela Bonilla
     * @version 1.0
     */
    @FXML
    public void cerrarApp() {
        Platform.exit();
        System.out.println("Se cerró la aplicación");
    }


    /**
     * Permite la apertura de una nueva escena, donde se podrán ver los ítems de la subasta
     * @author Mariela Bonilla
     * @param1 event: Evento que va a recibir cuando el mouse le de click al botón
     * @version 1.0
     */
    public void salirVentana(MouseEvent event) {
        Stage escenaPrincipal = (Stage)((Node) event.getSource()).getScene().getWindow();
        escenaPrincipal.setIconified(true);
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iniciarTablaOfertas();
        listarOfertas();
        //traer la identificaciuon del usuario y preguntar si esa identificacion se encuientra entre los oferente,
        //si está =
        //btn_ir_ofertar.Disable(true)
        validarVendedor();
        ControllerOferta gestorOferta = new ControllerOferta();

        if (gestorOferta.validarOferente()) {
            btn_ir_ofertar.setDisable(true);
            aviso_oferta.setText("Ya ha realizado su oferta");
        }

        ControllerInfoSubasta gestorInfoSubasta = new ControllerInfoSubasta();

        String [] datosSubasta = gestorInfoSubasta.obtenerDatosSubasta();

        posicion_estado.setText(datosSubasta[5]);
        posicion_fecha.setText(datosSubasta[1]);
        posicion_precio.setText(datosSubasta[3]);
        calcularAnnos(LocalDate.parse(datosSubasta[1]));
        calcularMeses(LocalDate.parse(datosSubasta[1]));
        calcularDias(LocalDate.parse(datosSubasta[1]));

        if (calcularTiempo(LocalDate.parse(datosSubasta[1]))) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Subasta vencida");
            alert.setContentText("Esta subasta ya ha vencido");
            alert.show();
            btn_ir_ofertar.setDisable(true);
            aviso_oferta.setText("La subasta ha vencido");
            posicion_estado.setText("Subasta vencida");
            modificarEstadoSubasta();
        }

        validarGanador();


    }

    /**
     * Validara si el usuario actual es el ganador de la subasta y poder darle la oportunidad de calificarlo
     * @author Mariela Bonilla
     * @version 1.0
     */
    public void validarGanador() {
        ControllerSubasta gestorSubasta = new ControllerSubasta();

        if (gestorSubasta.validarGanador()){
            btn_calificar_vendedor.setDisable(false);
        } else {
            btn_calificar_vendedor.setDisable(true);
        }
    }

    /**
     * Validara si el usuario actual es el vendedor de la subasta y ver si puede ofertar o no
     * @author Mariela Bonilla
     * @version 1.0
     */
    public void validarVendedor() {
        ControllerSubasta gestorSubasta = new ControllerSubasta();

        if (gestorSubasta.validarVendedor()){
            btn_ir_ofertar.setDisable(true);
            aviso_oferta.setText("Usted es el creador de la subasta");

        } else {
            btn_ir_ofertar.setDisable(false);
        }
    }

    /**
     * Permite la apertura de una nueva escena, donde se podrán ver los ítems de la subasta
     * @author Mariela Bonilla
     * @version 1.0
     */
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

    /**
     * Permite la apertura de una nueva escena, donde se podrá realizar la oferta
     * @author Mariela Bonilla
     * @version 1.0
     */
    @FXML
    private void mostrarEscenaOferta() throws IOException {
        Stage escenaOferta = new Stage();


        Scene nuevaEscena;

        Parent ruta = FXMLLoader.load(getClass().getResource("realizar_oferta.fxml"));

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
                escenaOferta.setX(event.getScreenX()-xSetOff);
                escenaOferta.setY(event.getScreenY()-ySetOff);
            }
        });

        escenaOferta.setScene(nuevaEscena);
        escenaOferta.show();
    }



    /**
     * Permite la apertura de una nueva escena, donde se podrá realizar la calificación al vendedor
     * @author Mariela Bonilla
     * @version 1.0
     */
    @FXML
    private void mostrarEscenaCalificar() throws IOException {
        Stage escenaOferta = new Stage();

        Scene nuevaEscena;

        Parent ruta = FXMLLoader.load(getClass().getResource("realizar_calificacion_a_subastador.fxml"));

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
                escenaOferta.setX(event.getScreenX()-xSetOff);
                escenaOferta.setY(event.getScreenY()-ySetOff);
            }
        });

        escenaOferta.setScene(nuevaEscena);
        escenaOferta.show();
    }



    public void irMenu(ActionEvent event) throws IOException {
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

    public void irSubastasDisponibles(ActionEvent event) throws IOException {
        Stage escenaPrincipal = (Stage)((Node) event.getSource()).getScene().getWindow();

        Parent ruta = FXMLLoader.load(getClass().getResource("subastas_disponibles.fxml"));

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

    private boolean calcularTiempo(LocalDate fechaVencimiento) {
        boolean tiempoAcabado=false;
        LocalDate fechaActual = LocalDate.now();
        Period fecha = Period.between(fechaActual, fechaVencimiento);

        if (fecha.getYears() <= 0 &&fecha.getMonths() <= 0 && fecha.getDays() <= 0) {
            tiempoAcabado=true;
        }

        return tiempoAcabado;
    }




}
