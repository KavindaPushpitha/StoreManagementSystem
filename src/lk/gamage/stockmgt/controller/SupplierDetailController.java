package lk.gamage.stockmgt.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
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
import lk.gamage.stockmgt.business.custom.SupplierBO;
import lk.gamage.stockmgt.business.custom.impl.SupplierBOImpl;
import lk.gamage.stockmgt.common.IDGenerator;
import lk.gamage.stockmgt.common.Validation;
import lk.gamage.stockmgt.model.SupplierDTO;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SupplierDetailController implements Initializable {
    @FXML
    private JFXTextField txtSupplierSearch;

    @FXML
    private JFXButton btnAddSupplier;

    @FXML
    private JFXTextField txtSupplierID;

    @FXML
    private JFXTextField txtSupplierName;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnSave;

    @FXML
    private TableView<SupplierDTO> tblSuppliers;
    @FXML
    private Label lblNotify;
    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtTele;

    @FXML
    private JFXTextField txtCompanyName;

    @FXML
    private JFXTextField txtCompanyTel;

    @FXML
    private JFXSpinner spnSpinner;
    @FXML
    private JFXTextField txtCompanyAddress;

    @FXML
    private JFXButton btnDelete;
    private boolean update = false;
    private ArrayList<SupplierDTO> allSupplier;
    private ArrayList<String> supplierName;
    private SupplierBO supplierBO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        supplierBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.SUPPLIER);
        try {
            tblSuppliers.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("SupplierID"));
            tblSuppliers.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("SupplierName"));
            tblSuppliers.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
            tblSuppliers.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("contact"));
            tblSuppliers.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("companyName"));
            tblSuppliers.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("companyAddress"));
            tblSuppliers.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("companyContact"));
            loadAllSuppliers();
            loadSupplierNames();
            setSupplierID();

        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(SupplierDetailController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    private void setSupplierID() {
        try {
            String supplierID;
            supplierID = IDGenerator.getNewID("supplier", "supplierID", "S");
            txtSupplierID.setText(supplierID);
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(SupplierDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadSupplierNames() {
        TextFields.bindAutoCompletion(txtSupplierSearch, supplierName);
    }

    private void loadAllSuppliers() throws Exception {
        allSupplier = supplierBO.getAllSupplier();
        tblSuppliers.setItems(FXCollections.observableArrayList(allSupplier));
        supplierName = new ArrayList<>();
        for (SupplierDTO supplierDTO : allSupplier) {
            supplierName.add(supplierDTO.getSupplierName());
        }
    }


    @FXML
    void addNewSupplier(ActionEvent event) {
        clearTxtFileds();
        enableTxtFilds();
        setSupplierID();
        txtSupplierName.requestFocus();
        update = false;

    }

    @FXML
    void deleteSupplier(ActionEvent event) {
        if (tblSuppliers.getSelectionModel().getSelectedIndex() != -1) {
            try {
                boolean deleteSupplier = supplierBO.deleteSupplier(txtSupplierID.getText());

                if (deleteSupplier) {
                    Alert delete = new Alert(Alert.AlertType.INFORMATION, "Successfully Deleted !", ButtonType.OK);
                    delete.show();
//                    Notifications notificationsn=Notifications.create().title("Supplier").text("Supplier Deleted from the system !").hideAfter(Duration.seconds(3)).position(Pos.BOTTOM_RIGHT);
//                    notificationsn.darkStyle();
//                    notificationsn.showInformation();
                    loadAllSuppliers();
                    setSupplierID();
                    clearTxtFileds();

                } else {
                    Alert delete = new Alert(Alert.AlertType.ERROR, "Not Deleted !", ButtonType.OK);
                    delete.show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Logger.getLogger(SupplierDetailController.class.getName()).log(Level.SEVERE, null, e);
            }
        } else {
            Alert delete = new Alert(Alert.AlertType.ERROR, "Please Select Row to delete!", ButtonType.OK);
            delete.show();
        }

    }

    @FXML
    void keyReleased(KeyEvent event) {
        spnSpinner.setVisible(true);
    }

    @FXML
    void saveSupplier(ActionEvent event) {
        if (Validation.nameValidate(txtSupplierName.getText())) {
            if (Validation.addressValidate(txtAddress.getText())) {
                if (Validation.addressValidate(txtCompanyAddress.getText())) {
                    if (Validation.telephoneValidate(txtTele.getText())) {
                        if (Validation.nameValidate(txtCompanyName.getText())) {
                            if (Validation.telephoneValidate(txtCompanyTel.getText())) {
                                SupplierDTO supplierDTO;
                                if (txtSupplierID.getText().isEmpty() || txtSupplierName.getText().isEmpty() || txtTele.getText().isEmpty() || txtCompanyName.getText().isEmpty() || txtCompanyAddress.getText().isEmpty() || txtCompanyTel.getText().isEmpty() || txtAddress.getText().isEmpty()) {
                                    supplierDTO = null;
                                    Alert add = new Alert(Alert.AlertType.ERROR, "You need to fill this form correct !", ButtonType.OK);
                                    Optional<ButtonType> buttonType = add.showAndWait();
                                    if (buttonType.get() == ButtonType.OK) {
                                        if (txtSupplierName.getText().isEmpty()) {
                                            txtSupplierName.requestFocus();
                                        } else if (txtAddress.getText().isEmpty()) {
                                            txtAddress.requestFocus();
                                        } else if (txtTele.getText().isEmpty()) {
                                            txtTele.requestFocus();
                                        } else if (txtCompanyName.getText().isEmpty()) {
                                            txtCompanyName.requestFocus();
                                        } else if (txtCompanyAddress.getText().isEmpty()) {
                                            txtCompanyAddress.requestFocus();
                                        } else {
                                            txtCompanyTel.requestFocus();
                                        }
                                    }

                                } else {
                                    supplierDTO = new SupplierDTO(txtSupplierID.getText(), txtSupplierName.getText(), txtAddress.getText(), txtTele.getText(), txtCompanyName.getText(), txtCompanyAddress.getText(), txtCompanyTel.getText());
                                    if (update == false) {
                                        try {
                                            boolean addSupplier = supplierBO.addSupplier(supplierDTO);
                                            if (addSupplier) {
                                                Alert add = new Alert(Alert.AlertType.INFORMATION, "Successfully Added !", ButtonType.OK);
                                                add.show();
                                                Notifications notificationsn = Notifications.create().title("Supplier").text("New Supplier is Add to the system !").hideAfter(Duration.seconds(3)).position(Pos.BOTTOM_RIGHT);
                                                notificationsn.darkStyle();
                                                notificationsn.showInformation();
                                                loadAllSuppliers();
                                                disableTxtFilds();
                                                clearTxtFileds();
                                                setSupplierID();
                                            } else {
                                                Alert add = new Alert(Alert.AlertType.ERROR, "Not Added !", ButtonType.OK);
                                                add.show();
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                            Logger.getLogger(SupplierDetailController.class.getName()).log(Level.SEVERE, null, e);
                                        }
                                    } else {
                                        try {
                                            boolean updateSupplier = supplierBO.updateSupplier(supplierDTO);
                                            if (updateSupplier) {
                                                Alert updated = new Alert(Alert.AlertType.INFORMATION, "Successfully Updated !", ButtonType.OK);
                                                updated.show();
                                                Notifications notificationsn = Notifications.create().title("Supplier").text("Updated existing Supplier!").hideAfter(Duration.seconds(3)).position(Pos.BOTTOM_RIGHT);
                                                notificationsn.darkStyle();
                                                notificationsn.showInformation();
                                                loadAllSuppliers();
                                                disableTxtFilds();
                                                clearTxtFileds();
                                                setSupplierID();
                                            } else {
                                                Alert updated = new Alert(Alert.AlertType.ERROR, "Not Updated !", ButtonType.OK);
                                                updated.show();
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                            Logger.getLogger(SupplierDetailController.class.getName()).log(Level.SEVERE, null, e);
                                        }
                                    }
                                }
                            } else {
                                txtCompanyTel.requestFocus();
                                txtCompanyTel.setFocusColor(Color.RED);
                                lblNotify.setText("Company Telephone format is incorrect !");
                            }
                        } else {
                            txtCompanyName.requestFocus();
                            txtCompanyName.setFocusColor(Color.RED);
                            lblNotify.setText("Company Name format is incorrect !");
                        }
                    } else {
                        txtTele.requestFocus();
                        txtTele.setFocusColor(Color.RED);
                        lblNotify.setText("Telephone no format is incorrect !");
                    }
                } else {
                    txtCompanyAddress.requestFocus();
                    txtCompanyAddress.setFocusColor(Color.RED);
                    lblNotify.setText("Company address format is incorrect !");
                }
            } else {
                txtAddress.requestFocus();
                txtAddress.setFocusColor(Color.RED);
                lblNotify.setText("Address format is incorrect !");
            }
        } else {
            txtSupplierName.requestFocus();
            txtSupplierName.setFocusColor(Color.RED);
            lblNotify.setText("Supplier Name format is incorrect !");
        }
        loadSupplierNames();
    }

    @FXML
    void searchSupplier(ActionEvent event) {
        try {
            SupplierDTO supplierDTO = supplierBO.searchSupplier(txtSupplierSearch.getText());
            txtSupplierID.setText(supplierDTO.getSupplierID());
            txtSupplierName.setText(supplierDTO.getSupplierName());
            txtAddress.setText(supplierDTO.getAddress());
            txtCompanyName.setText(supplierDTO.getCompanyName());
            txtCompanyAddress.setText(supplierDTO.getCompanyAddress());
            txtCompanyTel.setText(supplierDTO.getCompanyContact());
            txtTele.setText(supplierDTO.getContact());

        } catch (Exception e) {
            Alert noSupplier = new Alert(Alert.AlertType.ERROR, "No such Item !", ButtonType.OK);
            noSupplier.show();
        }

    }

    @FXML
    void updateSupplier(ActionEvent event) {
        enableTxtFilds();
        update = true;

    }

    public void clearTxtFileds() {
        txtSupplierName.clear();
        txtAddress.clear();
        txtCompanyName.clear();
        txtCompanyAddress.clear();
        txtCompanyTel.clear();
        txtTele.clear();

    }

    public void enableTxtFilds() {
        txtSupplierName.setDisable(false);
        txtAddress.setDisable(false);
        txtCompanyName.setDisable(false);
        txtCompanyAddress.setDisable(false);
        txtCompanyTel.setDisable(false);
        txtTele.setDisable(false);
    }

    @FXML
    void mouseClicked(MouseEvent event) {
        if (tblSuppliers.getSelectionModel().getSelectedIndex() != -1) {
            SupplierDTO selectedItem = tblSuppliers.getSelectionModel().getSelectedItem();
            txtSupplierID.setText(selectedItem.getSupplierID());
            txtSupplierName.setText(selectedItem.getSupplierName());
            txtAddress.setText(selectedItem.getAddress());
            txtCompanyName.setText(selectedItem.getCompanyName());
            txtCompanyAddress.setText(selectedItem.getCompanyAddress());
            txtCompanyTel.setText(selectedItem.getCompanyContact());
            txtTele.setText(selectedItem.getContact());
        }

    }

    void disableTxtFilds() {
        txtSupplierID.setDisable(true);
        txtSupplierName.setDisable(true);
        txtAddress.setDisable(true);
        txtCompanyName.setDisable(true);
        txtCompanyAddress.setDisable(true);
        txtCompanyTel.setDisable(true);
        txtTele.setDisable(true);
    }

    @FXML
    void focusToAddress(ActionEvent event) {
        if (Validation.nameValidate(txtSupplierName.getText())) {
            txtAddress.requestFocus();
            lblNotify.setText("");
        } else {
            txtSupplierName.requestFocus();
            txtSupplierName.setFocusColor(Color.RED);
            lblNotify.setText("Supplier name format is incorrect !");
        }

    }

    @FXML
    void focusToCompanyAddress(ActionEvent event) {
        if (Validation.telephoneValidate(txtCompanyTel.getText())) {
            txtCompanyAddress.requestFocus();
            lblNotify.setText("");
        } else {
            txtCompanyTel.requestFocus();
            txtCompanyTel.setFocusColor(Color.RED);
            lblNotify.setText("Telephone no format is incorrect !");
        }
    }

    @FXML
    void focusToCompanyName(ActionEvent event) {
        if (Validation.telephoneValidate(txtTele.getText())) {
            txtCompanyName.requestFocus();
            lblNotify.setText("");
        } else {
            txtTele.requestFocus();
            txtTele.setFocusColor(Color.RED);
            lblNotify.setText("Telephone no format is incorrect !");
        }

    }

    @FXML
    void focusToCompanyTel(ActionEvent event) {
        txtCompanyTel.requestFocus();
    }

    @FXML
    void focusToTelNo(ActionEvent event) {
        if (Validation.addressValidate(txtAddress.getText())) {
            txtTele.requestFocus();
            lblNotify.setText("");
        } else {
            txtAddress.requestFocus();
            txtAddress.setFocusColor(Color.RED);
            lblNotify.setText("Address format is incorrect !");
        }
    }

    @FXML
    void onAction(ActionEvent event) {
        saveSupplier(event);
    }
}
