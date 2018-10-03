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
import lk.gamage.stockmgt.business.custom.StockBO;
import lk.gamage.stockmgt.business.custom.impl.ItemBOImpl;
import lk.gamage.stockmgt.business.custom.impl.StockBOImpl;
import lk.gamage.stockmgt.common.Validation;
import lk.gamage.stockmgt.model.ItemDTO;
import lk.gamage.stockmgt.model.StockDTO;
import lk.gamage.stockmgt.model.StockTableDTO;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StockMgtController implements Initializable {
    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXTextField txtSearchItem;

    @FXML
    private TableView<StockTableDTO> tblStock;

    @FXML
    private JFXTextField txtStockID;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXTextField txtItemName;

    @FXML
    private JFXTextField txtBrand;

    @FXML
    private JFXTextField txtModelNo;

    @FXML
    private JFXTextField txtSellingPrice;
    @FXML
    private JFXSpinner spnSpinner;
    @FXML
    private Label lblNotify;
    @FXML
    private JFXTextField txtQtyOnHand;

    @FXML
    private JFXButton btnDelete;
    private ItemBO itemBO;
    private StockBO stockBO;
    private ArrayList<String> itemNames;
    private ArrayList<ItemDTO> allItem;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stockBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.STOCK);
        itemBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.ITEM);
        tblStock.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("stockID"));
        tblStock.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblStock.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("modelNo"));
        tblStock.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("itemName"));
        tblStock.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("brand"));
        tblStock.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
        tblStock.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        getAllStocks();
        getAllItems();
        loadNames();
        txtSearchItem.requestFocus();
        spnSpinner.setVisible(false);
    }

    public void getAllItems() {
        try {
            itemNames = new ArrayList<>();
            allItem = itemBO.getAllItem();
            for (ItemDTO itemDTO : allItem) {
                itemNames.add(itemDTO.getItemName());
            }

        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(StockMgtController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void loadNames() {
        TextFields.bindAutoCompletion(txtSearchItem, itemNames);
    }

    @FXML
    void SearchItems(ActionEvent event) {
        try {
            ItemDTO itemDTO = itemBO.searchItem(txtSearchItem.getText());
            txtItemCode.setText(itemDTO.getItemCode());
            txtBrand.setText(itemDTO.getBrand());
            txtModelNo.setText(itemDTO.getModelNo());
            txtItemName.setText(itemDTO.getItemName());
            searchStock();
            txtSellingPrice.requestFocus();
            spnSpinner.setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(StockMgtController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void searchStock() {
        try {
            StockDTO stockDTO = stockBO.searchStock(txtItemCode.getText());
            txtStockID.setText(stockDTO.getStockID());
            txtQtyOnHand.setText(stockDTO.getQtyOnHand() + "");
            txtSellingPrice.setText(stockDTO.getSellingPrice() + "");
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(StockMgtController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void keyRelease(KeyEvent event) {
        spnSpinner.setVisible(true);
    }

    @FXML
    void deleteStock(ActionEvent event) {
        if (tblStock.getSelectionModel().getSelectedIndex() != -1) {
            try {
                boolean deleteStock = stockBO.deleteStock(txtStockID.getText());
                if (deleteStock) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "Successfully deleted !", ButtonType.OK);
                    a.show();
                    Notifications notificationsn = Notifications.create().title("Stock").text("Stock Deleted !").hideAfter(Duration.seconds(3)).position(Pos.BOTTOM_RIGHT);
                    notificationsn.darkStyle();
                    notificationsn.showInformation();
                    getAllStocks();
                    clearTextFileds();
                } else {
                    Alert a = new Alert(Alert.AlertType.ERROR, "Not deleted !", ButtonType.OK);
                    a.show();
                }

            } catch (Exception e) {
                e.printStackTrace();
                Logger.getLogger(StockMgtController.class.getName()).log(Level.SEVERE, null, e);
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Please select a row !", ButtonType.OK);
            a.show();
        }
    }

    @FXML
    void mouseClicked(MouseEvent event) {
        if (tblStock.getSelectionModel().getSelectedIndex() != -1) {
            StockTableDTO selectedItem = tblStock.getSelectionModel().getSelectedItem();
            txtItemCode.setText(selectedItem.getItemCode());
            txtItemName.setText(selectedItem.getItemName());
            txtModelNo.setText(selectedItem.getModelNo());
            txtBrand.setText(selectedItem.getBrand());
            txtSellingPrice.setText("" + selectedItem.getSellingPrice());
            txtStockID.setText(selectedItem.getStockID());
            txtQtyOnHand.setText(selectedItem.getQtyOnHand() + "");
        }

    }

    @FXML
    void saveUpdates(ActionEvent event) {
        if (!txtStockID.getText().isEmpty()) {
            if (Validation.cashValidation(txtSellingPrice.getText())) {
                StockDTO stockDTO = new StockDTO(txtStockID.getText(), txtItemCode.getText(), Integer.parseInt(txtQtyOnHand.getText()), Double.parseDouble(txtSellingPrice.getText()));

                try {
                    boolean updateStock = stockBO.updateSellingPrice(stockDTO);
                    if (updateStock) {
                        Alert a = new Alert(Alert.AlertType.INFORMATION, "Selling Price is updated !");
                        a.show();
                        Notifications notificationsn = Notifications.create().title("Stock").text("Selling Price is Updated !").hideAfter(Duration.seconds(3)).position(Pos.BOTTOM_RIGHT);
                        notificationsn.darkStyle();
                        notificationsn.showInformation();
                        getAllStocks();
                        clearTextFileds();
                    } else {
                        Alert a = new Alert(Alert.AlertType.ERROR, "Selling Price is not updated !");
                        a.show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Logger.getLogger(StockMgtController.class.getName()).log(Level.SEVERE, null, e);
                }
            } else {
                txtSellingPrice.requestFocus();
                txtSellingPrice.setFocusColor(Color.RED);
                lblNotify.setText("Selling Price format is incorrect !");
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Nothing to update !");
            a.show();
        }
    }

    public void getAllStocks() {
        try {
            ArrayList<StockTableDTO> allStock = stockBO.getAllStock();
            tblStock.setItems(FXCollections.observableArrayList(allStock));
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(StockMgtController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    void clearTextFileds() {
        txtSellingPrice.clear();
        txtStockID.clear();
        txtItemName.clear();
        txtItemCode.clear();
        txtBrand.clear();
        txtModelNo.clear();
        txtQtyOnHand.clear();
    }

}
