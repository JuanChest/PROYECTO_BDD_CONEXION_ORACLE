import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Cargar el archivo FXML del menú principal
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Interfaz/MenuPrincipal.fxml"));
        
        // Configurar la escena principal
        Scene scene = new Scene(root, 800, 600); // Tamaño inicial recomendado
        primaryStage.setTitle("Proyecto: Menu Principal");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // Este método inicia la aplicación JavaFX
    }
}