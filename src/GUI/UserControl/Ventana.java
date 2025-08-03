package GUI.UserControl;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Ventana {
    public static void cambiarEscena(Stage stage, String rutaFXML, String tituloVentana) {
        try {
            FXMLLoader loader = new FXMLLoader(Ventana.class.getResource(rutaFXML));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(tituloVentana);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // También puedes usar un logger
        }
    }
}