package lk.gamage.stockmgt.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SplashScreenController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Timeline time = new Timeline(new KeyFrame(Duration.millis(5000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Parent parent = FXMLLoader.load(this.getClass().getResource("/lk/gamage/stockmgt/view/Login.fxml"));
                    Scene temp = new Scene(parent);
                    Stage stage = (Stage) rootPane.getScene().getWindow();
                    boolean add = stage.getIcons().add(new Image("/lk/gamage/stockmgt/assest/Electrical_96px.png"));
                    stage.setScene(temp);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Logger.getLogger(SplashScreenController.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }));
        time.play();
    }
}
