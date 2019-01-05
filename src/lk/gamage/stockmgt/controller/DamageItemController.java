package lk.gamage.stockmgt.controller;

import com.jfoenix.animation.alert.JFXAlertAnimation;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import lk.gamage.stockmgt.business.custom.CustomerBO;
import lk.gamage.stockmgt.business.custom.CustomerOrdersBO;
import lk.gamage.stockmgt.business.custom.DamageItemBO;
import lk.gamage.stockmgt.business.custom.ItemBO;
import lk.gamage.stockmgt.business.custom.impl.CustomerBOImpl;
import lk.gamage.stockmgt.business.custom.impl.CustomerOrdersBOImpl;
import lk.gamage.stockmgt.business.custom.impl.DamageItemBOImpl;
import lk.gamage.stockmgt.business.custom.impl.ItemBOImpl;
import lk.gamage.stockmgt.common.IDGenerator;
import lk.gamage.stockmgt.common.Validation;
import lk.gamage.stockmgt.model.*;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DamageItemController implements Initializable {

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXTextField txtDamageID;

    @FXML
    private TableView<DamageItemTableDTO> tblDamage;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXTextField txtItemName;

    @FXML
    private JFXTextField txtBrand;

    @FXML
    private JFXTextField txtModel;

    @FXML
    private JFXComboBox<String> txtDamageType;
    private boolean update = false;

    @FXML
    private JFXTextField txtFault;

    @FXML
    private JFXTextField txtCustomerID;

    @FXML
    private JFXTextField txtCustomerName;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXTextField txtDamageItemSearch;

    @FXML
    private JFXButton btnAddNew;
    private ArrayList<ItemDTO> allItem;
    private ArrayList<String> itemNames;
    private ArrayList<String> damageDates;
    @FXML
    private JFXSpinner spnItemName;
    @FXML
    private JFXTextField txtNIC;
    @FXML
    private JFXTextField txtOrderID;
    private ArrayList<DamageItemTableDTO> allDamageItem;
    @FXML
    private JFXTextField txtOrderDate;
    @FXML
    private JFXTextField txtDate;
    @FXML
    private JFXSpinner spnDamageDate;

    @FXML
    private JFXTextField txtDamageDateSearch;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private Label lblNotify;
    private ItemBO itemBO;
    private CustomerBO customerBO;
    private DamageItemBO damageItemBO;
    private CustomerOrdersBO customerOrdersBO;
    private ArrayList<CustomerDTO> allCustomer;
    private ArrayList<String> customerOrderIDs;
    private ArrayList<String> customerNames;
    private ArrayList<ItemDTO> allItems;
    private ArrayList<String> itemName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        itemBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.ITEM);
        customerBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);
        damageItemBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.DAMAGE_ITEM);
        customerOrdersBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER_ORDERS);
        tblDamage.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("damageID"));
        tblDamage.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblDamage.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("orderID"));
        tblDamage.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("customerID"));
        tblDamage.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tblDamage.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("nic"));
        tblDamage.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("itemName"));
        tblDamage.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("brand"));
        tblDamage.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("modelNo"));
        tblDamage.getColumns().get(9).setCellValueFactory(new PropertyValueFactory<>("fault"));
        tblDamage.getColumns().get(10).setCellValueFactory(new PropertyValueFactory<>("damageType"));
        tblDamage.getColumns().get(11).setCellValueFactory(new PropertyValueFactory<>("damageDate"));
        getAllDamageItems();
        getDamageDates();
        loadDate();
        setDamageID();
        getAllItems();
        loadToTextFiled();
        getAllOrderIDs();
        loadToOrderText();
        loadToTextItemNames();
        loadDamageDates();
        txtDamageType.getItems().addAll("Shop Repair", "Company Repair");
    }

    @FXML
    void addNewItem(ActionEvent event) {
        enableTextFilds();
        txtOrderID.requestFocus();
        clearTextFildes();
        setDamageID();
        update = false;
    }

    private void clearTextFildes() {
        txtDamageID.clear();
        txtOrderID.clear();
        txtItemCode.clear();
        txtItemName.clear();
        txtCustomerID.clear();
        txtCustomerName.clear();
        txtNIC.clear();
        txtBrand.clear();
        txtModel.clear();
        txtFault.clear();
    }

    @FXML
    void damageDateKeyReleased(KeyEvent event) {
        spnDamageDate.setVisible(true);
        spnItemName.setVisible(false);
    }

    @FXML
    void itemNameReyReleased(KeyEvent event) {
        spnItemName.setVisible(true);
        spnDamageDate.setVisible(false);
    }

    @FXML
    void focusToCustomerName(ActionEvent event) {
        if (Validation.idValidation(txtOrderID.getText(), "P")) {
            try {
                DamageTextDTO allCustomerDetail = customerOrdersBO.getAllCustomerDetail(txtOrderID.getText());
                if (allCustomerDetail != null) {
                    txtCustomerName.setText(allCustomerDetail.getCustomerName());
                    txtCustomerID.setText(allCustomerDetail.getCustomerID());
                    txtNIC.setText(allCustomerDetail.getNic());
                    txtOrderDate.setText(allCustomerDetail.getOrderDate());
                }
            } catch (Exception e) {
                e.printStackTrace();
                Logger.getLogger(DamageItemController.class.getName()).log(Level.SEVERE, null, e);
            }
            txtItemName.requestFocus();
            lblNotify.setText("");
        } else {
            txtOrderID.requestFocus();
            txtOrderID.selectAll();
            lblNotify.setText("Order Id format is incorrect !");
        }
    }

    @FXML
    void deleteItem(ActionEvent event) {
        try {
            boolean damageItem = damageItemBO.deleteDamageItem(txtDamageID.getText());
            if (damageItem) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Successfully Deleted !", ButtonType.OK);
                a.show();
                Notifications notificationsn = Notifications.create().title("Damage Items").text("Deleted Damage Item !").hideAfter(Duration.seconds(3)).position(Pos.BOTTOM_RIGHT);
                notificationsn.darkStyle();
                notificationsn.showInformation();
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Not Deleted !", ButtonType.OK);
                a.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(DamageItemController.class.getName()).log(Level.SEVERE, null, e);
        }
        getAllDamageItems();
    }

    @FXML
    void onMouseClick(MouseEvent event) {
        if (tblDamage.getSelectionModel().getSelectedIndex() != -1) {
            txtDamageID.setText(tblDamage.getSelectionModel().getSelectedItem().getDamageID());
            txtOrderID.setText(tblDamage.getSelectionModel().getSelectedItem().getOrderID());
            txtItemCode.setText(tblDamage.getSelectionModel().getSelectedItem().getItemCode());
            txtItemName.setText(tblDamage.getSelectionModel().getSelectedItem().getItemName());
            txtCustomerID.setText(tblDamage.getSelectionModel().getSelectedItem().getCustomerID());
            txtCustomerName.setText(tblDamage.getSelectionModel().getSelectedItem().getCustomerName());
            txtNIC.setText(tblDamage.getSelectionModel().getSelectedItem().getNic());
            txtBrand.setText(tblDamage.getSelectionModel().getSelectedItem().getBrand());
            txtModel.setText(tblDamage.getSelectionModel().getSelectedItem().getModelNo());
            txtFault.setText(tblDamage.getSelectionModel().getSelectedItem().getFault());
            txtDate.setText(tblDamage.getSelectionModel().getSelectedItem().getDamageDate());
            txtDamageType.getSelectionModel().select(tblDamage.getSelectionModel().getSelectedItem().getDamageType());
        }
    }

    @FXML
    void saveItem(ActionEvent event) {
        if (Validation.idValidation(txtOrderID.getText(), "Pp")) {
            if (Validation.nameValidate(txtItemName.getText())) {
                if (Validation.nameValidate(txtDamageType.getSelectionModel().getSelectedItem())) {
                    if (Validation.nameValidate(txtFault.getText())) {
                        if (txtDamageID.getText().isEmpty() || txtItemCode.getText().isEmpty() || txtItemName.getText().isEmpty() || txtCustomerID.getText().isEmpty() || txtCustomerName.getText().isEmpty() || txtFault.getText().isEmpty() || txtOrderID.getText().isEmpty()) {
                            Alert a = new Alert(Alert.AlertType.INFORMATION, "Fill the form correctly !", ButtonType.OK);
                            Optional<ButtonType> buttonType = a.showAndWait();
                            if (buttonType.get() == ButtonType.OK) {
                                if (txtFault.getText().isEmpty()) {
                                    txtFault.requestFocus();
                                } else if (txtOrderID.getText().isEmpty()) {
                                    txtOrderID.requestFocus();
                                } else if (txtItemName.getText().isEmpty()) {
                                    txtItemName.requestFocus();
                                }
                            }
                        } else {
                            if (update == false) {
                                DamageItemDTO damageItemDTO = new DamageItemDTO(txtDamageID.getText(), txtItemCode.getText(), txtFault.getText(), txtDamageType.getSelectionModel().getSelectedItem().toString(), txtDate.getText(), txtOrderID.getText());
                                try {
                                    boolean addDamageItem = damageItemBO.addDamageItem(damageItemDTO);
                                    if (addDamageItem) {
                                        Alert a = new Alert(Alert.AlertType.INFORMATION, "Successfully added !", ButtonType.OK);
                                        a.show();
                                        Notifications notificationsn = Notifications.create().title("Damage Items").text("Add new Damage item to the system !").hideAfter(Duration.seconds(3)).position(Pos.BOTTOM_RIGHT);
                                        notificationsn.darkStyle();
                                        notificationsn.showInformation();
                                        setDamageID();
                                        disableTextFileds();
                                        clearTextFildes();
                                    } else {
                                        Alert a = new Alert(Alert.AlertType.ERROR, "Not Added !", ButtonType.OK);
                                        a.show();
                                    }

                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Logger.getLogger(DamageItemController.class.getName()).log(Level.SEVERE, null, e);
                                }
                            } else if (update == true) {
                                DamageItemDTO damageItemDTO = new DamageItemDTO(txtDamageID.getText(), txtItemCode.getText(), txtFault.getText(), txtDamageType.getSelectionModel().getSelectedItem().toString(), txtDate.getText(), txtOrderID.getText());
                                try {
                                    boolean updateDamageItem = damageItemBO.updateDamageItem(damageItemDTO);
                                    if (updateDamageItem) {
                                        Alert a = new Alert(Alert.AlertType.INFORMATION, "Successfully Updated !", ButtonType.OK);
                                        a.show();
                                        Notifications notificationsn = Notifications.create().title("Damage Items").text("Updated existing Damage Item !").hideAfter(Duration.seconds(3)).position(Pos.BOTTOM_RIGHT);
                                        notificationsn.darkStyle();
                                        notificationsn.showInformation();
                                        disableTextFileds();
                                        clearTextFildes();
                                    } else {
                                        Alert a = new Alert(Alert.AlertType.ERROR, "Not Updated !", ButtonType.OK);
                                        a.show();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Logger.getLogger(DamageItemController.class.getName()).log(Level.SEVERE, null, e);
                                }
                            }
                            getAllDamageItems();
                        }
                    } else {
                        txtFault.requestFocus();
                        txtFault.setFocusColor(Color.RED);
                        lblNotify.setText("Fault format is incorrect !");
                    }
                } else {
                    txtDamageType.requestFocus();
                    txtDamageType.setFocusColor(Color.RED);
                    lblNotify.setText("Damage Type format is incorrect !");
                }
            } else {
                txtItemName.requestFocus();
                txtItemName.setFocusColor(Color.RED);
                lblNotify.setText("Item Name format is incorrect !");
            }
        } else {
            txtOrderID.requestFocus();
            txtOrderID.setFocusColor(Color.RED);
            lblNotify.setText("Order ID format is incorrect !");
        }
    }

    @FXML
    void searchItem(ActionEvent event) {
        if (Validation.nameValidate(txtItemName.getText())) {
            try {
                ItemDTO itemDTO = itemBO.searchItem(txtItemName.getText());
                if (itemDTO != null) {
                    txtItemCode.setText(itemDTO.getItemCode());
                    txtBrand.setText(itemDTO.getBrand());
                    txtModel.setText(itemDTO.getModelNo());
                    txtDamageType.requestFocus();
                } else {
                    Alert a = new Alert(Alert.AlertType.ERROR, "No such Item !", ButtonType.OK);
                    a.show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Logger.getLogger(DamageItemController.class.getName()).log(Level.SEVERE, null, e);
            }
            lblNotify.setText("");
        } else {
            txtItemName.requestFocus();
            txtItemName.selectAll();
            lblNotify.setText("Item name format is incorrect !");
        }
    }

    @FXML
    void focusToFault(ActionEvent event) {
        txtFault.requestFocus();
    }

    @FXML
    void searchCustomer(ActionEvent event) {
        if (Validation.nameValidate(txtCustomerName.getText())) {
            try {
                CustomerDTO customerDTO = customerBO.searchCustomer(txtCustomerName.getText());
                if (customerDTO != null) {
                    txtCustomerID.setText(customerDTO.getCustomerID());
                    txtNIC.setText(customerDTO.getNic());
                    txtFault.requestFocus();
                    lblNotify.setText("");
                } else {
                    Alert a = new Alert(Alert.AlertType.ERROR, "No such Customer !", ButtonType.OK);
                    a.show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Logger.getLogger(DamageItemController.class.getName()).log(Level.SEVERE, null, e);
            }
        } else {
            txtCustomerName.requestFocus();
            txtCustomerName.selectAll();
            lblNotify.setText("Customer Name format is incorrect !");
        }
    }

    @FXML
    void updateItem(ActionEvent event) {
        enableTextFilds();
        update = true;
    }

    void loadDate() {
        txtDate.setText(LocalDate.now().toString());
    }

    void enableTextFilds() {
        txtItemName.setDisable(false);
        txtFault.setDisable(false);
        txtDamageType.setDisable(false);
        txtOrderID.setDisable(false);

    }

    private void setDamageID() {
        try {
            String damageID;
            damageID = IDGenerator.getNewID("DamageItem", "damageID", "D");
            txtDamageID.setText(damageID);
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(DamageItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void getAllItems() {
        try {
            allItem = itemBO.getAllItem();
            itemNames = new ArrayList<>();
            for (ItemDTO itemDTO : allItem) {
                itemNames.add(itemDTO.getItemName());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(DamageItemController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    void loadToTextFiled() {
        TextFields.bindAutoCompletion(txtItemName, itemNames);
    }

    @FXML
    void searchDamageItem(ActionEvent event) {
        if (Validation.dateValidation(txtDamageDateSearch.getText())) {
            try {
                ArrayList<DamageItemTableDTO> dtos = damageItemBO.searchDamageItems(txtDamageItemSearch.getText(), txtDamageDateSearch.getText());
                tblDamage.setItems(FXCollections.observableArrayList(dtos));
            } catch (Exception e) {
                e.printStackTrace();
                Logger.getLogger(DamageItemController.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    @FXML
    void focusToDamageDate(ActionEvent event) {
        if (Validation.nameValidate(txtDamageItemSearch.getText())) {
            txtDamageDateSearch.requestFocus();
        } else {
            txtDamageItemSearch.requestFocus();
            txtDamageItemSearch.setFocusColor(Color.RED);
        }
    }

    void getAllOrderIDs() {
        try {
            customerOrderIDs = customerOrdersBO.getCustomerOrderIDs();
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(DamageItemController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    void loadToTextItemNames() {
        TextFields.bindAutoCompletion(txtDamageItemSearch, itemNames);
    }

    void getDamageDates() {
        try {
            allDamageItem = damageItemBO.getAllDamageItem();
            damageDates = new ArrayList<>();
            for (DamageItemTableDTO d : allDamageItem) {
                damageDates.add(d.getDamageDate());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(DamageItemController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    void loadDamageDates() {
        TextFields.bindAutoCompletion(txtDamageDateSearch, damageDates);
    }

    void loadToOrderText() {
        TextFields.bindAutoCompletion(txtOrderID, customerOrderIDs);
    }

    void getAllDamageItems() {
        try {
            ArrayList<DamageItemTableDTO> allDamageItem = damageItemBO.getAllDamageItem();
            tblDamage.setItems(FXCollections.observableArrayList(allDamageItem));
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(DamageItemController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    void disableTextFileds() {
        txtItemName.setDisable(true);
        txtFault.setDisable(true);
        txtDamageType.setDisable(true);
        txtOrderID.setDisable(true);
    }
}
