import Util.ContextoConexion;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMaster extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Aquí defines si estás trabajando con MASTER o REMOTO
        ContextoConexion.setTipoConexion(ContextoConexion.TipoConexion.MASTER);

        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Interfaz/PantallaPrincipal.fxml"));
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Proyecto: Menu Principal (Master)");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
