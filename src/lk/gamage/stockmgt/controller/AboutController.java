package lk.gamage.stockmgt.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AboutController implements Initializable {
    @FXML
    private ImageView btnClose;
    @FXML
    private AnchorPane root;
    @FXML
    private Hyperlink hpLink;
    @FXML
    private WebView webView;
    @FXML
    private WebEngine engine;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        engine=webView.getEngine();
    }

    @FXML
    void closeAbout(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(this.getClass().getResource("/lk/gamage/stockmgt/view/DashBoard.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) this.root.getScene().getWindow();

            stage.setScene(scene);
            stage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
            Logger.getLogger(AboutController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void hyper(ActionEvent event) {
        //engine.load("http://http://www.ijse.lk");
    }


}
