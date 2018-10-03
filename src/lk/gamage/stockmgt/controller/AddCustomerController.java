package lk.gamage.stockmgt.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.ValueAxis;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.gamage.stockmgt.business.BOFactory;
import lk.gamage.stockmgt.business.custom.CustomerBO;
import lk.gamage.stockmgt.business.custom.impl.CustomerBOImpl;
import lk.gamage.stockmgt.common.IDGenerator;
import lk.gamage.stockmgt.common.Validation;
import lk.gamage.stockmgt.model.CustomerDTO;
import org.controlsfx.control.Notifications;
import sample.NIC_Details;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddCustomerController implements Initializable {
    @FXML
    private AnchorPane addCustomerRoot;

    @FXML
    private JFXTextField txtCustomerID;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtAddress;
    @FXML
    private Label txtNityfy;
    @FXML
    private JFXTextField txtNIC;

    @FXML
    private JFXTextField txtDOB;

    @FXML
    private JFXTextField txtTelephone;

    @FXML
    private ImageView btnClose;

    @FXML
    private JFXButton btnCancel;


    private CustomerBO customerBO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCustomerID();
        txtName.requestFocus();
        customerBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);
    }

    private void setCustomerID() {
        try {
            String customerID;
            customerID = IDGenerator.getNewID("Customer", "customerID", "C");
            txtCustomerID.setText(customerID);
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(AddCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void saveCustomer(ActionEvent event) {
        if (Validation.nameValidate(txtName.getText())) {
            if (Validation.addressValidate(txtAddress.getText())) {
                if (Validation.nicValidate(txtNIC.getText())) {
                    if (Validation.dateValidation(txtDOB.getText())) {
                        if (Validation.telephoneValidate(txtTelephone.getText())) {
                            if (txtCustomerID.getText().isEmpty() || txtName.getText().isEmpty() || txtAddress.getText().isEmpty() || txtNIC.getText().isEmpty() || txtDOB.getText().isEmpty() || txtTelephone.getText().isEmpty()) {
                                Alert a = new Alert(Alert.AlertType.ERROR, "First you need to fill this form correct!");
                                a.show();
                            } else {
                                CustomerDTO customer = new CustomerDTO(txtCustomerID.getText(), txtName.getText(), txtDOB.getText(), txtAddress.getText(), txtTelephone.getText(), txtNIC.getText());
                                try {
                                    boolean isAdded = customerBO.addCustomer(customer);
                                    if (isAdded) {
                                        Alert added = new Alert(Alert.AlertType.INFORMATION, "Successfully Saved !", ButtonType.OK);
                                        added.show();
                                        Notifications notificationsn = Notifications.create().title("Customer").text("New Customer is Add to the system !").hideAfter(Duration.seconds(3)).position(Pos.BOTTOM_RIGHT);
                                        notificationsn.darkStyle();
                                        notificationsn.showInformation();
                                        clearTextFildes(event);
                                        Stage stage = (Stage) btnSave.getScene().getWindow();
                                        stage.close();
                                    }
                                } catch (Exception e) {
                                    Alert error = new Alert(Alert.AlertType.ERROR, "Something went wrong !", ButtonType.OK);
                                    error.show();
                                    e.printStackTrace();
                                    Logger.getLogger(AddCustomerController.class.getName()).log(Level.SEVERE, null, e);
                                }
                            }
                        } else {
                            txtTelephone.requestFocus();
                            txtTelephone.setFocusColor(Color.RED);
                            txtNityfy.setText("Telephone No format is incorrect !");
                        }
                    } else {
                        txtNIC.requestFocus();
                        txtNIC.setFocusColor(Color.RED);
                        txtNityfy.setText("Invalid NIC No !");
                    }
                } else {
                    txtNIC.requestFocus();
                    txtNIC.setFocusColor(Color.RED);
                    txtNityfy.setText("NIC format is incorrect !");
                }
            } else {
                txtAddress.requestFocus();
                txtAddress.setFocusColor(Color.RED);
                txtNityfy.setText("Address format is incorrect !");
            }
        } else {
            txtName.requestFocus();
            txtName.setFocusColor(Color.RED);
            txtNityfy.setText("Customer Name format is incorrect !");
        }


    }

    @FXML
    void clearTextFildes(ActionEvent event) {
        txtName.clear();
        txtDOB.clear();
        txtAddress.clear();
        txtTelephone.clear();
        txtNIC.clear();
    }

    @FXML
    void closeAddCustomer(MouseEvent event) {
        AddCustomerController addCustomerController = new AddCustomerController();
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

    }

    @FXML
    void focusChangeTel(ActionEvent event) {
        if (Validation.dobValidation(txtDOB.getText())) {
            txtTelephone.requestFocus();
            txtNityfy.setText("");
        } else {
            txtDOB.requestFocus();
            txtDOB.setFocusColor(Color.RED);
            txtDOB.selectAll();
            txtNityfy.setText("DOB format is incorrect !");
        }
    }

    @FXML
    void focusChangeToAddress(ActionEvent event) {
        if (Validation.nameValidate(txtName.getText())) {
            txtAddress.requestFocus();
            txtNityfy.setText("");
        } else {
            txtName.requestFocus();
            txtName.setFocusColor(Color.RED);
            txtName.selectAll();
            txtNityfy.setText("Name format is incorrect !");


        }

    }

    @FXML
    void focusChangeToDOB(ActionEvent event) {
        if (Validation.nicValidate(txtNIC.getText())) {
            if (txtNIC.getText().length() > 10) {
                txtDOB.setDisable(false);
                txtDOB.requestFocus();
                txtNityfy.setText("You need to enter the DOB manual for this nic pattern !");
            } else {
                NIC_Details N = new NIC_Details();
                N.setNic(txtNIC.getText());
                txtDOB.setText(N.setMonth());
                if (Validation.dateValidation(txtDOB.getText())) {
                    txtTelephone.requestFocus();
                    txtNityfy.setText("");
                } else {
                    txtNIC.requestFocus();
                    txtNIC.setFocusColor(Color.RED);
                    txtNityfy.setText("Invalid NIC");
                }

            }

        } else {
            txtNIC.requestFocus();
            txtNIC.selectAll();
            txtNIC.setFocusColor(Color.RED);
            txtNityfy.setText("NIC format is incorrect !");
        }
    }

    @FXML
    void focusChangeToEnterNIC(ActionEvent event) {
        if (Validation.addressValidate(txtAddress.getText())) {
            txtNIC.requestFocus();
            txtNityfy.setText("");
        } else {
            txtAddress.requestFocus();
            txtAddress.selectAll();
            txtAddress.setFocusColor(Color.RED);
            txtNityfy.setText("Address format is incorrect !");
        }

    }

    @FXML
    void onAction(ActionEvent event) {
        if (Validation.telephoneValidate(txtTelephone.getText())) {
            saveCustomer(event);
            txtNityfy.setText("");
        } else {
            txtTelephone.requestFocus();
            txtTelephone.selectAll();
            txtTelephone.setFocusColor(Color.RED);
            txtNityfy.setText("Telephone format is incorrect !");
        }
    }


}




