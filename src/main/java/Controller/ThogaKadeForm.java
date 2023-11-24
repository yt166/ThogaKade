package Controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ThogaKadeForm {
    public AnchorPane thogaKade;
    public Label lblMain;
    public Label lblTime;
    public Label lblDate;
    public Button btnCustomer;
    public Button btnItem;
    public ImageView img1;

    public void initialize(){
        manageTime();
        manageDate();
    }

    private void manageDate() {
        Timeline day = new Timeline(new KeyFrame
                (Duration.ZERO,
                        actionEvent -> lblDate.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd:MM:yyyy")))),new KeyFrame(Duration.seconds(1)));
        day.setCycleCount(Animation.INDEFINITE);
        day.play();
    }

    private void manageTime() {
        Timeline timeline = new Timeline(new KeyFrame
                (Duration.ZERO,
                        actionEvent -> lblTime.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss")))),new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void btnCustomerOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) thogaKade.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/CustomerForm.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Customer Form");
        stage.show();
    }

    public void btnItemOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) thogaKade.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ItemForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setResizable(true);
        stage.setTitle("Item Form");
        stage.show();

    }
}
