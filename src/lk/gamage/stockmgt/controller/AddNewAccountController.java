package lk.gamage.stockmgt.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.gamage.stockmgt.business.BOFactory;
import lk.gamage.stockmgt.business.custom.LoginBO;
import lk.gamage.stockmgt.common.PasswordUtils;
import lk.gamage.stockmgt.common.Validation;
import lk.gamage.stockmgt.model.LoginDTO;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddNewAccountController implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXPasswordField txtConfirm;

    @FXML
    private JFXButton btnCreate;

    @FXML
    private JFXButton btnCancel;
    private LoginBO loginBO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.LOGIN);
    }

    @FXML
    void close(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(this.getClass().getResource("/lk/gamage/stockmgt/view/Login.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) this.root.getScene().getWindow();

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Logger.getLogger(AddNewAccountController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void createAccount(ActionEvent event) {
        if (Validation.addressValidate(txtUserName.getText())) {
            if (Validation.passwordValidation(txtPassword.getText())) {
                if (txtPassword.getText().equals(txtConfirm.getText())) {
                    try {
                        String salt = PasswordUtils.getSalt(30);
                        boolean addAccount = loginBO.addAccount(new LoginDTO("U01", txtUserName.getText(), PasswordUtils.generateSecurePassword(txtPassword.getText(), salt), salt));
                        if (addAccount) {
                            Notifications notificationsn = Notifications.create().title("Create Account").text("New User Account is created !").hideAfter(Duration.seconds(3)).position(Pos.BOTTOM_RIGHT);
                            notificationsn.darkStyle();
                            notificationsn.showInformation();
                            try {
                                Parent parent = FXMLLoader.load(this.getClass().getResource("/lk/gamage/stockmgt/view/Login.fxml"));
                                Scene scene = new Scene(parent);
                                Stage stage = (Stage) this.root.getScene().getWindow();

                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException e) {
                                e.printStackTrace();
                                Logger.getLogger(AddNewAccountController.class.getName()).log(Level.SEVERE, null, e);
                            }
                        } else {
                            Notifications notificationsn = Notifications.create().title("Create Account").text("New User Account is not created !").hideAfter(Duration.seconds(3)).position(Pos.BOTTOM_RIGHT);
                            notificationsn.darkStyle();
                            notificationsn.showError();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Logger.getLogger(AddNewAccountController.class.getName()).log(Level.SEVERE, null, e);
                    }
                } else {
                    txtConfirm.requestFocus();
                    txtConfirm.setFocusColor(Color.RED);
                }
            } else {
                txtPassword.requestFocus();
                txtPassword.setFocusColor(Color.RED);
            }
        } else {
            txtUserName.requestFocus();
            txtUserName.setFocusColor(Color.RED);
        }
    }

    @FXML
    void focusToConfirm(ActionEvent event) {
        if (Validation.passwordValidation(txtPassword.getText())) {
            txtConfirm.requestFocus();
        } else {
            txtUserName.requestFocus();
            txtUserName.setFocusColor(Color.RED);
        }
    }

    @FXML
    void focusToPassword(ActionEvent event) {
        if (Validation.addressValidate(txtUserName.getText())) {
            txtPassword.requestFocus();
        } else {
            txtPassword.requestFocus();
            txtPassword.setFocusColor(Color.RED);
        }
    }

}
