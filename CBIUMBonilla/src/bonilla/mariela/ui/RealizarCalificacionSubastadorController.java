/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package bonilla.mariela.ui;

import bonilla.mariela.tl.ControllerUsuario;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.controlsfx.control.Rating;

import java.text.DecimalFormat;

public class RealizarCalificacionSubastadorController {

    @FXML
    Rating estrellas;

    @FXML
    Label mensaje_votacion;

    @FXML
    JFXButton btn_calificar;

    public void calificar() {
        ControllerUsuario gestorUsuario = new ControllerUsuario();
        double valor;
        DecimalFormat df = new DecimalFormat("#.0");
        valor = estrellas.getRating();


        estrellas.setDisable(true);
        btn_calificar.setDisable(true);
        mensaje_votacion.setText("Usted ya ha realizado su voto");

        /*
        Falta de programar
         */

        gestorUsuario.realizarPuntuacionVendedor(valor);

    }
}
