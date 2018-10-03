package lk.gamage.stockmgt.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.gamage.stockmgt.business.BOFactory;
import lk.gamage.stockmgt.business.custom.*;
import lk.gamage.stockmgt.business.custom.impl.GRNBOImpl;
import lk.gamage.stockmgt.business.custom.impl.GRNDetailBOImpl;
import lk.gamage.stockmgt.business.custom.impl.ItemBOImpl;
import lk.gamage.stockmgt.business.custom.impl.StockBOImpl;
import lk.gamage.stockmgt.common.Validation;
import lk.gamage.stockmgt.entity.GRNDetail_PK;
import lk.gamage.stockmgt.model.*;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthorizeGRNController implements Initializable {
    @FXML
    private JFXButton btnSave;
    private double fullTotal;
    @FXML
    private TableView<AuthorizeGRNTableDTO> tblGRN;
    @FXML
    private AnchorPane dashRoot;
    @FXML
    private JFXTextField txtGRNID;

    @FXML
    private JFXTextField txtGRNDate;

    @FXML
    private JFXTextField txtSupplierOrderID;
    @FXML
    private JFXTextField txtStockID;
    @FXML
    private JFXTextField txtStatus;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXTextField txtItemName;

    @FXML
    private JFXTextField txtModelNo;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXTextField txtBuyingPrice;

    @FXML
    private JFXTextField txtBrand;
    private String status = null;
    @FXML
    private Label lblNotify;
    @FXML
    private JFXButton btnDelete;
    private ArrayList<ItemDTO> allItem;
    private ArrayList<String> itemNames;
    @FXML
    private JFXTextField txtTotal;
    @FXML
    private JFXButton btnAuthorize;
    private GRNBO grnbo;
    private GRNDetailBO grnDetailBO;
    private ItemBO itemBO;
    private StockBO stockBO;
    private double total = 0.0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        grnbo = BOFactory.getInstance().getBO(BOFactory.BOTypes.GRN);
        grnDetailBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.GRN_DETAIL);
        itemBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.ITEM);
        stockBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.STOCK);
        searchUnauthorizeGRN();
        tblGRN.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("stockID"));
        tblGRN.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblGRN.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("itemName"));
        tblGRN.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("brand"));
        tblGRN.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("modelNo"));
        tblGRN.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblGRN.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("buyingPrice"));
        loadGRNDeatails();
        loadAllItemNames();
        loadItemNames();

    }

    private void loadItemNames() {
        TextFields.bindAutoCompletion(txtItemName, itemNames);
    }

    @FXML
    void authorizeGRN(ActionEvent event) {
        if (Validation.cashValidation(txtTotal.getText())) {
            if (tblGRN.getItems().size() >= 1) {
                status = "Authorized";
                String grnidText = txtGRNID.getText();
                ArrayList<GRNDetailDTO> detailDTOS = new ArrayList<>();
                for (int i = 0; i < tblGRN.getItems().size(); i++) {
                    String itemCode = (String) tblGRN.getColumns().get(1).getCellObservableValue(i).getValue();
                    String stockID = (String) tblGRN.getColumns().get(0).getCellObservableValue(i).getValue();
                    double buyingPrice = (Double) tblGRN.getColumns().get(6).getCellObservableValue(i).getValue();
                    int Qty = (Integer) tblGRN.getColumns().get(5).getCellObservableValue(i).getValue();
                    detailDTOS.add(new GRNDetailDTO(grnidText, itemCode, stockID, buyingPrice, Qty));
                }
                GRNDTO grndto = new GRNDTO(txtGRNID.getText(), txtSupplierOrderID.getText(), txtGRNDate.getText(), Double.parseDouble(txtTotal.getText()), "Authorized", detailDTOS);
                try {
                    boolean updateGRN = grnbo.updateGRN(grndto);
                    if (updateGRN) {
                        Alert a = new Alert(Alert.AlertType.INFORMATION, "GRN Detail Updated !", ButtonType.OK);
                        a.show();
                        Notifications n = Notifications.create().title("Authorize GRN").text("GRN is Authorized !").hideAfter(Duration.seconds(3)).position(Pos.BOTTOM_RIGHT);
                        n.darkStyle();
                        n.showInformation();
                        try {
                            Parent parent = FXMLLoader.load(this.getClass().getResource("/lk/gamage/stockmgt/view/DashBoard.fxml"));
                            Scene scene = new Scene(parent);
                            Stage stage = (Stage) this.dashRoot.getScene().getWindow();
                            stage.setScene(scene);
                            stage.centerOnScreen();
                        } catch (IOException e) {
                            e.printStackTrace();
                            Logger.getLogger(AuthorizeGRNController.class.getName()).log(Level.SEVERE, null, e);
                        }
                    } else {
                        Alert a = new Alert(Alert.AlertType.ERROR, "GRN Detail Not Updated !", ButtonType.OK);
                        a.show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Logger.getLogger(AuthorizeGRNController.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        } else {
            txtTotal.requestFocus();
            txtTotal.setFocusColor(Color.RED);
            lblNotify.setText("Total format is incorrect !");
        }
    }

    void clearText() {
        txtItemName.clear();
        txtItemCode.clear();
        txtBrand.clear();
        txtModelNo.clear();
        txtBuyingPrice.clear();
        txtStockID.clear();
        txtQty.clear();
    }

    @FXML
    void searchItem(ActionEvent event) {
        try {
            ItemDTO itemDTO = itemBO.searchItem(txtItemName.getText());
            if (itemDTO != null) {
                txtItemCode.setText(itemDTO.getItemCode());
                txtBrand.setText(itemDTO.getBrand());
                txtModelNo.setText(itemDTO.getModelNo());
                searchStock();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(AuthorizeGRNController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    void searchStock() {
        try {
            StockDTO stockDTO = stockBO.searchStock(txtItemCode.getText());
            if (stockDTO != null) {
                txtStockID.setText(stockDTO.getStockID());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(AuthorizeGRNController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void delete(ActionEvent event) {
        total = Double.parseDouble(txtTotal.getText());
        if (tblGRN.getItems().size() == 1) {
            if (tblGRN.getSelectionModel().getSelectedIndex() != -1) {
                GRNDetailDTO grnDetailDTO = new GRNDetailDTO(txtGRNID.getText(), txtItemCode.getText(), txtStockID.getText(), Double.parseDouble(txtBuyingPrice.getText()), Integer.parseInt(txtQty.getText()));
                try {
                    boolean deleteGRNDetails = grnbo.deleteGRNDetails(grnDetailDTO);
                    if (deleteGRNDetails) {
                        Alert a = new Alert(Alert.AlertType.INFORMATION, "GRN Detail Deleted !", ButtonType.OK);
                        a.show();
                        Notifications notificationsn = Notifications.create().title("Authorize GRN").text("GRN Detail is Deleted !").hideAfter(Duration.seconds(3)).position(Pos.BOTTOM_RIGHT);
                        notificationsn.darkStyle();
                        notificationsn.showInformation();
                        double buying = Double.parseDouble(txtBuyingPrice.getText());
                        int qTy = Integer.parseInt(txtQty.getText());
                        total = total - (buying * qTy);
                        txtTotal.setText(total + "");
                        clearText();
                        txtGRNID.clear();
                        txtSupplierOrderID.clear();
                        txtGRNDate.clear();
                        txtStatus.clear();

                    } else {
                        Alert a = new Alert(Alert.AlertType.ERROR, "GRN Detail Not Deleted !", ButtonType.OK);
                        a.show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Logger.getLogger(AuthorizeGRNController.class.getName()).log(Level.SEVERE, null, e);
                }
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Please Select a row !", ButtonType.OK);
                a.show();
            }
        } else {
            if (tblGRN.getSelectionModel().getSelectedIndex() != -1) {
                GRNDetailDTO grnDetailDTO = new GRNDetailDTO(txtGRNID.getText(), txtItemCode.getText(), txtStockID.getText(), Double.parseDouble(txtBuyingPrice.getText()), Integer.parseInt(txtQty.getText()));
                try {
                    boolean deleteGRNDetail = grnDetailBO.deleteGRNDetail(grnDetailDTO);
                    if (deleteGRNDetail) {
                        Alert a = new Alert(Alert.AlertType.INFORMATION, "GRN Detail Deleted !", ButtonType.OK);
                        a.show();
                        Notifications notificationsn = Notifications.create().title("Authorize GRN").text("GRN Detail Deleted !").hideAfter(Duration.seconds(3)).position(Pos.BOTTOM_RIGHT);
                        notificationsn.darkStyle();
                        notificationsn.showInformation();
                        double buying = Double.parseDouble(txtBuyingPrice.getText());
                        int qTy = Integer.parseInt(txtQty.getText());
                        total = total - (buying * qTy);
                        txtTotal.setText(total + "");
                        clearText();
                    } else {
                        Alert a = new Alert(Alert.AlertType.ERROR, "GRN Detail Not Deleted !", ButtonType.OK);
                        a.show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Logger.getLogger(AuthorizeGRNController.class.getName()).log(Level.SEVERE, null, e);
                }
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Please Select a row !", ButtonType.OK);
                a.show();
            }
        }
        loadGRNDeatails();
    }

    @FXML
    void onMouseClick(MouseEvent event) {
        if (tblGRN.getSelectionModel().getSelectedIndex() != -1) {
            txtItemCode.setText(tblGRN.getSelectionModel().getSelectedItem().getItemCode());
            txtItemName.setText(tblGRN.getSelectionModel().getSelectedItem().getItemName());
            txtStockID.setText(tblGRN.getSelectionModel().getSelectedItem().getStockID());
            txtQty.setText(tblGRN.getSelectionModel().getSelectedItem().getQty() + "");
            txtBuyingPrice.setText(tblGRN.getSelectionModel().getSelectedItem().getBuyingPrice() + "");
            txtBrand.setText(tblGRN.getSelectionModel().getSelectedItem().getBrand());
            txtModelNo.setText(tblGRN.getSelectionModel().getSelectedItem().getModelNo());
            tblGRN.getItems().remove(tblGRN.getSelectionModel().getSelectedIndex());
            double selectedAmount = Integer.parseInt(txtQty.getText()) * Double.parseDouble(txtBuyingPrice.getText());
            txtTotal.setText(Double.parseDouble(txtTotal.getText()) - selectedAmount + "");
        }
    }

    @FXML
    void saveChanges(ActionEvent event) {
        if (Validation.nameValidate(txtItemName.getText())) {
            if (Validation.idValidation(txtSupplierOrderID.getText(), "Vv")) {
                if (Validation.orderQty(txtQty.getText())) {
                    if (Validation.cashValidation(txtBuyingPrice.getText())) {
                        if (txtItemName.getText().isEmpty() || txtQty.getText().isEmpty() || txtBuyingPrice.getText().isEmpty() || txtSupplierOrderID.getText().isEmpty()) {
                            Alert a = new Alert(Alert.AlertType.ERROR, "You need to fill the form first !", ButtonType.OK);
                            Optional<ButtonType> buttonType = a.showAndWait();
                            if (buttonType.get() == ButtonType.OK) {
                                if (txtSupplierOrderID.getText().isEmpty()) {
                                    txtSupplierOrderID.requestFocus();
                                } else if (txtItemName.getText().isEmpty()) {
                                    txtItemName.requestFocus();
                                } else if (txtBuyingPrice.getText().isEmpty()) {
                                    txtBuyingPrice.requestFocus();
                                } else {
                                    txtQty.requestFocus();
                                }
                            }
                        } else {
                            try {
                                int qty = Integer.parseInt(txtQty.getText());
                                double price = Double.parseDouble(txtBuyingPrice.getText());
                                fullTotal = price * qty;
                                txtTotal.setText(Double.parseDouble(txtTotal.getText()) + fullTotal + "");
                                AuthorizeGRNTableDTO grnTableDTO = new AuthorizeGRNTableDTO(txtStockID.getText(), txtItemCode.getText(), txtItemName.getText(), txtBrand.getText(), txtModelNo.getText(), Double.parseDouble(txtBuyingPrice.getText()), Integer.parseInt(txtQty.getText()));
                                tblGRN.getItems().add(grnTableDTO);
                                clearText();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    } else {
                        txtBuyingPrice.requestFocus();
                        txtBuyingPrice.setFocusColor(Color.RED);
                        lblNotify.setText("Buying Price format is incorrect !");
                    }
                } else {
                    txtQty.requestFocus();
                    txtQty.setFocusColor(Color.RED);
                    lblNotify.setText("Qty format is incorrect !");
                }
            } else {
                txtSupplierOrderID.requestFocus();
                txtSupplierOrderID.setFocusColor(Color.RED);
                lblNotify.setText("Supplier Order ID format is incorrect !");
            }
        } else {
            txtItemName.requestFocus();
            txtItemName.setFocusColor(Color.RED);
            lblNotify.setText("Item Name format is incorrect !");
        }
    }

    void searchUnauthorizeGRN() {
        try {
            GRNDTO grndto = grnbo.searchGRN("not authorized");
            txtGRNID.setText(grndto.getGrnID());
            txtGRNDate.setText(grndto.getGrnDate());
            txtSupplierOrderID.setText(grndto.getSupplierOrderID());
            txtStatus.setText(grndto.getStatus());
            txtTotal.setText(grndto.getSupplierPayment() + "");
        } catch (Exception e) {
            Notifications n = Notifications.create().title("Authorize GRN").text("No GRN to Authorize !").hideAfter(Duration.seconds(3)).position(Pos.BOTTOM_RIGHT);
            n.darkStyle();
            n.showError();
        }
    }

    void loadGRNDeatails() {
        try {
            ArrayList<AuthorizeGRNTableDTO> allGRNDetail = grnDetailBO.getAllGRNDetail(txtGRNID.getText());
            tblGRN.setItems(FXCollections.observableArrayList(allGRNDetail));
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(AuthorizeGRNController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    void loadAllItemNames() {
        try {
            allItem = itemBO.getAllItem();
            itemNames = new ArrayList<>();
            for (ItemDTO itemDTO : allItem) {
                itemNames.add(itemDTO.getItemName());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(AuthorizeGRNController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void focusToBuyingprice(ActionEvent event) {
        if (Validation.orderQty(txtQty.getText())) {
            txtBuyingPrice.requestFocus();
            lblNotify.setText("");
        } else {
            txtQty.requestFocus();
            txtQty.setFocusColor(Color.RED);
            lblNotify.setText("Qty format is incorrect !");
        }
    }

    @FXML
    void focusToItemName(ActionEvent event) {
        if (Validation.idValidation(txtSupplierOrderID.getText(), "vV")) {
            txtItemName.requestFocus();
            lblNotify.setText("");
        } else {
            txtSupplierOrderID.requestFocus();
            txtSupplierOrderID.setFocusColor(Color.RED);
            lblNotify.setText("Supplier Order ID format is incorrect !");
        }
    }

}
