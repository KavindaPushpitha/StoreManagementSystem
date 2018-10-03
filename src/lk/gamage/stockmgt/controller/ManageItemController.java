package lk.gamage.stockmgt.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import lk.gamage.stockmgt.business.BOFactory;
import lk.gamage.stockmgt.business.custom.ItemBO;
import lk.gamage.stockmgt.business.custom.impl.ItemBOImpl;
import lk.gamage.stockmgt.common.IDGenerator;
import lk.gamage.stockmgt.common.Validation;
import lk.gamage.stockmgt.model.ItemDTO;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManageItemController implements Initializable {
    @FXML
    private JFXTextField txtSearchItem;

    @FXML
    private JFXButton btnAddNewItem;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXTextField txtItemName;

    @FXML
    private JFXTextField txtBrand;

    @FXML
    private JFXTextField txtModelNo;
    @FXML
    private Label lblNotify;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXSpinner spnSpinner;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private TableView<ItemDTO> tblAllItem;

    @FXML
    private JFXButton btnDelete;
    private boolean update = false;
    private static ItemBO itemBO;
    private ArrayList<String> itemName;

    private ArrayList<ItemDTO> allItem;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        itemBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.ITEM);
        try {
            tblAllItem.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
            tblAllItem.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("itemName"));
            tblAllItem.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("brand"));
            tblAllItem.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("ModelNo"));
            loadAllItems();
            loadItemNames();
            setItemCode();
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(ManageItemController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    private void setItemCode() {
        try {
            String itemCode;
            itemCode = IDGenerator.getNewID("Items", "itemCode", "E");
            txtItemCode.setText(itemCode);
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(ManageItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadItemNames() {
        TextFields.bindAutoCompletion(txtSearchItem, itemName);
    }


    private void loadAllItems() throws Exception {
        allItem = itemBO.getAllItem();
        tblAllItem.setItems(FXCollections.observableArrayList(allItem));
        itemName = new ArrayList<>();
        for (ItemDTO itemDTO : allItem) {
            itemName.add(itemDTO.getItemName());
        }
    }


    @FXML
    void onKeyRelese(KeyEvent event) {
        spnSpinner.setVisible(true);
    }

    @FXML
    void addNewItem(ActionEvent event) {
        txtItemName.setDisable(false);
        txtBrand.setDisable(false);
        txtModelNo.setDisable(false);
        setItemCode();
        clearTextFileds();
        txtItemName.requestFocus();
        update = false;
    }

    @FXML
    void deleteItem(ActionEvent event) {
        if (tblAllItem.getSelectionModel().getSelectedIndex() != -1) {
            Alert delete = new Alert(Alert.AlertType.ERROR, "you need to select a Row first !", ButtonType.OK);
            delete.show();
        } else {
            try {
                boolean deleteItem = itemBO.deleteItem(txtItemCode.getText());
                if (deleteItem) {
                    Alert delete = new Alert(Alert.AlertType.INFORMATION, "Successfully Deleted !", ButtonType.OK);
                    delete.show();
                    Notifications notificationsn = Notifications.create().title("Items").text("Item deleted from the system !").hideAfter(Duration.seconds(3)).position(Pos.BOTTOM_RIGHT);
                    notificationsn.darkStyle();
                    notificationsn.showInformation();
                    loadAllItems();
                    clearTextFileds();
                    setItemCode();
                } else {
                    Alert delete = new Alert(Alert.AlertType.ERROR, "Not Deleted !", ButtonType.OK);
                    delete.show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Logger.getLogger(ManageItemController.class.getName()).log(Level.SEVERE, null, e);
            }
        }

    }

    @FXML
    void mouseClicked(MouseEvent event) {
        if (tblAllItem.getSelectionModel().getSelectedIndex() != -1) {
            txtSearchItem.clear();
            ItemDTO selectedItem = tblAllItem.getSelectionModel().getSelectedItem();
            txtItemCode.setText(selectedItem.getItemCode());
            txtItemName.setText(selectedItem.getItemName());
            txtBrand.setText(selectedItem.getBrand());
            txtModelNo.setText(selectedItem.getModelNo());
        }
    }

    @FXML
    void saveItem(ActionEvent event) {
        if (Validation.addressValidate(txtItemName.getText())) {
            if (Validation.addressValidate(txtItemName.getText())) {
                if (Validation.addressValidate(txtBrand.getText())) {
                    if (Validation.addressValidate(txtModelNo.getText())) {
                        ItemDTO itemDTO;
                        if (txtItemCode.getText().isEmpty() || txtItemName.getText().isEmpty() || txtBrand.getText().isEmpty() || txtModelNo.getText().isEmpty()) {

                            itemDTO = null;
                            Alert add = new Alert(Alert.AlertType.ERROR, "Please Add Some Data !", ButtonType.OK);
                            Optional<ButtonType> buttonType = add.showAndWait();
                            if (buttonType.get() == ButtonType.OK) {
                                if (txtItemName.getText().isEmpty()) {
                                    txtItemName.requestFocus();
                                } else if (txtBrand.getText().isEmpty()) {
                                    txtBrand.requestFocus();
                                } else {
                                    txtModelNo.requestFocus();
                                }
                            }
                        } else {
                            itemDTO = new ItemDTO(txtItemCode.getText(), txtItemName.getText(), txtBrand.getText(), txtModelNo.getText());


                            if (update == false) {
                                try {
                                    boolean addItem = itemBO.addItem(itemDTO);
                                    if (addItem) {
                                        Alert add = new Alert(Alert.AlertType.INFORMATION, "Successfully Added !", ButtonType.OK);
                                        add.show();
                                        Notifications notificationsn = Notifications.create().title("Items").text("New Item is Add to the system !").hideAfter(Duration.seconds(3)).position(Pos.BOTTOM_RIGHT);
                                        notificationsn.darkStyle();
                                        notificationsn.showInformation();
                                        loadAllItems();
                                        setItemCode();
                                        clearTextFileds();
                                        disableTxtFilds();
                                    } else {
                                        Alert add = new Alert(Alert.AlertType.ERROR, "Not Added !", ButtonType.OK);
                                        add.show();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Logger.getLogger(ManageItemController.class.getName()).log(Level.SEVERE, null, e);
                                }
                            } else {
                                try {
                                    boolean updateItem = itemBO.updateItem(itemDTO);
                                    if (updateItem) {
                                        Alert updated = new Alert(Alert.AlertType.INFORMATION, "Successfully Updated !", ButtonType.OK);
                                        updated.show();
                                        Notifications notificationsn = Notifications.create().title("Items").text("Updated existing Item !").hideAfter(Duration.seconds(3)).position(Pos.BOTTOM_RIGHT);
                                        notificationsn.darkStyle();
                                        notificationsn.showInformation();
                                        loadAllItems();
                                        clearTextFileds();
                                        disableTxtFilds();

                                    } else {
                                        Alert updated = new Alert(Alert.AlertType.ERROR, "Not Updated !", ButtonType.OK);
                                        updated.show();
                                    }
                                } catch (Exception e) {
                                    Alert add = new Alert(Alert.AlertType.ERROR, "Please Add Some Data !", ButtonType.OK);
                                    add.show();
                                }
                            }
                        }
                    } else {
                        txtModelNo.requestFocus();
                        txtModelNo.setFocusColor(Color.RED);
                        lblNotify.setText("Model No format is incorrect !");
                    }
                } else {
                    txtBrand.requestFocus();
                    txtBrand.setFocusColor(Color.RED);
                    lblNotify.setText("Item Brand format is incorrect !");
                }
            } else {
                txtItemName.requestFocus();
                txtItemName.setFocusColor(Color.RED);
                lblNotify.setText("Item Name format is incorrect !");
            }
        } else {
            txtItemName.requestFocus();
            txtItemName.setFocusColor(Color.RED);
            lblNotify.setText("Item Name format is incorrect !");
        }
    }

    @FXML
    void updateItem(ActionEvent event) {
        txtItemName.setDisable(false);
        txtBrand.setDisable(false);
        txtModelNo.setDisable(false);
        update = true;

    }

    public void clearTextFileds() {
        txtItemName.clear();
        txtBrand.clear();
        txtModelNo.clear();
    }

    public void disableTxtFilds() {
        txtModelNo.setDisable(true);
        txtItemName.setDisable(true);
        txtBrand.setDisable(true);
    }

    @FXML
    void SearchItems(ActionEvent event) {
        spnSpinner.setVisible(false);
        try {
            ItemDTO itemDTO = itemBO.searchItem(txtSearchItem.getText());
            txtItemCode.setText(itemDTO.getItemCode());
            txtItemName.setText(itemDTO.getItemName());
            txtBrand.setText(itemDTO.getBrand());
            txtModelNo.setText(itemDTO.getModelNo());
        } catch (Exception e) {
            Alert noItem = new Alert(Alert.AlertType.ERROR, "No such Item !", ButtonType.OK);
            noItem.show();
        }

    }

    @FXML
    void changeFocusToBrand(ActionEvent event) {
        if (Validation.addressValidate(txtItemName.getText())) {
            txtBrand.requestFocus();
            lblNotify.setText("");
        } else {
            txtItemName.requestFocus();
            txtItemName.setFocusColor(Color.RED);
            lblNotify.setText("Item name pattern is incorrect !");
        }

    }

    @FXML
    void changeFocusToItemName(ActionEvent event) {
        txtItemName.requestFocus();
    }

    @FXML
    void changeFocusToModelNo(ActionEvent event) {
        if (Validation.addressValidate(txtBrand.getText())) {
            txtModelNo.requestFocus();
            lblNotify.setText("");
        } else {
            txtBrand.requestFocus();
            txtBrand.setFocusColor(Color.RED);
            lblNotify.setText("Item Brand pattern is incorrect !");
        }
    }

    @FXML
    void onAction(ActionEvent event) {
        saveItem(event);
    }
}
