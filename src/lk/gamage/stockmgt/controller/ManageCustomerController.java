package lk.gamage.stockmgt.controller;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import lk.gamage.stockmgt.business.BOFactory;
import lk.gamage.stockmgt.business.custom.CustomerBO;
import lk.gamage.stockmgt.business.custom.impl.CustomerBOImpl;
import lk.gamage.stockmgt.common.NIC_Details;
import lk.gamage.stockmgt.common.Validation;
import lk.gamage.stockmgt.entity.Customer;
import lk.gamage.stockmgt.model.CustomerDTO;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManageCustomerController implements Initializable {

    @FXML
    private JFXTextField txtCustomerSearch;

    @FXML
    private JFXTextField txtCustomerID;

    @FXML
    private JFXTextField txtFName;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnSave;

    @FXML
    private TableView<CustomerDTO> tblAllCustomers;

    @FXML
    private JFXTextField txtLName;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtTelephone;
    @FXML
    private Label lblNotify;
    @FXML
    private JFXTextField txtNIC;

    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXSpinner spnSpinner;

    @FXML
    private JFXTextField txtDOB;
    private CustomerBO customerBO;
    private ArrayList<CustomerDTO> allCustomer;
    ArrayList<String> names;
    @FXML
    private JFXRadioButton rbtnCustomer;
    private boolean rButtons = true;
    @FXML
    private JFXRadioButton rbtnNIC;
    private ArrayList<String> nic;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ToggleGroup t = new ToggleGroup();
        rbtnNIC.setToggleGroup(t);
        rbtnCustomer.setToggleGroup(t);
        customerBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);
        tblAllCustomers.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        tblAllCustomers.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        tblAllCustomers.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("dob"));
        tblAllCustomers.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblAllCustomers.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("contact"));
        tblAllCustomers.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("nic"));
        if (rButtons) {
            loadAllCustomers();
            loadCustomerNames();
        } else if (!rButtons) {
            loadAllCustomers();
            loadNic();
        }

    }

    public void loadAllCustomers() {
        try {
            allCustomer = customerBO.getAllCustomer();
            tblAllCustomers.setItems(FXCollections.observableArrayList(allCustomer));
            names = new ArrayList<>();
            nic = new ArrayList<>();
            for (CustomerDTO customerDTO : allCustomer) {
                names.add(customerDTO.getCustomerName());
                nic.add(customerDTO.getNic());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(ManageCustomerController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    void loadNic() {
        TextFields.bindAutoCompletion(txtCustomerSearch, nic);
    }

    private void loadCustomerNames() {
        TextFields.bindAutoCompletion(txtCustomerSearch, names);
    }


    @FXML
    void enableTxtFilds(ActionEvent event) {
        txtFName.setDisable(false);
        txtAddress.setDisable(false);
        txtTelephone.setDisable(false);
        txtNIC.setDisable(false);
    }

    void clearTxtFileds() {
        txtAddress.clear();
        txtFName.clear();
        txtNIC.clear();
        txtTelephone.clear();
        txtDOB.clear();
        txtCustomerID.clear();
    }

    @FXML
    void byCustomerName(ActionEvent event) {
        if (rbtnCustomer.isSelected()) {
            rButtons = true;
            loadCustomerNames();
        }
    }

    @FXML
    void byNIC(ActionEvent event) {
        if (rbtnNIC.isSelected()) {
            rButtons = false;
            loadNic();
        }
    }

    @FXML
    void searchCustomer(ActionEvent event) {
        spnSpinner.setVisible(false);
        if (rButtons) {
            try {
                CustomerDTO customerDTO = customerBO.searchCustomer(txtCustomerSearch.getText());
                if (customerDTO != null) {
                    txtCustomerID.setText(customerDTO.getCustomerID());
                    txtFName.setText(customerDTO.getCustomerName());
                    txtDOB.setText(customerDTO.getDob());
                    txtAddress.setText(customerDTO.getAddress());
                    txtTelephone.setText(customerDTO.getContact());
                    txtNIC.setText(customerDTO.getNic());
                }

            } catch (Exception e) {
                Alert noCustomer = new Alert(Alert.AlertType.ERROR, "No such Customer !", ButtonType.OK);
                noCustomer.show();

            }
        } else if (!rButtons) {
            try {
                CustomerDTO customerDTO = customerBO.searchByNic(txtCustomerSearch.getText());
                if (customerDTO != null) {
                    txtCustomerID.setText(customerDTO.getCustomerID());
                    txtFName.setText(customerDTO.getCustomerName());
                    txtDOB.setText(customerDTO.getDob());
                    txtAddress.setText(customerDTO.getAddress());
                    txtTelephone.setText(customerDTO.getContact());
                    txtNIC.setText(customerDTO.getNic());
                }

            } catch (Exception e) {
                Alert noCustomer = new Alert(Alert.AlertType.ERROR, "No such Customer !", ButtonType.OK);
                noCustomer.show();
            }
        }
    }

    @FXML
    void updateCustomer(ActionEvent event) {
        if (Validation.telephoneValidate(txtTelephone.getText())) {
            if (Validation.nameValidate(txtFName.getText())) {
                if (Validation.nicValidate(txtNIC.getText())) {
                    if (Validation.dateValidation(txtDOB.getText())) {
                        if (Validation.addressValidate(txtAddress.getText())) {
                            if (txtFName.getText().isEmpty() || txtAddress.getText().isEmpty() || txtTelephone.getText().isEmpty() || txtNIC.getText().isEmpty() || txtDOB.getText().isEmpty()) {
                                Alert update = new Alert(Alert.AlertType.ERROR, "Fill the form correct !", ButtonType.OK);
                                Optional<ButtonType> buttonType = update.showAndWait();
                                if (buttonType.get() == ButtonType.OK) {
                                    if (txtFName.getText().isEmpty()) {
                                        txtFName.requestFocus();
                                    } else if (txtAddress.getText().isEmpty()) {
                                        txtAddress.requestFocus();
                                    } else if (txtDOB.getText().isEmpty()) {
                                        txtDOB.requestFocus();
                                    } else if (txtTelephone.getText().isEmpty()) {
                                        txtTelephone.requestFocus();
                                    } else {
                                        txtNIC.requestFocus();
                                    }
                                }
                            } else {
                                try {
                                    boolean updateCustomer = customerBO.updateCustomer(new CustomerDTO(txtCustomerID.getText(), txtFName.getText(), txtDOB.getText(), txtAddress.getText(), txtTelephone.getText(), txtNIC.getText()));
                                    loadAllCustomers();
                                    if (updateCustomer) {
                                        Alert update = new Alert(Alert.AlertType.INFORMATION, "Successfully Updated !", ButtonType.OK);
                                        update.show();
                                        clearTxtFileds();
                                        Notifications notificationsn = Notifications.create().title("Customer").text("Updated Existing Customer !").hideAfter(Duration.seconds(3)).position(Pos.BOTTOM_RIGHT);
                                        notificationsn.darkStyle();
                                        notificationsn.showInformation();
                                    } else {
                                        Alert update = new Alert(Alert.AlertType.ERROR, "Not Updated !", ButtonType.OK);
                                        update.show();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Logger.getLogger(ManageCustomerController.class.getName()).log(Level.SEVERE, null, e);
                                }
                            }
                        } else {
                            txtAddress.requestFocus();
                            txtAddress.setFocusColor(Color.RED);
                            lblNotify.setText("Address format is incorrect !");

                        }
                    } else {
                        txtNIC.requestFocus();
                        txtNIC.setFocusColor(Color.RED);
                        lblNotify.setText("Invalid NIC !");
                    }
                } else {
                    txtNIC.requestFocus();
                    txtNIC.setFocusColor(Color.RED);
                    lblNotify.setText("NIC format is incorrect !");

                }
            } else {
                txtFName.requestFocus();
                txtFName.setFocusColor(Color.RED);
                lblNotify.setText("Customer Name format is incorrect !");
            }
        } else {
            txtTelephone.requestFocus();
            txtTelephone.setFocusColor(Color.RED);
            lblNotify.setText("Telephone format is incorrect !");
        }


    }

    @FXML
    void deleteCustomer(ActionEvent event) {
        try {
            int row = tblAllCustomers.getSelectionModel().getSelectedIndex();

            boolean deleteCustomer = customerBO.deleteCustomer(txtCustomerID.getText());
            if (deleteCustomer) {
                Alert delete = new Alert(Alert.AlertType.INFORMATION, "Successfully Deleted !", ButtonType.OK);
                delete.show();
            } else {
                Alert delete = new Alert(Alert.AlertType.ERROR, "Not Deleted !", ButtonType.OK);
                delete.show();
            }
            if (row != -1) {
                loadAllCustomers();
            } else {
                Alert delete = new Alert(Alert.AlertType.ERROR, "Please Select a Row", ButtonType.OK);
                delete.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(ManageCustomerController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void mouseClicked(MouseEvent event) {
        if (tblAllCustomers.getSelectionModel().getSelectedIndex() != -1) {
            CustomerDTO selectedItem = tblAllCustomers.getSelectionModel().getSelectedItem();
            txtFName.setText(selectedItem.getCustomerName());
            txtCustomerID.setText(selectedItem.getCustomerID());
            txtAddress.setText(selectedItem.getAddress());
            txtTelephone.setText(selectedItem.getContact());
            txtNIC.setText(selectedItem.getNic());
            txtDOB.setText(selectedItem.getDob());
        }
    }

    @FXML
    void focusTOAddress(ActionEvent event) {
        if (Validation.nameValidate(txtFName.getText())) {
            txtAddress.requestFocus();
            lblNotify.setText("");
        } else {
            txtFName.requestFocus();
            txtFName.setFocusColor(Color.RED);
            lblNotify.setText("Name format is incorrect !");
        }

    }

    @FXML
    void focusToDOB(ActionEvent event) {
        if (Validation.nicValidate(txtNIC.getText())) {
            lblNotify.setText("");
            NIC_Details n = new NIC_Details();
            n.setNic(txtNIC.getText());
            txtDOB.setText(n.setMonth());
            if (Validation.dateValidation(txtDOB.getText())) {
                onAction(event);
            } else {
                txtNIC.requestFocus();
                txtNIC.setFocusColor(Color.RED);
                lblNotify.setText("Invalid NIC");
            }
        } else {
            txtNIC.requestFocus();
            txtNIC.setFocusColor(Color.RED);
            lblNotify.setText("NIC format is incorrect !");
        }
    }

    @FXML
    void focusToNic(ActionEvent event) {
        if (Validation.telephoneValidate(txtTelephone.getText())) {
            txtNIC.requestFocus();
            lblNotify.setText("");
        } else {
            txtTelephone.requestFocus();
            txtTelephone.setFocusColor(Color.RED);
            lblNotify.setText("Telephone No format is incorrect !");
        }
    }

    @FXML
    void focusToTeleNo(ActionEvent event) {
        if (Validation.addressValidate(txtAddress.getText())) {
            txtTelephone.requestFocus();
            lblNotify.setText("");
        } else {
            txtAddress.requestFocus();
            txtAddress.setFocusColor(Color.RED);
            lblNotify.setText("Address format is incorrect !");
        }
    }

    @FXML
    void keyRelesed(KeyEvent event) {
        spnSpinner.setVisible(true);
    }

    @FXML
    void onAction(ActionEvent event) {
        updateCustomer(event);
    }
}
