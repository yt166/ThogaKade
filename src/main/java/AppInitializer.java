import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppInitializer extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ThogaKadeForm.fxml"))));
        stage.setResizable(false);
        stage.setTitle("Thoga Kade");
        stage.show();
    }
}