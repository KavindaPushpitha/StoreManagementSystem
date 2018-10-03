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

public class ManageUserController implements Initializable {
    @FXML
    private AnchorPane userRoot;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXTextField txtNewUserName;

    @FXML
    private JFXButton btnChange;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXPasswordField txtNewPassword;

    @FXML
    private JFXPasswordField txtConfirmPassword;
    private LoginDTO account;
    @FXML
    private JFXButton btnCancel;
    private LoginBO loginBO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.LOGIN);
        try {
            account = loginBO.getAccount();
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(ManageUserController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void CloseManageUser(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(this.getClass().getResource("/lk/gamage/stockmgt/view/DashBoard.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) this.userRoot.getScene().getWindow();

            stage.setScene(scene);
            stage.centerOnScreen();
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(ManageUserController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void saveChanges(ActionEvent event) {
        try {
            LoginDTO accountDetail = loginBO.getAccount();
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(ManageUserController.class.getName()).log(Level.SEVERE, null, e);
        }
        if (txtUserName.getText().equals(account.getUserName())) {
            if (PasswordUtils.verifyUserPassword(txtPassword.getText(), account.getPassword(), account.getSaltValue())) {
                if (Validation.addressValidate(txtNewUserName.getText())) {
                    if (Validation.passwordValidation(txtNewPassword.getText())) {
                        if (txtNewPassword.getText().equals(txtConfirmPassword.getText())) {
                            try {
                                String salt = PasswordUtils.getSalt(30);
                                boolean updateAccount = loginBO.updateAccount(new LoginDTO("U01", txtNewUserName.getText(), PasswordUtils.generateSecurePassword(txtNewPassword.getText(), salt), salt));
                                if (updateAccount) {
                                    Notifications notificationsn = Notifications.create().title("User Account").text("User Account is Updated !").hideAfter(Duration.seconds(3)).position(Pos.BOTTOM_RIGHT);
                                    notificationsn.darkStyle();
                                    notificationsn.showInformation();
                                    try {
                                        Parent parent = FXMLLoader.load(this.getClass().getResource("/lk/gamage/stockmgt/view/DashBoard.fxml"));
                                        Scene scene = new Scene(parent);
                                        Stage stage = (Stage) this.userRoot.getScene().getWindow();

                                        stage.setScene(scene);
                                        stage.centerOnScreen();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        Logger.getLogger(ManageUserController.class.getName()).log(Level.SEVERE, null, e);
                                    }
                                } else {
                                    Notifications notificationsn = Notifications.create().title("User Account").text("User Account is not Updated !").hideAfter(Duration.seconds(3)).position(Pos.BOTTOM_RIGHT);
                                    notificationsn.darkStyle();
                                    notificationsn.showError();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                Logger.getLogger(ManageUserController.class.getName()).log(Level.SEVERE, null, e);
                            }
                        } else {
                            txtConfirmPassword.requestFocus();
                            txtConfirmPassword.setFocusColor(Color.RED);
                        }
                    } else {
                        txtNewPassword.requestFocus();
                        txtNewPassword.setFocusColor(Color.RED);
                    }
                } else {
                    txtNewUserName.requestFocus();
                    txtNewUserName.setFocusColor(Color.RED);
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
    void focusToConfilrm(ActionEvent event) {
        if (Validation.passwordValidation(txtNewPassword.getText())) {
            txtConfirmPassword.requestFocus();
        } else {
            txtNewPassword.requestFocus();
            txtNewPassword.setFocusColor(Color.RED);
        }
    }

    @FXML
    void focusToNewPassword(ActionEvent event) {
        if (Validation.addressValidate(txtNewUserName.getText())) {
            txtNewPassword.requestFocus();
        } else {
            txtNewUserName.requestFocus();
            txtNewUserName.setFocusColor(Color.RED);
        }
    }

    @FXML
    void focusToNewUser(ActionEvent event) {
        if (PasswordUtils.verifyUserPassword(txtPassword.getText(), account.getPassword(), account.getSaltValue())) {
            txtNewUserName.requestFocus();
        } else {
            txtPassword.requestFocus();
            txtPassword.setFocusColor(Color.RED);
        }
    }

    @FXML
    void focusToPassword(ActionEvent event) {
        if (Validation.addressValidate(txtUserName.getText())) {
            txtPassword.requestFocus();
        } else {
            txtUserName.requestFocus();
            txtUserName.setFocusColor(Color.RED);
        }
    }

}
