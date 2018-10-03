package lk.gamage.stockmgt.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.gamage.stockmgt.business.BOFactory;
import lk.gamage.stockmgt.business.custom.GRNBO;
import lk.gamage.stockmgt.business.custom.ItemBO;
import lk.gamage.stockmgt.business.custom.StockBO;
import lk.gamage.stockmgt.business.custom.SupplierOrderBO;
import lk.gamage.stockmgt.business.custom.impl.GRNBOImpl;
import lk.gamage.stockmgt.business.custom.impl.ItemBOImpl;
import lk.gamage.stockmgt.business.custom.impl.StockBOImpl;
import lk.gamage.stockmgt.common.IDGenerator;
import lk.gamage.stockmgt.common.Validation;
import lk.gamage.stockmgt.model.*;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddNewGRNController implements Initializable {
    @FXML
    private AnchorPane root;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXTextField txtGRNID;

    @FXML
    private JFXTextField txtGRNDate;

    @FXML
    private JFXTextField txtSupplierOrderID;
    @FXML
    private JFXTextField txtTotal;
    @FXML
    private JFXTextField txtStockID;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXTextField txtItemName;
    @FXML
    private Label lblDouble;

    @FXML
    private ImageView imgNotiyfy;

    @FXML
    private JFXTextField txtModelNo;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private Label lblNotify;
    @FXML
    private JFXTextField txtBuying;

    @FXML
    private JFXTextField txtSellingPrice;

    @FXML
    private TableView<AddNewGRNTableDTO> tblGRN;

    @FXML
    private JFXTextField txtBrand;

    @FXML
    private JFXButton btnAddForAuthorize;
    private double amount = 0;
    private double x = 0;
    double total = 0;
    @FXML
    private JFXButton btnDeleteRow;
    private ArrayList<String> supplierOrderIDs;
    private String status = null;
    private ItemBO itemBO;
    private StockBO stockBO;
    private GRNBO grnbo;
    private ArrayList<ItemDTO> allItem;
    private ArrayList<String> itemNames;
    private SupplierOrderBO supplierOrderBO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        supplierOrderBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.SUPPLIER_ORDER);
        itemBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.ITEM);
        stockBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.STOCK);
        grnbo = BOFactory.getInstance().getBO(BOFactory.BOTypes.GRN);
        tblGRN.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("stockID"));
        tblGRN.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblGRN.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("itemName"));
        tblGRN.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("buyingPrice"));
        tblGRN.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblGRN.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("amount"));
        loadGrnDate();
        getAllItems();
        loadItemNames();
        setGRNID();
        loadSupplierOrderIDs();
        loadToTextSupplierOrderID();
        txtSupplierOrderID.requestFocus();
        txtTotal.setText("0.0");

    }

    private void setGRNID() {
        try {
            String grnID;
            grnID = IDGenerator.getNewID("grn", "grnID", "G");
            txtGRNID.setText(grnID);
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(AddNewGRNController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void addForAuthorize(ActionEvent event) {
        if (Validation.cashValidation(txtTotal.getText())) {
            if (tblGRN.getItems().size() >= 1) {
                status = "Not Authorized";
                String grnID = txtGRNID.getText();
                String supplierOrderID = txtSupplierOrderID.getText();
                String date = txtGRNDate.getText();
                double payment = Double.parseDouble(txtTotal.getText());
                ArrayList<GRNDetailDTO> grnDetailDTOS = new ArrayList<>();
                for (int i = 0; i < tblGRN.getItems().size(); i++) {
                    String itemCode = (String) tblGRN.getColumns().get(1).getCellObservableValue(i).getValue();
                    String stockID = (String) tblGRN.getColumns().get(0).getCellObservableValue(i).getValue();
                    double price = (Double) tblGRN.getColumns().get(3).getCellObservableValue(i).getValue();
                    int qty = (Integer) tblGRN.getColumns().get(4).getCellObservableValue(i).getValue();
                    grnDetailDTOS.add(new GRNDetailDTO(grnID, itemCode, stockID, price, qty));
                }
                GRNDTO grndto = new GRNDTO(grnID, supplierOrderID, date, payment, status, grnDetailDTOS);
                try {
                    boolean addGRN = grnbo.addGRN(grndto);
                    if (addGRN) {
                        Alert a = new Alert(Alert.AlertType.INFORMATION, "GRN is waiting for owner Authorization !", ButtonType.OK);
                        a.showAndWait();
                        Notifications n = Notifications.create().title("Authorize GRN").text("Please Authorize GRN from Authorize GRN Window !").hideAfter(Duration.seconds(3)).position(Pos.BOTTOM_RIGHT);
                        n.darkStyle();
                        n.showError();
                        lblDouble.setVisible(true);
                        imgNotiyfy.setVisible(true);
                    } else {
                        Alert a = new Alert(Alert.AlertType.ERROR, "Something went wrong !", ButtonType.OK);
                        a.showAndWait();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Logger.getLogger(AddNewGRNController.class.getName()).log(Level.SEVERE, null, e);
                }
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "No items to save ! !", ButtonType.OK);
                a.showAndWait();
            }
        } else {
            txtTotal.requestFocus();
            txtTotal.setFocusColor(Color.RED);
        }

    }

    @FXML
    void focusToBuyingPrice(ActionEvent event) {
        if (Validation.orderQty(txtQty.getText())) {
            txtBuying.requestFocus();
            lblNotify.setText("");
        } else {
            txtQty.requestFocus();
            txtQty.setFocusColor(Color.RED);
            lblNotify.setText("Qty format is incorrect !");
        }
    }

    @FXML
    void focusToItemName(ActionEvent event) {
        if (Validation.idValidation(txtSupplierOrderID.getText(), "Vv")) {
            txtItemName.requestFocus();
            lblNotify.setText("");
        } else {
            txtSupplierOrderID.requestFocus();
            txtSupplierOrderID.setFocusColor(Color.RED);
            lblNotify.setText("SupplierOrder id is incorrect !");
        }
    }

    @FXML
    void addToTable(ActionEvent event) {
        if (Validation.idValidation(txtSupplierOrderID.getText(), "Vv")) {
            if (Validation.nameValidate(txtItemName.getText())) {
                if (Validation.orderQty(txtQty.getText())) {
                    if (Validation.cashValidation(txtSellingPrice.getText())) {
                        if (Validation.cashValidation(txtBuying.getText())) {
                            if (txtStockID.getText().isEmpty() || txtSupplierOrderID.getText().isEmpty() || txtGRNID.getText().isEmpty() || txtBuying.getText().isEmpty() || txtSellingPrice.getText().isEmpty() || txtQty.getText().isEmpty() || txtItemCode.getText().isEmpty() || txtItemName.getText().isEmpty()) {
                                Alert a = new Alert(Alert.AlertType.ERROR, "You need fill this form first !", ButtonType.OK);
                                Optional<ButtonType> buttonType = a.showAndWait();
                                if (txtSupplierOrderID.getText().isEmpty()) {
                                    txtSupplierOrderID.requestFocus();
                                } else if (txtItemName.getText().isEmpty()) {
                                    txtItemName.requestFocus();
                                } else if (txtQty.getText().isEmpty()) {
                                    txtQty.requestFocus();
                                } else if (txtBuying.getText().isEmpty()) {
                                    txtBuying.requestFocus();
                                } else {
                                    txtSellingPrice.requestFocus();
                                }
                            } else {
                                double price = Double.parseDouble(txtBuying.getText());
                                double qTy = Double.parseDouble(txtQty.getText());
                                amount = price * qTy;
                                AddNewGRNTableDTO addNewGRNTableDTO = new AddNewGRNTableDTO(txtStockID.getText(), txtItemCode.getText(), txtItemName.getText(), Double.parseDouble(txtBuying.getText()), Integer.parseInt(txtQty.getText()), amount);
                                tblGRN.getItems().add(addNewGRNTableDTO);
                                total = total + amount;
                                txtTotal.setText(total + "");
                                clearTextFileds();
                            }
                        } else {
                            txtBuying.requestFocus();
                            txtBuying.setFocusColor(Color.RED);
                            lblNotify.setText("Buying Price format is incorrect !");
                        }
                    } else {
                        txtSellingPrice.requestFocus();
                        txtSellingPrice.setFocusColor(Color.RED);
                        lblNotify.setText("Selling Price format is incorrect !");
                    }
                } else {
                    txtQty.requestFocus();
                    txtQty.setFocusColor(Color.RED);
                    lblNotify.setText("Qty format is incorrect !");
                }
            } else {
                txtItemName.requestFocus();
                txtItemName.setFocusColor(Color.RED);
                lblNotify.setText("Item Name format is incorrect !");
            }
        } else {
            txtSupplierOrderID.requestFocus();
            txtSupplierOrderID.setFocusColor(Color.RED);
            lblNotify.setText("Supplier Order ID format is incorrect !");
        }
    }

    @FXML
    void deleteRow(ActionEvent event) {

        if (tblGRN.getSelectionModel().getSelectedIndex() != -1) {
            double amount = tblGRN.getSelectionModel().getSelectedItem().getAmount();
            tblGRN.getItems().remove(tblGRN.getSelectionModel().getSelectedIndex());
            total = total - amount;
            txtTotal.setText(total + "");
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Please Select row to delete !", ButtonType.OK);
            a.showAndWait();
        }
    }

    void loadGrnDate() {
        txtGRNDate.setText(LocalDate.now().toString());
    }

    public void getAllItems() {
        try {
            allItem = itemBO.getAllItem();
            itemNames = new ArrayList<>();
            for (ItemDTO itemDTO : allItem) {
                itemNames.add(itemDTO.getItemName());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(AddNewGRNController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    void loadItemNames() {
        TextFields.bindAutoCompletion(txtItemName, itemNames);
    }

    @FXML
    void searchItems(ActionEvent event) {
        try {
            ItemDTO itemDTO = itemBO.searchItem(txtItemName.getText());
            if (itemDTO != null) {
                txtItemCode.setText(itemDTO.getItemCode());
                txtModelNo.setText(itemDTO.getModelNo());
                txtBrand.setText(itemDTO.getBrand());
                txtQty.requestFocus();
                searchStock();
            }
        } catch (Exception e) {
            Alert a = new Alert(Alert.AlertType.ERROR, "No such item !", ButtonType.OK);
            a.showAndWait();
        }
    }

    void searchStock() {
        try {
            StockDTO stockDTO = stockBO.searchStock(txtItemCode.getText());
            if (stockDTO != null) {
                txtSellingPrice.setText(stockDTO.getSellingPrice() + "");
                txtStockID.setText(stockDTO.getStockID());
            } else {
                txtStockID.setDisable(false);
                txtStockID.requestFocus();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(AddNewGRNController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void focusToQty(ActionEvent event) {
        if (Validation.idValidation(txtStockID.getText(), "Tt")) {
            txtQty.requestFocus();
            lblNotify.setText("");
        } else {
            txtStockID.requestFocus();
            txtStockID.setFocusColor(Color.RED);
            lblNotify.setText("Stock id format is incorrect !");
        }
    }

    @FXML
    void createStock(ActionEvent event) {
        if (txtSellingPrice.getText().isEmpty() || txtStockID.getText().isEmpty() || txtItemCode.getText().isEmpty() || txtQty.getText().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Fill the form correctly !", ButtonType.OK);
            Optional<ButtonType> buttonType = a.showAndWait();
            if (buttonType.get() == ButtonType.OK) {
                if (txtItemCode.getText().isEmpty()) {
                    txtItemCode.requestFocus();
                    txtItemCode.setFocusColor(Color.RED);
                } else if (txtStockID.getText().isEmpty()) {
                    txtStockID.requestFocus();
                    txtStockID.setFocusColor(Color.RED);
                } else if (txtSellingPrice.getText().isEmpty()) {
                    txtSellingPrice.requestFocus();
                    txtSellingPrice.setFocusColor(Color.RED);
                } else if (txtQty.getText().isEmpty()) {
                    txtQty.requestFocus();
                    txtQty.setFocusColor(Color.RED);
                }
            }
        } else {
            StockDTO stockDTO = new StockDTO(txtStockID.getText(), txtItemCode.getText(), 0, Double.parseDouble(txtSellingPrice.getText()));
            try {
                boolean addStock = stockBO.addStock(stockDTO);
                if (addStock) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "New stock is created !", ButtonType.OK);
                    Optional<ButtonType> buttonType = a.showAndWait();
                    Notifications notificationsn = Notifications.create().title("Stock").text("New Stock Added to the system  !").hideAfter(Duration.seconds(3)).position(Pos.BOTTOM_RIGHT);
                    notificationsn.darkStyle();
                    notificationsn.showInformation();
                    if (buttonType.get() == ButtonType.OK) {
                        addToTable(event);
                    }
                }
            } catch (Exception e) {
                addToTable(event);
            }
        }
    }

    @FXML
    void focusToSellingPrice(ActionEvent event) {
        if (Validation.cashValidation(txtBuying.getText())) {
            txtSellingPrice.requestFocus();
        } else {
            txtBuying.requestFocus();
            txtBuying.setFocusColor(Color.RED);
            lblNotify.setText("Buying price format is incorrect !");
        }
    }

    void clearTextFileds() {
        txtStockID.clear();
        txtItemCode.clear();
        txtItemName.clear();
        txtSellingPrice.clear();
        txtBuying.clear();
        txtModelNo.clear();
        txtBrand.clear();
        txtQty.clear();
    }

    void loadSupplierOrderIDs() {
        try {
            supplierOrderIDs = supplierOrderBO.getSupplierOrderIDs();
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(AddNewGRNController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    void loadToTextSupplierOrderID() {
        TextFields.bindAutoCompletion(txtSupplierOrderID, supplierOrderIDs);
    }

}
