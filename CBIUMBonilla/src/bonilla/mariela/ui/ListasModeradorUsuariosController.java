package bonilla.mariela.ui;

import bonilla.mariela.bl.dao.DaoFactory;
import bonilla.mariela.bl.usuario.IUsuarioDao;
import bonilla.mariela.bl.usuario.Usuario;
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
import java.util.ResourceBundle;

public class ListasModeradorUsuariosController implements Initializable {

    @FXML
    TableColumn<Usuario,String> columnTipoUsuario;
    @FXML TableColumn<Usuario,String> columnNombre;
    @FXML TableColumn<Usuario,String> columnId;
    @FXML TableColumn<Usuario,String> columnFecha;
    @FXML TableColumn<Usuario,String>  columnEdad;
    @FXML TableColumn <Usuario,String> columnCorreo;
    @FXML TableColumn <Usuario,String> columnProvincia;
    @FXML TableColumn <Usuario,String> columnCanton;
    @FXML TableColumn <Usuario,String> columnDistrito;
    @FXML TableColumn<Usuario,String> columnDireccion;
    @FXML TableColumn <Usuario,String> columnPuntuacion;


    @FXML
    TableView<Usuario> tableUsuarios;
    ObservableList<Usuario> listaUsuarios;


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
    private void irMenuListas(ActionEvent event) throws IOException {
        Stage escenaPrincipal = (Stage)((Node) event.getSource()).getScene().getWindow();

        Parent ruta = FXMLLoader.load(getClass().getResource("listas_moderador.fxml"));

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



    public void initialize(URL location, ResourceBundle resources){

        iniciarTablaUsuarios();
        listasUsuarios();

    }


    private void iniciarTablaUsuarios() {
        iniciarColsUsuarios();

    }

    private void iniciarColsUsuarios() {
        columnTipoUsuario.setCellValueFactory(new PropertyValueFactory<>("tipoUsuario"));
        columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnId.setCellValueFactory(new PropertyValueFactory<>("identificacion"));
        columnFecha.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        columnEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        columnCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        columnProvincia.setCellValueFactory(new PropertyValueFactory<>("provincia"));
        columnCanton.setCellValueFactory(new PropertyValueFactory<>("canton"));
        columnDistrito.setCellValueFactory(new PropertyValueFactory<>("distrito"));
        columnDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        columnPuntuacion.setCellValueFactory(new PropertyValueFactory<>("puntuacion"));


        listaUsuarios = FXCollections.observableArrayList();
        tableUsuarios.setItems(listaUsuarios);
    }

    public void listasUsuarios() {
        int cont = 0;

        try {
            DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.SQLSERVER);


            IUsuarioDao dao = factory.getUsuarioDao();
            for (Usuario dato : dao.listasDeUsuarios().values()) {

                listaUsuarios.add(dato);

            }

        }catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
