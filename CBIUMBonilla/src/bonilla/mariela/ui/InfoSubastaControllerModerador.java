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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.Rating;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
    @FXML Text horas;
    @FXML Text minutos;
    @FXML Text segundos;


    @FXML
    JFXButton btn_vencer_subasta,
            btn_ver_items;

    @FXML
    TableView<Oferta> tabla_ofertas;
    @FXML
    TableColumn<Oferta,String> columnOferta;
    @FXML TableColumn<Oferta,String> columnOferente;




    int currSecond =0;
    Thread temporizador;


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
    public void initialize(URL location, ResourceBundle resources)
     {

        iniciarTablaOfertas();
        listarOfertas();
        ControllerOferta gestorOferta = new ControllerOferta();


        ControllerInfoSubasta gestorInfoSubasta = new ControllerInfoSubasta();

        String[] datosSubasta = gestorInfoSubasta.obtenerDatosSubasta();

        posicion_estado.setText(datosSubasta[7]);
        posicion_fecha.setText(datosSubasta[1]);
        posicion_precio.setText(datosSubasta[5]);
        calcularAnnos(LocalDate.parse(datosSubasta[1]));
        calcularMeses(LocalDate.parse(datosSubasta[1]));
        calcularDias(LocalDate.parse(datosSubasta[1]));

         String [] tiempo = calcularHoras(InfoSubasta.getTiempoFin());
         horas.setText(tiempo[0]);
         minutos.setText(tiempo[1]);
         segundos.setText(tiempo[2]);


         currSecond = hsmToSeconds(Integer.parseInt(tiempo[0]),
                 Integer.parseInt(tiempo[1]), Integer.parseInt(tiempo[2]));

         if (calcularTiempo(LocalDate.parse(datosSubasta[1]))) {
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Subasta vencida");
             alert.setContentText("Esta subasta ya ha vencido");
             alert.show();
             aviso_oferta.setText("La subasta ha vencido");
             posicion_estado.setText("Vencida");
             modificarEstadoSubasta();
             obtenerMejorOferta();
             alert.getOnHiding();
         } else {
             empezarTemporizador();
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
            dias.setText("0");
        } else {
            dias.setText(Integer.toString(dia.getDays()));
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
            meses.setText("0");
        } else {
            meses.setText(Integer.toString(mes.getMonths()));
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
            annos.setText("0");
        } else {
            annos.setText(Integer.toString(anno.getYears()));
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

        if (fecha.getYears() <= 0 &&fecha.getMonths() <= 0 && fecha.getDays() <= 0 &&
                horas.getText().equals("00")  && minutos.getText().equals("00") && segundos.getText().equals("00")) {
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


    private String[] calcularHoras(LocalTime tiempoVencimiento) {
        LocalTime tiempoHoy = LocalTime.now();
        String[]tiempoCalculado = new String[3];
        int horasTiempo=0;
        int minutosTiempo=0;
        int segundosTiempo=0;

        int horasHoy = tiempoHoy.getHour();
        int minutosHoy = tiempoHoy.getMinute();
        int segundosHoy = tiempoHoy.getSecond();

        int horasFin = tiempoVencimiento.getHour();
        int minutosFin = tiempoVencimiento.getMinute();
        int segundosFin = tiempoVencimiento.getSecond();

        if (horasFin>horasHoy) {
            horasTiempo = horasFin - horasHoy;
        } else if (horasFin == horasHoy){
            horasTiempo = 0;
            horas.setText("00");
        } else if (horasFin<horasHoy){
            horasTiempo = 24 - (horasHoy-horasFin);
        }

        if (minutosFin > minutosHoy){
            minutosTiempo = minutosFin - minutosHoy;
        } else if (minutosFin == minutosHoy){
            minutosTiempo = 00;
            minutos.setText("00");
        } else if (minutosFin<minutosHoy){
            minutosTiempo = 60 - (minutosHoy-minutosFin);
        }

        if (segundosFin > segundosHoy){
            segundosTiempo = segundosFin - segundosHoy;
        } else if (segundosFin == segundosHoy){
            segundosTiempo = 00;
            segundos.setText("00");
        } else if (segundosFin<segundosHoy){
            segundosTiempo = 60 - (segundosHoy-segundosFin);
        }

        tiempoCalculado[0] = Integer.toString(horasTiempo);
        tiempoCalculado[1] = Integer.toString(minutosTiempo);
        tiempoCalculado[2] = Integer.toString(segundosTiempo);
        return tiempoCalculado;
    }

    void empezarTemporizador() {
        temporizador = new Thread(new Runnable() {

            @Override
            public void run() {
                boolean seguir = true;
                int annos=0;
                int meses = 0;
                int dias = 0;
                try {
                    while (seguir) {
                        imprimirTemporizador();
                        Thread.sleep(1000);
                        if (currSecond == 0){
                            temporizador.stop();
                            seguir = false;

                        }
                        currSecond-=1;

                    }
                }catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            }
        });
        temporizador.start();
    }

    void imprimirTemporizador() {
        LinkedList<Integer> currHms = segundosAHms(currSecond);
             if (currHms.get(0) < 10) {
            horas.setText("0"+Integer.toString(currHms.get(0)));
        }else {
            horas.setText(Integer.toString(currHms.get(0)));
        }
        if (currHms.get(1) < 10) {
            minutos.setText("0"+Integer.toString(currHms.get(1)));
        }else {
            minutos.setText(Integer.toString(currHms.get(1)));
        }

        if (currHms.get(2) < 10) {
            segundos.setText("0"+Integer.toString(currHms.get(2)));
        }else {
            segundos.setText(Integer.toString(currHms.get(2)));
        }
    }



    Integer hsmToSeconds(int h, int m, int s) {
        int hASegundo = h * 3600;
        int mASegundo = m * 60;
        int total = hASegundo + mASegundo + s;
        return total;
    }

    Integer hsmAMinutos(int h, int m) {
        int hAMinuto = h * 3600;
        int total = hAMinuto + m;
        return total;
    }


    LinkedList<Integer> segundosAHms(int currSecond) {
        int horas = currSecond / 3600;
        currSecond = currSecond % 3600;
        int minutos = currSecond / 60;
        currSecond = currSecond % 60;
        int segundos = currSecond;
        LinkedList<Integer> listaTiempo = new LinkedList<>();
        listaTiempo.add(horas);
        listaTiempo.add(minutos);
        listaTiempo.add(segundos);

        return listaTiempo;
    }

}
