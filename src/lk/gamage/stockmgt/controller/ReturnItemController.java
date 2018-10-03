package lk.gamage.stockmgt.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import lk.gamage.stockmgt.business.BOFactory;
import lk.gamage.stockmgt.business.custom.CustomerBO;
import lk.gamage.stockmgt.business.custom.DamageItemBO;
import lk.gamage.stockmgt.business.custom.ReturnItemBO;
import lk.gamage.stockmgt.business.custom.impl.CustomerBOImpl;
import lk.gamage.stockmgt.business.custom.impl.DamageItemBOImpl;
import lk.gamage.stockmgt.business.custom.impl.ReturnItemBOImpl;
import lk.gamage.stockmgt.common.IDGenerator;
import lk.gamage.stockmgt.common.Validation;
import lk.gamage.stockmgt.model.*;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReturnItemController implements Initializable {
    @FXML
    private JFXTextField txtStatus;

    @FXML
    private JFXTextField txtSearchReturnItem;

    @FXML
    private TableView<ReturnItemTableDTO> tblReturn;

    @FXML
    private JFXTextField txtReturnID;

    @FXML
    private JFXTextField txtDamageID;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXTextField txtReturnDate;

    @FXML
    private JFXTextField txtCustomerID;

    @FXML
    private JFXTextField txtCustomerName;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnAddNew;

    @FXML
    private JFXTextField txtDamageDate;

    @FXML
    private JFXTextField txtOrderID;

    @FXML
    private JFXTextField txtItemName;
    private boolean update = false;
    @FXML
    private JFXTextField txtBrand;
    private ArrayList<DamageItemTableDTO> allDamageItem;
    private ArrayList<String> ids;
    @FXML
    private JFXTextField txtModel;
    private DamageItemBO damageItemBO;
    private CustomerBO customerBO;
    private ReturnItemBO returnItemBO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        damageItemBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.DAMAGE_ITEM);
        customerBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);
        returnItemBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.RETURN_ITEM);
        tblReturn.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("returnID"));
        tblReturn.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("damageID"));
        tblReturn.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblReturn.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tblReturn.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("contact"));
        tblReturn.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("itemName"));
        tblReturn.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("brand"));
        tblReturn.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("modelNo"));
        tblReturn.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("damageDate"));
        tblReturn.getColumns().get(9).setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        tblReturn.getColumns().get(10).setCellValueFactory(new PropertyValueFactory<>("status"));
        getAllReturnItems();
        loadDate();
        setReturnID();
        loadDamageID();
        loadToDamageText();
    }

    private void setReturnID() {
        try {
            String returnID;
            returnID = IDGenerator.getNewID("returnItem", "returnID", "R");
            txtReturnID.setText(returnID);
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(ReturnItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void loadDamageID() {
        try {
            allDamageItem = damageItemBO.getAllDamageItem();
            ids = new ArrayList<>();
            for (DamageItemTableDTO dto : allDamageItem) {
                ids.add(dto.getDamageID());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(ReturnItemController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    void loadToDamageText() {
        TextFields.bindAutoCompletion(txtDamageID, ids);
    }

    @FXML
    void addNew(ActionEvent event) {
        clearTextFields();
        txtDamageID.requestFocus();
        setReturnID();
        loadDate();
        txtDamageID.setDisable(false);
        txtStatus.setDisable(false);
        update = false;
    }

    @FXML
    void searchDamageID(ActionEvent event) {
        if (Validation.idValidation(txtDamageID.getText(), "D")) {
            try {
                DamageItemTableDTO dto = damageItemBO.searchDamageItem(txtDamageID.getText());
                if (dto != null) {
                    txtDamageDate.setText(dto.getDamageDate());
                    txtCustomerID.setText(dto.getCustomerID());
                    txtCustomerName.setText(dto.getCustomerName());
                    txtItemCode.setText(dto.getItemCode());
                    txtBrand.setText(dto.getBrand());
                    txtModel.setText(dto.getModelNo());
                    txtItemName.setText(dto.getItemName());
                    txtOrderID.setText(dto.getOrderID());
                    CustomerDTO customerDTO = customerBO.searchCustomer(txtCustomerName.getText());
                    if (customerDTO != null) {
                        txtContact.setText(customerDTO.getContact());
                    }
                    txtStatus.requestFocus();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Logger.getLogger(ReturnItemController.class.getName()).log(Level.SEVERE, null, e);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Damage ID format is in correct !(Dxxx)", ButtonType.OK);
            alert.show();
            txtDamageID.requestFocus();
            txtDamageID.setFocusColor(Color.RED);
        }
    }

    @FXML
    void delete(ActionEvent event) {
        if (tblReturn.getSelectionModel().getSelectedIndex() != -1) {
            try {
                boolean deleteReturnItem = returnItemBO.deleteReturnItem(txtReturnID.getText());
                if (deleteReturnItem) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "Successfully Deleted !", ButtonType.OK);
                    a.show();
                    Notifications notificationsn = Notifications.create().title("Return Items").text("Deleted Return Item !").hideAfter(javafx.util.Duration.seconds(3)).position(Pos.BOTTOM_RIGHT);
                    notificationsn.darkStyle();
                    notificationsn.showInformation();
                    clearTextFields();
                    getAllReturnItems();
                    setReturnID();
                } else {
                    Alert a = new Alert(Alert.AlertType.ERROR, "Not Deleted !", ButtonType.OK);
                    a.show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Logger.getLogger(ReturnItemController.class.getName()).log(Level.SEVERE, null, e);
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Please select row !", ButtonType.OK);
            a.show();
        }
    }

    @FXML
    void save(ActionEvent event) {
        if (!txtDamageID.getText().isEmpty()) {
            if (Validation.idValidation(txtDamageID.getText(), "Dd")) {
                if (Validation.nameValidate(txtStatus.getText())) {
                    if (txtReturnID.getText().isEmpty() || txtDamageID.getText().isEmpty() || txtCustomerName.getText().isEmpty() || txtItemName.getText().isEmpty() || txtBrand.getText().isEmpty() || txtModel.getText().isEmpty() || txtStatus.getText().isEmpty()) {
                        Alert a = new Alert(Alert.AlertType.ERROR, "Fill the form correctly !", ButtonType.OK);
                        Optional<ButtonType> buttonType = a.showAndWait();
                        if (buttonType.get() == ButtonType.OK) {
                            if (txtStatus.getText().isEmpty()) {
                                txtStatus.requestFocus();
                            } else {
                                txtDamageID.requestFocus();
                            }
                        }
                    } else {
                        if (update == false) {
                            try {
                                boolean returnItem = returnItemBO.addReturnItem(new ReturnItemDTO(txtReturnID.getText(), txtDamageID.getText(), txtItemCode.getText(), txtReturnDate.getText(), txtStatus.getText()));
                                if (returnItem) {
                                    Alert a = new Alert(Alert.AlertType.INFORMATION, "Successfully Added !", ButtonType.OK);
                                    a.show();
                                    Notifications notificationsn = Notifications.create().title("Return Items").text("New Return item added to the system !").hideAfter(javafx.util.Duration.seconds(3)).position(Pos.BOTTOM_RIGHT);
                                    notificationsn.darkStyle();
                                    notificationsn.showInformation();
                                    txtStatus.setDisable(true);
                                    txtDamageID.setDisable(true);
                                    clearTextFields();
                                } else {
                                    Alert a = new Alert(Alert.AlertType.ERROR, "Not Added !", ButtonType.OK);
                                    a.show();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                Logger.getLogger(ReturnItemController.class.getName()).log(Level.SEVERE, null, e);
                            }
                        } else if (update == true) {
                            try {
                                boolean returnItem = returnItemBO.updateReturnItem(new ReturnItemDTO(txtReturnID.getText(), txtDamageID.getText(), txtItemCode.getText(), txtReturnDate.getText(), txtStatus.getText()));
                                if (returnItem) {
                                    Alert a = new Alert(Alert.AlertType.INFORMATION, "Successfully Updated !", ButtonType.OK);
                                    a.show();
                                } else {
                                    Alert a = new Alert(Alert.AlertType.ERROR, "Not Updated !", ButtonType.OK);
                                    a.show();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                Logger.getLogger(ReturnItemController.class.getName()).log(Level.SEVERE, null, e);
                            }
                        }
                    }
                } else {
                    Alert a = new Alert(Alert.AlertType.ERROR, "Status format is incorrect !", ButtonType.OK);
                    a.show();
                    txtStatus.requestFocus();
                    txtStatus.setFocusColor(Color.RED);
                }
            } else {
                txtDamageID.requestFocus();
                txtDamageID.setFocusColor(Color.RED);
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Nothing to save !", ButtonType.OK);
            a.show();
        }
        update = false;
        getAllReturnItems();
        loadDate();
        setReturnID();
    }

    @FXML
    void searchReturnItem(ActionEvent event) {

    }

    void clearTextFields() {
        txtDamageID.clear();
        txtReturnID.clear();
        txtItemCode.clear();
        txtCustomerID.clear();
        txtOrderID.clear();
        txtCustomerName.clear();
        txtItemName.clear();
        txtBrand.clear();
        txtModel.clear();
        txtStatus.clear();
        txtDamageDate.clear();
        txtReturnDate.clear();
        txtContact.clear();

    }

    @FXML
    void update(ActionEvent event) {
        txtDamageID.setDisable(false);
        txtStatus.setDisable(false);
        update = true;
    }

    void loadDate() {
        txtReturnDate.setText(LocalDate.now().toString());
    }

    void getAllReturnItems() {
        try {
            ArrayList<ReturnItemTableDTO> allReturnItem = returnItemBO.getAllReturnItem();
            tblReturn.setItems(FXCollections.observableArrayList(allReturnItem));
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(ReturnItemController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void onMouseClick(MouseEvent event) {
        if (tblReturn.getSelectionModel().getSelectedIndex() != -1) {
            txtReturnID.setText(tblReturn.getSelectionModel().getSelectedItem().getReturnID());
            txtDamageID.setText(tblReturn.getSelectionModel().getSelectedItem().getDamageID());
            txtStatus.setText(tblReturn.getSelectionModel().getSelectedItem().getStatus());
            txtContact.setText(tblReturn.getSelectionModel().getSelectedItem().getContact());
            txtModel.setText(tblReturn.getSelectionModel().getSelectedItem().getModelNo());
            txtBrand.setText(tblReturn.getSelectionModel().getSelectedItem().getBrand());
            txtCustomerName.setText(tblReturn.getSelectionModel().getSelectedItem().getCustomerName());
            txtDamageDate.setText(tblReturn.getSelectionModel().getSelectedItem().getDamageDate());
            txtReturnDate.setText(tblReturn.getSelectionModel().getSelectedItem().getReturnDate());
            txtItemCode.setText(tblReturn.getSelectionModel().getSelectedItem().getItemCode());
            txtItemName.setText(tblReturn.getSelectionModel().getSelectedItem().getItemName());
        }
    }

}
