import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Cargar el FXML
        Parent root = FXMLLoader.load(getClass().getResource(
                "GUI/Interfaz/MenuPrincipal.fxml"));

        Scene scene = new Scene(root);
        primaryStage.setTitle("Gestion Categoria");
        primaryStage.setScene(scene);

        // Establecer tamaño mínimo (para que no se pueda achicar más)
        primaryStage.setMinWidth(400);
        primaryStage.setMinHeight(600);

        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args); // Este método inicia la aplicación JavaFX
    }
}