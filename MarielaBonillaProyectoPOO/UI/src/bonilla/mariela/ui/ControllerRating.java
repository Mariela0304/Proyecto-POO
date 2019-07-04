package bonilla.mariela.ui;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.controlsfx.control.Rating;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerRating implements Initializable {

    @FXML Rating estrellas;

    @FXML JFXButton btn_calificar;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
