package bonilla.mariela.ui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

import javafx.stage.StageStyle;

public class Main extends Application {

    private Stage sitioPrincipal;
    private double xSetOff;
    private double ySetOff;

    @Override
    public void start(Stage escenaPrincipal) throws Exception {
        escenaPrincipal.initStyle(StageStyle.TRANSPARENT);

        Parent ruta = FXMLLoader.load(Main.class.getResource("sitio_inicio.fxml"));

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

        Scene escena = new Scene(ruta);
        escenaPrincipal.setScene(escena);
        escenaPrincipal.show();
    }

    public static void main(String[] args) {
      launch(args);

    }
}
