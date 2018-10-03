package lk.gamage.stockmgt.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import lk.gamage.stockmgt.business.BOFactory;
import lk.gamage.stockmgt.business.custom.LoginBO;
import lk.gamage.stockmgt.common.PasswordUtils;
import lk.gamage.stockmgt.common.Validation;
import lk.gamage.stockmgt.model.LoginDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController implements Initializable {
    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label lblCreate;
    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXButton btnClose;

    @FXML
    private ImageView imgLock;

    @FXML
    private Label lblInvalidPasswordOrUser;
    @FXML
    private JFXButton btnLogin;
    private LoginBO loginBO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.LOGIN);
        FadeTransition fade = new FadeTransition(Duration.millis(2000), rootPane);
        fade.setFromValue(0.0);
        fade.setToValue(3);
        fade.play();
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(4), imgLock);
        scaleTransition.setFromY(0);
        scaleTransition.setFromX(0);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        scaleTransition.play();
        searchUser();

    }

    void searchUser() {
        try {
            LoginDTO account = loginBO.getAccount();
            if (account == null) {
                lblCreate.setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void viewDashboard(ActionEvent event) {
        if (txtUserName.getText().isEmpty() || txtPassword.getText().isEmpty()) {
            if (txtUserName.getText().isEmpty()) {
                txtUserName.requestFocus();
                txtUserName.setFocusColor(Color.RED);
            } else if (txtPassword.getText().isEmpty()) {
                txtPassword.requestFocus();
                txtPassword.setFocusColor(Color.RED);
            }
        } else {
            try {
                LoginDTO accountDetail = loginBO.getAccount();
                if (PasswordUtils.verifyUserPassword(txtPassword.getText(), accountDetail.getPassword(), accountDetail.getSaltValue()) & txtUserName.getText().equals(accountDetail.getUserName())) {
                    try {
                        Parent parent = FXMLLoader.load(this.getClass().getResource("/lk/gamage/stockmgt/view/DashBoard.fxml"));
                        Scene scene = new Scene(parent);
                        Stage stage = (Stage) this.rootPane.getScene().getWindow();

                        stage.setScene(scene);
                        stage.centerOnScreen();

                    } catch (IOException e) {
                        e.printStackTrace();
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
                    }
                } else {
                    lblInvalidPasswordOrUser.setVisible(true);
                    txtUserName.requestFocus();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    @FXML
    void closeLogin(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void focusTOPassword(ActionEvent event) {
        if (Validation.addressValidate(txtUserName.getText())) {
            txtPassword.requestFocus();
            lblInvalidPasswordOrUser.setVisible(false);
        } else {
            txtUserName.setFocusColor(Color.RED);
        }
    }

    @FXML
    void loadCreateAccount(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(this.getClass().getResource("/lk/gamage/stockmgt/view/AddAccount.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) this.rootPane.getScene().getWindow();

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
