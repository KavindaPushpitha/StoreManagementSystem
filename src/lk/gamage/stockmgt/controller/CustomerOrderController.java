package lk.gamage.stockmgt.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import lk.gamage.stockmgt.business.BOFactory;
import lk.gamage.stockmgt.business.custom.*;
import lk.gamage.stockmgt.business.custom.impl.CustomerBOImpl;
import lk.gamage.stockmgt.business.custom.impl.CustomerOrdersBOImpl;
import lk.gamage.stockmgt.business.custom.impl.ItemBOImpl;
import lk.gamage.stockmgt.business.custom.impl.StockBOImpl;
import lk.gamage.stockmgt.common.IDGenerator;
import lk.gamage.stockmgt.common.Validation;
import lk.gamage.stockmgt.model.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerOrderController implements Initializable {
    @FXML
    private AnchorPane customerOrderRoot;
    @FXML
    private JFXButton btnRemove;
    @FXML
    private JFXTextField txtSearchCustomer;

    @FXML
    private JFXButton btnAddCustomer;
    @FXML
    private JFXRadioButton rbtnCustomer;

    @FXML
    private JFXRadioButton rbtnNIC;
    @FXML
    private JFXTextField txtCustomerID;

    @FXML
    private JFXTextField txtCustomerName;
    @FXML
    private JFXTextField txtWarrantyID;

    @FXML
    private JFXTextField txtWarrantyPeriod;

    @FXML
    private JFXTextField txtOrderDate;

    @FXML
    private JFXTextField txtOrderID;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXTextField txtItemName;

    @FXML
    private JFXTextField txtModelNo;

    @FXML
    private JFXTextField txtUnitPrice;

    @FXML
    private JFXTextField txtOrderQty;

    @FXML
    private JFXTextField txtAvailableQty;
    @FXML
    private JFXTextField txtBrand;

    @FXML
    private JFXButton btnAddTable;

    @FXML
    private JFXButton btnAddWarranty;

    @FXML
    private TableView<OrderTableDTO> tblOrders;
    @FXML
    private Label lblNotify;
    @FXML
    private JFXTextField txtTotal;

    @FXML
    private JFXTextField txtBalance;

    @FXML
    private JFXTextField txtPayment;


    @FXML
    private JFXButton btnPrintBill;
    private double amount;
    @FXML
    private JFXTextField txtCash;
    private double total = 0;
    private ArrayList<CustomerDTO> allCustomer;
    private ArrayList<String> customerNames;
    private ArrayList<String> itemNames;
    private ArrayList<ItemDTO> allItem;
    @FXML
    private JFXSpinner spnSpinner;
    private CustomerBO customerBO;
    private StockBO stockBO;
    private ItemBO itemBO;
    private CustomerOrdersBO customerOrdersBO;
    private WarrantyBO warrantyBO;
    private int i;
    private boolean rButtons = true;
    private ArrayList<String> nic;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ToggleGroup t = new ToggleGroup();
        rbtnCustomer.setToggleGroup(t);
        rbtnNIC.setToggleGroup(t);
        warrantyBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.WARRANTY);
        customerBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);
        stockBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.STOCK);
        itemBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.ITEM);
        customerOrdersBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER_ORDERS);
        tblOrders.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblOrders.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("itemDescriptiom"));
        tblOrders.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("warrantyID"));
        tblOrders.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("warrantyPeriod"));
        tblOrders.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("from"));
        tblOrders.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("to"));
        tblOrders.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblOrders.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("orderQty"));
        tblOrders.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("total"));
        spnSpinner.setVisible(false);
        if (rButtons) {
            getAllCustomers();
            loadCustomerNames();
        } else if (!rButtons) {
            getAllCustomers();
            loadNIC();
        }
        loadOrderDate();
        setWarrantyID();
        getAllItems();
        loadItemNames();
        setOrderID();
    }

    private void setWarrantyID() {
        try {
            String warrantyID = warrantyBO.getWarrantyID();
            if (warrantyID != null) {
                i = Integer.parseInt(warrantyID.substring(3, 4));
                ++i;
                txtWarrantyID.setText("W00" + i);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(CustomerOrderController.class.getName()).log(Level.SEVERE, null, e);
        }
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
            loadNIC();
        }
    }

    @FXML
    void onAction(ActionEvent event) {
        loadToTable(event);
    }

    private void setOrderID() {
        try {
            String OrderID;
            OrderID = IDGenerator.getNewID("CustomerOrders", "orderID", "P");
            txtOrderID.setText(OrderID);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    void loadNIC() {
        TextFields.bindAutoCompletion(txtSearchCustomer, nic);
    }

    void getAllCustomers() {
        try {
            allCustomer = customerBO.getAllCustomer();
            nic = new ArrayList<>();
            customerNames = new ArrayList<>();
            for (CustomerDTO customerDTO : allCustomer) {
                customerNames.add(customerDTO.getCustomerName());
                nic.add(customerDTO.getNic());
            }

        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(CustomerOrderController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    void getAllItems() {
        try {
            itemNames = new ArrayList<>();
            allItem = itemBO.getAllItem();
            for (ItemDTO itemDTO : allItem) {
                itemNames.add(itemDTO.getItemName());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(CustomerOrderController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void spinnerVisible(KeyEvent event) {
        spnSpinner.setVisible(true);
    }

    void loadItemNames() {
        TextFields.bindAutoCompletion(txtItemName, itemNames);
    }

    void loadCustomerNames() {
        TextFields.bindAutoCompletion(txtSearchCustomer, customerNames);
    }

    @FXML
    void searchItem(ActionEvent event) {
        try {
            ItemDTO itemDTO = itemBO.searchItem(txtItemName.getText());
            if (itemDTO != null) {
                txtItemCode.setText(itemDTO.getItemCode());
                txtModelNo.setText(itemDTO.getModelNo());
                txtBrand.setText(itemDTO.getBrand());
                searchStock();
                txtUnitPrice.requestFocus();
                txtUnitPrice.selectAll();
                spnSpinner.setVisible(false);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "No such item !", ButtonType.OK);
                a.show();
            }
        } catch (Exception e) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Item has out of stock !", ButtonType.OK);
            a.show();
        }
    }

    void searchStock() {
        try {
            StockDTO stockDTO = stockBO.searchStock(txtItemCode.getText());
            if (stockDTO != null) {
                txtAvailableQty.setText(stockDTO.getQtyOnHand() + "");
                txtUnitPrice.setText(stockDTO.getSellingPrice() + "");
            } else {
                txtAvailableQty.setText("" + 0);
                txtUnitPrice.setText("" + 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(CustomerOrderController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void focusTioOrderQty(ActionEvent event) {
        if (Validation.cashValidation(txtUnitPrice.getText())) {
            txtOrderQty.requestFocus();
        } else {
            txtUnitPrice.requestFocus();
            txtUnitPrice.setFocusColor(Color.RED);
        }
    }

    @FXML
    void searchCustomer(ActionEvent event) {
        if (rButtons) {
            try {
                CustomerDTO customerDTO = customerBO.searchCustomer(txtSearchCustomer.getText());
                if (customerDTO != null) {
                    txtCustomerID.setText(customerDTO.getCustomerID());
                    txtCustomerName.setText(customerDTO.getCustomerName());
                    txtCustomerName.requestFocus();
                    loadCustomerNames();
                    spnSpinner.setVisible(false);
                }
            } catch (Exception e) {
                Alert a = new Alert(Alert.AlertType.ERROR, "No such customer", ButtonType.OK);
                a.show();
            }
        } else if (!rButtons) {
            try {
                CustomerDTO customerDTO = customerBO.searchByNic(txtSearchCustomer.getText());
                if (customerDTO != null) {
                    txtCustomerID.setText(customerDTO.getCustomerID());
                    txtCustomerName.setText(customerDTO.getCustomerName());
                    txtCustomerName.requestFocus();
                    loadNIC();
                    spnSpinner.setVisible(false);
                }
            } catch (Exception e) {
                Alert a = new Alert(Alert.AlertType.ERROR, "No such customer", ButtonType.OK);
                a.show();
            }
        }
    }

    @FXML
    void focusToItemName(ActionEvent event) {
        if (Validation.nameValidate(txtCustomerName.getText())) {
            txtItemName.requestFocus();
            lblNotify.setText("");
        } else {
            txtCustomerName.requestFocus();
            txtCustomerName.setFocusColor(Color.RED);
            lblNotify.setText("Customer name formate is incorrect!");
        }
    }

    @FXML
    void focusToWarrantyPeriod(ActionEvent event) {
        if (Validation.idValidation(txtWarrantyID.getText(), "wW")) {
            txtWarrantyPeriod.requestFocus();
        } else {
            txtWarrantyID.requestFocus();
            txtWarrantyID.setFocusColor(Color.RED);
            lblNotify.setText("Warranty id pattern is incorrect !");
        }
    }


    @FXML
    void loadAddCustomerForm(ActionEvent event) throws IOException {
        Parent p = FXMLLoader.load(this.getClass().getResource("/lk/gamage/stockmgt/view/AddCustomerForm.fxml"));
        Scene s = new Scene(p);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(s);
        stage.show();
        //customerOrderRoot.setVisible(false);

    }

    @FXML
    void removeFromTable(ActionEvent event) {
        double total = 0.0;
        if (tblOrders.getSelectionModel().getSelectedIndex() != -1) {
            total = tblOrders.getSelectionModel().getSelectedItem().getTotal();
            tblOrders.getItems().remove(tblOrders.getSelectionModel().getSelectedIndex());
            --i;
            txtWarrantyID.setText("W00" + i);
            this.total = this.total - total;
            txtTotal.setText(this.total + "");
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Please Select Row to Remove !", ButtonType.OK);
            a.show();
        }
    }

    @FXML
    void loadToTable(ActionEvent event) {
        if (Validation.nameValidate(txtCustomerName.getText())) {
            if (Validation.nameValidate(txtItemName.getText())) {
                if (Validation.cashValidation(txtUnitPrice.getText())) {
                    if (Validation.qtyOne(txtOrderQty.getText())) {
                        if (Validation.idValidation(txtWarrantyID.getText(), "Ww")) {
                            if (Validation.periodValidation(txtWarrantyPeriod.getText())) {
                                if (txtCustomerName.getText().isEmpty() || txtItemName.getText().isEmpty() || txtWarrantyPeriod.getText().isEmpty() || txtUnitPrice.getText().isEmpty() || txtOrderQty.getText().isEmpty()) {
                                    Alert a = new Alert(Alert.AlertType.ERROR, "You need to fill this form correct !", ButtonType.OK);
                                    Optional<ButtonType> buttonType = a.showAndWait();
                                    if (buttonType.get() == ButtonType.OK) {
                                        if (txtCustomerName.getText().isEmpty()) {
                                            txtCustomerName.requestFocus();
                                        } else if (txtItemName.getText().isEmpty()) {
                                            txtItemName.requestFocus();
                                        } else if (txtUnitPrice.getText().isEmpty()) {
                                            txtUnitPrice.requestFocus();
                                        } else if (txtOrderQty.getText().isEmpty()) {
                                            txtOrderQty.requestFocus();
                                        } else if (txtWarrantyID.getText().isEmpty()) {
                                            txtWarrantyID.requestFocus();
                                        } else if (txtAvailableQty.getText().isEmpty()) {
                                            txtAvailableQty.setText("" + 0);
                                        } else {
                                            txtWarrantyPeriod.requestFocus();
                                        }
                                    }
                                } else {
                                    try {
                                        if (Integer.parseInt(txtOrderQty.getText()) <= Integer.parseInt(txtAvailableQty.getText())) {
                                            amount = Double.parseDouble(txtUnitPrice.getText()) * Integer.parseInt(txtOrderQty.getText());
                                            Calendar cal = Calendar.getInstance();
                                            SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY/MM/dd");
                                            String today = dateFormat.format(cal.getTime());
                                            cal.add(Calendar.MONTH, Integer.parseInt(txtWarrantyPeriod.getText()));
                                            String after = dateFormat.format(cal.getTime());
                                            OrderTableDTO orderTableDTO = new OrderTableDTO(txtItemCode.getText(), txtItemName.getText(), txtWarrantyID.getText(), txtWarrantyPeriod.getText(), today, after, Double.parseDouble(txtUnitPrice.getText()), Integer.parseInt(txtOrderQty.getText()), amount);
                                            tblOrders.getItems().add(orderTableDTO);
                                            total = total + amount;
                                            txtTotal.setText(total + "");
                                            i++;
                                            txtWarrantyID.setText("W00" + i);
                                            clearTextFileds();
                                            txtItemName.requestFocus();
                                        } else {
                                            Alert a = new Alert(Alert.AlertType.ERROR, "Item has out of Stock !", ButtonType.OK);
                                            a.show();
                                        }
                                    } catch (NumberFormatException e) {
                                        Alert a = new Alert(Alert.AlertType.ERROR, "Fill the form correctly !", ButtonType.OK);
                                        a.show();
                                    }
                                }
                            } else {
                                txtWarrantyPeriod.requestFocus();
                                txtWarrantyPeriod.setFocusColor(Color.RED);
                                lblNotify.setText("Warranty period format is incorrect !");
                            }
                        } else {
                            txtWarrantyID.requestFocus();
                            txtWarrantyID.setFocusColor(Color.RED);
                            lblNotify.setText("Warranty ID format is incorrect !");
                        }
                    } else {
                        txtOrderQty.requestFocus();
                        txtOrderQty.setFocusColor(Color.RED);
                        lblNotify.setText("Order Qty format is incorrect !");
                    }
                } else {
                    txtUnitPrice.requestFocus();
                    txtUnitPrice.setFocusColor(Color.RED);
                    lblNotify.setText("Unit Price format is incorrect !");
                }
            } else {
                txtItemName.requestFocus();
                txtItemName.setFocusColor(Color.RED);
                lblNotify.setText("Item Name format is incorrect !");
            }
        } else {
            txtCustomerName.requestFocus();
            txtCustomerName.setFocusColor(Color.RED);
            lblNotify.setText("Customer Name format is incorrect !");
        }
    }

    @FXML
    void focusToWarrantyID(ActionEvent event) {
        if (Validation.qtyOne(txtOrderQty.getText())) {
            txtWarrantyID.requestFocus();
            lblNotify.setText("");
        } else {
            txtOrderQty.requestFocus();
            txtOrderQty.setFocusColor(Color.RED);
            lblNotify.setText("Order qty format is incorrect !");
        }
    }

    @FXML
    void saveOrder(ActionEvent event) {
        ArrayList<OrderTableDTO> orderTableDTOS = new ArrayList<>();
        if (txtCash.getText().isEmpty() || tblOrders.getItems().size() < 0) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Fill the form correct !", ButtonType.OK);
            a.show();
        } else {
            if (tblOrders.getItems().size() >= 1) {
                if (Double.parseDouble(txtCash.getText()) >= Double.parseDouble(txtTotal.getText())) {
                    String orderID = txtOrderID.getText();
                    String customerID = txtCustomerID.getText();
                    String orderDate = txtOrderDate.getText();
                    double customerPayments = Double.parseDouble(txtTotal.getText());
                    ArrayList<WarrantyDTO> warrantyDTOS = new ArrayList<>();
                    ArrayList<OrderDetailDTO> orderDetailDTOS = new ArrayList<>();
                    for (int i = 0; i < tblOrders.getItems().size(); i++) {
                        String warrantyID = (String) tblOrders.getColumns().get(2).getCellObservableValue(i).getValue();
                        String warrantyPeriod = (String) tblOrders.getColumns().get(3).getCellObservableValue(i).getValue();
                        String from = (String) tblOrders.getColumns().get(4).getCellObservableValue(i).getValue();
                        String to = (String) tblOrders.getColumns().get(5).getCellObservableValue(i).getValue();
                        warrantyDTOS.add(new WarrantyDTO(warrantyID, warrantyPeriod, from, to));
                        String itemCode = (String) tblOrders.getColumns().get(0).getCellObservableValue(i).getValue();
                        double unitPrice = (Double) tblOrders.getColumns().get(6).getCellObservableValue(i).getValue();
                        int qty = (Integer) tblOrders.getColumns().get(7).getCellObservableValue(i).getValue();
                        String itemName = (String) tblOrders.getColumns().get(1).getCellObservableValue(i).getValue();
                        double total = (Double) tblOrders.getColumns().get(8).getCellObservableValue(i).getValue();
                        orderDetailDTOS.add(new OrderDetailDTO(orderID, itemCode, warrantyID, unitPrice, qty));
                        orderTableDTOS.add(new OrderTableDTO(itemCode, itemName, warrantyID, warrantyPeriod, from, to, unitPrice, qty, total));
                    }
                    OrdersDTO ordersDTO = new OrdersDTO(orderID, customerID, orderDate, customerPayments, warrantyDTOS, orderDetailDTOS);
                    try {
                        boolean orderAdded = customerOrdersBO.addCustomerOrder(ordersDTO);
                        if (orderAdded) {
                            Alert a = new Alert(Alert.AlertType.INFORMATION, "Order Added Successfully !", ButtonType.OK);
                            Optional<ButtonType> buttonType = a.showAndWait();
                            Notifications n = Notifications.create().title("Customer Order").text("New Customer Order is Added !").hideAfter(Duration.seconds(3)).position(Pos.BOTTOM_RIGHT);
                            n.darkStyle();
                            lblNotify.setText("");
                            n.showInformation();
                            String customerOrderID = txtOrderID.getText();
                            String custID = txtCustomerID.getText();
                            String custName = txtCustomerName.getText();
                            double tot = Double.parseDouble(txtTotal.getText());
                            JRBeanCollectionDataSource tbl = new JRBeanCollectionDataSource(tblOrders.getItems());

                            InputStream stream = getClass().getResourceAsStream("/lk/gamage/stockmgt/reports/CustomerOrderList_1.jrxml");
                            JasperReport jasperReport = JasperCompileManager.compileReport(stream);


                            Map<String, Object> map = new HashMap<>();
                            map.put("customerID", custID);
                            map.put("customerName", custName);
                            map.put("orderID", customerOrderID);
                            map.put("totalAmount", tot);
                            map.put("OrderList", tbl);

                            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, new JREmptyDataSource());
                            JasperViewer.viewReport(jasperPrint, false);

                            if (buttonType.get() == ButtonType.OK) {
                                clearTextFileds();
                                txtCustomerName.clear();
                                txtCustomerID.clear();
                                txtSearchCustomer.clear();
                                txtWarrantyPeriod.clear();
                                txtTotal.clear();
                                txtCash.clear();
                                txtBalance.clear();
                                setOrderID();
                            }

                        } else {
                            Alert a = new Alert(Alert.AlertType.ERROR, "Not Added Successfully !", ButtonType.OK);
                            a.show();
                        }
                    } catch (Exception e) {
                        Alert a = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
                        a.show();
                        e.printStackTrace();
                        Logger.getLogger(CustomerOrderController.class.getName()).log(Level.SEVERE, null, e);
                    }
                } else {
                    Alert a = new Alert(Alert.AlertType.ERROR, "Payment is not enough !", ButtonType.OK);
                    Optional<ButtonType> buttonType = a.showAndWait();
                    if (buttonType.get() == ButtonType.OK) {
                        txtCash.requestFocus();
                        txtCash.setFocusColor(Color.RED);
                    }
                }
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Fill the form correctly !", ButtonType.OK);
                Optional<ButtonType> buttonType = a.showAndWait();
            }
        }
    }

    void loadOrderDate() {
        txtOrderDate.setText(LocalDate.now().toString());
    }

    @FXML
    void viewBalance(KeyEvent event) {
        if (Validation.cashValidation(txtCash.getText())) {
            double total = Double.parseDouble(txtTotal.getText());
            double v = Double.parseDouble(txtCash.getText());
            txtBalance.setText("" + (total - v));
        } else {
            txtCash.requestFocus();
            txtCash.setFocusColor(Color.RED);
            lblNotify.setText("Cash format is incorrect !");
        }
    }

    void clearTextFileds() {
        txtItemName.clear();
        txtItemCode.clear();
        txtOrderQty.clear();
        txtAvailableQty.clear();
        txtUnitPrice.clear();
        txtWarrantyPeriod.clear();
        txtBrand.clear();
        txtModelNo.clear();
    }
}
