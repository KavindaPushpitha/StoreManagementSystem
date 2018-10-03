package lk.gamage.stockmgt.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import lk.gamage.stockmgt.business.BOFactory;
import lk.gamage.stockmgt.business.custom.ItemBO;
import lk.gamage.stockmgt.business.custom.StockBO;
import lk.gamage.stockmgt.business.custom.SupplierBO;
import lk.gamage.stockmgt.business.custom.SupplierOrderBO;
import lk.gamage.stockmgt.business.custom.impl.ItemBOImpl;
import lk.gamage.stockmgt.business.custom.impl.StockBOImpl;
import lk.gamage.stockmgt.business.custom.impl.SupplierBOImpl;
import lk.gamage.stockmgt.business.custom.impl.SupplierOrderBOImpl;
import lk.gamage.stockmgt.common.IDGenerator;
import lk.gamage.stockmgt.common.Validation;
import lk.gamage.stockmgt.model.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;

import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SupplierOrderController implements Initializable {
    @FXML
    private JFXTextField txtSearchSupplier;

    @FXML
    private JFXButton btnAddOrder;

    @FXML
    private JFXTextField txtSupplierID;

    @FXML
    private JFXTextField txtSupplierName;

    @FXML
    private JFXTextField txtCompanyName;

    @FXML
    private JFXTextField txtSupplierOrderID;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXTextField txtSupplierOrderDate;

    @FXML
    private JFXTextField txtItemName;

    @FXML
    private JFXTextField txtBrand;

    @FXML
    private JFXTextField txtModelName;

    @FXML
    private JFXTextField txtAvalableQty;

    @FXML
    private JFXSpinner spnSpinner;
    @FXML
    private JFXTextField txtOrderQty;

    @FXML
    private TableView<SupplierOrderTableDTO> tblSupplierOrders;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnPrintBill;


    @FXML
    private JFXButton btnCancel;
    private ArrayList<SupplierDTO> allSupplier;
    private ArrayList<ItemDTO> allItem;
    private ArrayList<String> supplierName;
    private ArrayList<String> itemName;
    private SupplierBO supplierBO;
    private SupplierOrderBO supplierOrderBO;
    private StockBO stockBO;
    private ItemBO itemBO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        supplierBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.SUPPLIER);
        supplierOrderBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.SUPPLIER_ORDER);
        stockBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.STOCK);
        itemBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.ITEM);
        loadOrderDate();
        try {
            tblSupplierOrders.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
            tblSupplierOrders.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("itemName"));
            tblSupplierOrders.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("ModelNo"));
            tblSupplierOrders.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("Brand"));
            tblSupplierOrders.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("orderQty"));
            getAllItems();
            loadItemNames();
            getAllSuppliers();
            loadSupplierNames();
            setSupplierOrderID();
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(SupplierOrderController.class.getName()).log(Level.SEVERE, null, e);
        }


    }

    @FXML
    void keyReleased(KeyEvent event) {
        spnSpinner.setVisible(true);
    }

    private void setSupplierOrderID() {
        try {
            String supplierOrderID;
            supplierOrderID = IDGenerator.getNewID("supplierOrders", "supplierOrderID", "V");
            txtSupplierOrderID.setText(supplierOrderID);
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(SupplierOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getAllItems() {
        try {
            allItem = itemBO.getAllItem();
            itemName = new ArrayList<>();
            for (ItemDTO itemDTO : allItem) {
                itemName.add(itemDTO.getItemName());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(SupplierOrderController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void getAllSuppliers() throws Exception {
        allSupplier = supplierBO.getAllSupplier();
        supplierName = new ArrayList<>();
        for (SupplierDTO supplierDTO : allSupplier) {
            supplierName.add(supplierDTO.getSupplierName());
        }
    }

    private void loadSupplierNames() {
        TextFields.bindAutoCompletion(txtSearchSupplier, supplierName);
    }

    private void loadItemNames() {
        TextFields.bindAutoCompletion(txtItemName, itemName);
    }

    private void loadOrderDate() {
        txtSupplierOrderDate.setText(LocalDate.now().toString());
    }


    @FXML
    void addToTable(ActionEvent event) {
        ArrayList<SupplierOrderTableDTO> rows = new ArrayList<>();
        if (Validation.nameValidate(txtItemName.getText())) {
            if (Validation.orderQty(txtOrderQty.getText())) {
                if (txtItemName.getText().isEmpty() || txtOrderQty.getText().isEmpty() || txtSupplierName.getText().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "You need to fill the form correct !", ButtonType.OK);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get() == ButtonType.OK) {
                        if (txtOrderQty.getText().isEmpty()) {
                            txtOrderQty.requestFocus();
                        } else if (txtItemName.getText().isEmpty()) {
                            txtItemName.requestFocus();
                        } else {
                            txtSearchSupplier.requestFocus();
                        }
                    }
                } else {
                    SupplierOrderTableDTO supplierOrderTableDTO = new SupplierOrderTableDTO(txtItemCode.getText(), txtItemName.getText(), txtModelName.getText(), txtBrand.getText(), Integer.parseInt(txtOrderQty.getText()));
                    tblSupplierOrders.getItems().add(supplierOrderTableDTO);
                    clearTextFields();
                    txtItemName.requestFocus();
                }
            } else {
                txtOrderQty.requestFocus();
                txtOrderQty.setFocusColor(Color.RED);
            }
        } else {
            txtItemName.requestFocus();
            txtItemName.setFocusColor(Color.RED);
        }

    }

    void clearTextFields() {
        txtItemName.clear();
        txtItemCode.clear();
        txtModelName.clear();
        txtBrand.clear();
        txtOrderQty.clear();
        txtAvalableQty.clear();
    }

    @FXML
    void deleteFromTable(ActionEvent event) {
        if (tblSupplierOrders.getSelectionModel().getSelectedIndex() != -1) {
            tblSupplierOrders.getItems().remove(tblSupplierOrders.getSelectionModel().getSelectedItem());

        } else {
            Alert notDeleted = new Alert(Alert.AlertType.ERROR, "Please Select Row !", ButtonType.OK);
            notDeleted.show();

        }
    }

    @FXML
    void saveOrder(ActionEvent event) {
        if (tblSupplierOrders.getItems().size() >= 1) {
            ArrayList<SupplierOrderDetailDTO> supplierOrderDetailDTOS = new ArrayList<>();
            String orderID = txtSupplierOrderID.getText();
            String supplierID = txtSupplierID.getText();
            String date = txtSupplierOrderDate.getText();
            for (int i = 0; i < tblSupplierOrders.getItems().size(); i++) {
                String itemCode = (String) tblSupplierOrders.getColumns().get(0).getCellObservableValue(i).getValue();
                int orderQty = (Integer) tblSupplierOrders.getColumns().get(4).getCellObservableValue(i).getValue();
                System.out.println(itemCode);
                System.out.println(orderQty);
                SupplierOrderDetailDTO supplierOrderDetailDTO = new SupplierOrderDetailDTO(orderID, itemCode, orderQty);
                supplierOrderDetailDTOS.add(supplierOrderDetailDTO);
            }
            SupplierOrderDTO supplierDTO = new SupplierOrderDTO(orderID, supplierID, date, supplierOrderDetailDTOS);
            try {
                boolean b = supplierOrderBO.addSupplierOrder(supplierDTO);
                if (b) {
                    Alert added = new Alert(Alert.AlertType.INFORMATION, "Supplier Order Added !", ButtonType.OK);
                    added.show();
                    String supID = txtSupplierID.getText();
                    String supOrderID = txtSupplierOrderID.getText();
                    String supName = txtSupplierName.getText();
                    String comName = txtCompanyName.getText();
                    ObservableList<SupplierOrderTableDTO> supplierOrderTableDTOS = tblSupplierOrders.getItems();
                    JRBeanCollectionDataSource tbl = new JRBeanCollectionDataSource(supplierOrderTableDTOS);
                    InputStream stream = getClass().getResourceAsStream("/lk/gamage/stockmgt/reports/SupplierOrder.jrxml");
                    JasperReport jasperReport = JasperCompileManager.compileReport(stream);


                    Map<String, Object> map = new HashMap<>();
                    map.put("supplierID", supID);
                    map.put("supplierName", supName);
                    map.put("supplierOrderID", supOrderID);
                    map.put("companyName", comName);
                    map.put("supplierOrderData", tbl);

                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, new JREmptyDataSource());
                    JasperViewer.viewReport(jasperPrint, false);
                    Notifications notificationsn = Notifications.create().title("Supplier Order").text("New Supplier Order is Add to the system !").hideAfter(Duration.seconds(3)).position(Pos.BOTTOM_RIGHT);
                    notificationsn.darkStyle();
                    notificationsn.showInformation();

                } else {
                    Alert added = new Alert(Alert.AlertType.ERROR, "Supplier Order Not Added !", ButtonType.OK);
                    added.show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Logger.getLogger(SupplierOrderController.class.getName()).log(Level.SEVERE, null, e);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "No order to save !", ButtonType.OK);
            alert.show();
        }
    }

    @FXML
    void searchSupplier(ActionEvent event) {
        spnSpinner.setVisible(false);
        try {
            SupplierDTO supplierDTO = supplierBO.searchSupplier(txtSearchSupplier.getText());
            if (supplierDTO != null) {
                txtSupplierName.setText(supplierDTO.getSupplierName());
                txtSupplierID.setText(supplierDTO.getSupplierID());
                txtCompanyName.setText(supplierDTO.getCompanyName());
                txtItemName.requestFocus();
            }

        } catch (Exception e) {
            Alert noItem = new Alert(Alert.AlertType.ERROR, "No such Supplier !", ButtonType.OK);
            noItem.show();
        }
    }

    @FXML
    void searchItems(ActionEvent event) {
        try {
            ItemDTO itemDTO = itemBO.searchItem(txtItemName.getText());
            if (itemDTO != null) {
                txtItemCode.setText(itemDTO.getItemCode());
                txtModelName.setText(itemDTO.getModelNo());
                txtBrand.setText(itemDTO.getBrand());
            }

            StockDTO stockDTO = stockBO.searchStock(txtItemCode.getText());
            if (stockDTO != null) {
                txtAvalableQty.setText("" + stockDTO.getQtyOnHand());
            } else {
                txtAvalableQty.setText("" + 0.0);
            }
            if (Validation.addressValidate(txtItemName.getText())) {
                txtOrderQty.requestFocus();
            } else {
                txtItemName.requestFocus();
                txtItemName.setFocusColor(Color.RED);
            }
        } catch (Exception e) {
            Alert noItem = new Alert(Alert.AlertType.ERROR, "No such Item !", ButtonType.OK);
            noItem.show();
        }
    }


}
