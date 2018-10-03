package lk.gamage.stockmgt.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.ValueAxis;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import lk.gamage.stockmgt.business.BOFactory;
import lk.gamage.stockmgt.business.custom.ItemBO;
import lk.gamage.stockmgt.business.custom.StockBO;
import lk.gamage.stockmgt.business.custom.impl.ItemBOImpl;
import lk.gamage.stockmgt.business.custom.impl.StockBOImpl;
import lk.gamage.stockmgt.common.IDGenerator;
import lk.gamage.stockmgt.common.Validation;
import lk.gamage.stockmgt.model.ItemDTO;
import lk.gamage.stockmgt.model.StockBackDoorCloseDTO;
import lk.gamage.stockmgt.model.StockBackDoorTableDTO;
import lk.gamage.stockmgt.model.StockDTO;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StockBackDoorController implements Initializable {

    @FXML
    private AnchorPane stockRoot;

    @FXML
    private JFXButton btnDelete;
    @FXML
    private Label lblNotify;
    @FXML
    private JFXButton btnAddToTable;

    @FXML
    private TableView<StockBackDoorTableDTO> tblStock;

    @FXML
    private JFXTextField txtStockID;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXTextField txtItemName;

    @FXML
    private JFXTextField txtBrand;

    @FXML
    private JFXTextField txtModel;
    @FXML
    private JFXButton btnCloseBackDoor;
    @FXML
    private JFXTextField txtSellingPrice;
    private int i;
    @FXML
    private JFXTextField txtQtyOnHand;

    @FXML
    private JFXButton btnSave;
    private StockBO stockBO;
    @FXML
    private JFXButton btnAddNewItem;
    private ItemBO itemBO;
    private ArrayList<String> itemNames;

    private static boolean isClicked = true;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stockBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.STOCK);
        itemBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.ITEM);
        tblStock.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("stockID"));
        tblStock.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblStock.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("ModelNo"));
        tblStock.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("itemName"));
        tblStock.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("brand"));
        tblStock.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
        tblStock.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        getAllItems();
        loadItemNames();
        setStockID();
        txtStockID.requestFocus();

    }


    void getAllItems() {
        try {
            ArrayList<ItemDTO> allItem = itemBO.getAllItem();
            itemNames = new ArrayList<>();
            for (ItemDTO itemDTO : allItem) {
                itemNames.add(itemDTO.getItemName());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(StockBackDoorController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void focusToItemName(ActionEvent event) {
        if (Validation.idValidation(txtStockID.getText(), "Tt")) {
            txtItemName.requestFocus();
            lblNotify.setText("");
        } else {
            txtStockID.requestFocus();
            txtStockID.setFocusColor(Color.RED);
            lblNotify.setText("Stock id format is incorrect !");
        }
    }

    @FXML
    void onAction(ActionEvent event) {
        try {
            ItemDTO itemDTO = itemBO.searchItem(txtItemName.getText());
            if (itemDTO != null) {
                txtItemCode.setText(itemDTO.getItemCode());
                txtBrand.setText(itemDTO.getBrand());
                txtModel.setText(itemDTO.getModelNo());
                txtSellingPrice.requestFocus();
            }
        } catch (Exception e) {
            Alert a = new Alert(Alert.AlertType.ERROR, "No Such Item. You need to add items to the system first !", ButtonType.OK);
            a.show();

        }
    }

    @FXML
    void focusToOty(ActionEvent event) {

        if (Validation.cashValidation(txtSellingPrice.getText())) {
            txtQtyOnHand.requestFocus();
            lblNotify.setText("");
        } else {
            txtSellingPrice.requestFocus();
            txtSellingPrice.setFocusColor(Color.RED);
            lblNotify.setText("Price format is incorrect !");
        }
    }

    private void loadItemNames() {
        TextFields.bindAutoCompletion(txtItemName, itemNames);
    }


    @FXML
    void deleteRow(ActionEvent event) {
        if (tblStock.getSelectionModel().getSelectedIndex() != -1) {
            tblStock.getItems().remove(tblStock.getSelectionModel().getSelectedIndex());
            --i;
            txtStockID.setText("T00" + i);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Please Select the row first !", ButtonType.OK);
            a.show();
        }
    }

    @FXML
    void loadItemMgt(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(this.getClass().getResource("/lk/gamage/stockmgt/view/ManageItems.fxml"));
        stockRoot.getChildren().setAll(pane);
        Scene scene = stockRoot.getScene();
        //TranslateTransition transition=new TranslateTransition(Duration.millis(1000),scene.getRoot());
        //transition.setFromX(-scene.getWidth());
        //transition.setToX(0);
        //transition.play();
        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000), stockRoot);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    @FXML
    void loadToTable(ActionEvent event) {
        ArrayList<StockDTO> rows = new ArrayList<>();
        if (Validation.idValidation(txtStockID.getText(), "Tt")) {
            if (Validation.nameValidate(txtItemName.getText())) {
                if (Validation.cashValidation(txtSellingPrice.getText())) {
                    if (Validation.orderQty(txtQtyOnHand.getText())) {
                        if (txtStockID.getText().isEmpty() || txtItemName.getText().isEmpty() || txtSellingPrice.getText().isEmpty() || txtQtyOnHand.getText().isEmpty()) {
                            Alert a = new Alert(Alert.AlertType.INFORMATION, "Fill the form correct !", ButtonType.OK);
                            Optional<ButtonType> buttonType = a.showAndWait();
                            if (buttonType.get() == ButtonType.OK) {
                                if (txtStockID.getText().isEmpty()) {
                                    txtStockID.requestFocus();
                                } else if (txtItemName.getText().isEmpty()) {
                                    txtItemName.requestFocus();
                                } else if (txtSellingPrice.getText().isEmpty()) {
                                    txtSellingPrice.requestFocus();
                                } else {
                                    txtQtyOnHand.requestFocus();
                                }
                            }
                        } else {
                            if (Validation.orderQty(txtQtyOnHand.getText())) {
                                StockBackDoorTableDTO a = new StockBackDoorTableDTO(txtStockID.getText(), txtItemCode.getText(), txtModel.getText(), txtItemName.getText(), txtBrand.getText(), Double.parseDouble(txtSellingPrice.getText()), Integer.parseInt(txtQtyOnHand.getText()));
                                tblStock.getItems().add(a);
                                txtItemName.requestFocus();
                                clearTextFileds();
                                ++i;
                                txtStockID.setText("T00" + i);
                                lblNotify.setText("");
                            } else {
                                txtQtyOnHand.requestFocus();
                                lblNotify.setText("QtyOnHand format is incorrect !");
                            }
                        }
                    } else {
                        txtQtyOnHand.requestFocus();
                        txtQtyOnHand.setFocusColor(Color.RED);
                        lblNotify.setText("Qty On Hand format is incorrect !");
                    }
                } else {
                    txtSellingPrice.requestFocus();
                    txtSellingPrice.setFocusColor(Color.RED);
                    lblNotify.setText("Selling Price format is incorrect !");
                }
            } else {
                txtItemName.requestFocus();
                txtItemName.setFocusColor(Color.RED);
                lblNotify.setText("Item Name format is incorrect !");
            }
        } else {
            txtStockID.requestFocus();
            txtStockID.setFocusColor(Color.RED);
            lblNotify.setText("Stock ID format is incorrect !");
        }
    }

    void clearTextFileds() {
        txtItemCode.clear();
        txtItemName.clear();
        txtModel.clear();
        txtBrand.clear();
        txtQtyOnHand.clear();
        txtSellingPrice.clear();
        txtStockID.clear();
    }

    @FXML
    void saveItems(ActionEvent event) {
        if (tblStock.getItems().size() >= 1) {
            ArrayList<StockDTO> stockDTOS = new ArrayList<>();
            for (int i = 0; i < tblStock.getItems().size(); i++) {
                String stockID = (String) tblStock.getColumns().get(0).getCellObservableValue(i).getValue();
                String itemCode = (String) tblStock.getColumns().get(1).getCellObservableValue(i).getValue();
                int qtyOnHand = (Integer) tblStock.getColumns().get(6).getCellObservableValue(i).getValue();
                double sellingPrice = (Double) tblStock.getColumns().get(5).getCellObservableValue(i).getValue();
                stockDTOS.add(new StockDTO(stockID, itemCode, qtyOnHand, sellingPrice));
            }

            try {
                boolean b = stockBO.addStock(stockDTOS);
                if (b) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "Successfully Added!", ButtonType.OK);
                    a.show();
                    Notifications notificationsn = Notifications.create().title("Stock BackDoor").text("New Stock is added to the system !").hideAfter(Duration.seconds(3)).position(Pos.BOTTOM_RIGHT);
                    notificationsn.darkStyle();
                    notificationsn.showInformation();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Logger.getLogger(StockBackDoorController.class.getName()).log(Level.SEVERE, null, e);
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "No items to save!", ButtonType.OK);
            a.show();
        }
    }

    @FXML
    void disableBackDoor(ActionEvent event) {
        StockBackDoorCloseDTO.setClicked(true);
        try {
            Parent parent = FXMLLoader.load(this.getClass().getResource("/lk/gamage/stockmgt/view/DashBoard.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) this.stockRoot.getScene().getWindow();

            stage.setScene(scene);
            stage.centerOnScreen();
            Notifications n = Notifications.create().title("Stock Back Door").text("You have Close the Stock Back Door Permenantly !").hideAfter(Duration.seconds(3)).position(Pos.BOTTOM_RIGHT);
            n.darkStyle();
            n.showConfirm();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void disable() {
        txtItemCode.setDisable(true);
        txtItemName.setDisable(true);
        txtModel.setDisable(true);
        txtBrand.setDisable(true);
        txtQtyOnHand.setDisable(true);
        txtSellingPrice.setDisable(true);
        tblStock.setEditable(false);
        btnAddNewItem.setDisable(true);
        btnAddToTable.setDisable(true);
        btnDelete.setDisable(true);
        btnSave.setDisable(true);
        btnCloseBackDoor.setDisable(true);
        //DashBoardController dashBoardController=new DashBoardController();
        //dashBoardController.btnStockBackDoor.setDisable(true);
    }

    void setStockID() {
        try {
            String allStockID = stockBO.getAllStockID();
            if (allStockID != null) {
                i = Integer.parseInt(allStockID.substring(3, 4));
                ++i;
                txtStockID.setText("T00" + i);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(StockBackDoorController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    public static boolean isIsClicked() {
        return isClicked;
    }

    public static void setIsClicked(boolean isClicked) {
        StockBackDoorController.isClicked = isClicked;
    }

}
