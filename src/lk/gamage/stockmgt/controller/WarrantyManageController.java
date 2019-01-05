package lk.gamage.stockmgt.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import com.sun.media.sound.AlawCodec;
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
import lk.gamage.stockmgt.business.custom.WarrantyBO;
import lk.gamage.stockmgt.business.custom.impl.CustomerBOImpl;
import lk.gamage.stockmgt.business.custom.impl.CustomerOrdersBOImpl;
import lk.gamage.stockmgt.business.custom.impl.WarrantyBOImpl;
import lk.gamage.stockmgt.common.Validation;
import lk.gamage.stockmgt.entity.Warranty;
import lk.gamage.stockmgt.model.CustomerDTO;
import lk.gamage.stockmgt.model.ManageWarrantyTableDTO;
import lk.gamage.stockmgt.model.WarrantyDTO;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WarrantyManageController implements Initializable {
    @FXML
    private JFXTextField txtSearchCustomer;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnSave;

    @FXML
    private TableView<ManageWarrantyTableDTO> tblWarrantys;

    @FXML
    private JFXTextField txtCustomerID;

    @FXML
    private JFXTextField txtCustomerName;

    @FXML
    private JFXTextField txtSearchOrderDate;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtNic;

    @FXML
    private JFXTextField txtWarrantyID;

    @FXML
    private JFXTextField txtOrderID;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXTextField txtItemName;
    @FXML
    private Label lblNotify;

    @FXML
    private JFXTextField txtBrand;

    @FXML
    private JFXTextField txtModelNo;

    @FXML
    private JFXTextField txtWarrantyFrom;
    @FXML
    private JFXRadioButton rbtnName;

    @FXML
    private JFXRadioButton rbtnNIC;
    @FXML
    private JFXTextField txtWarrantyTo;
    private ArrayList<String> customerOrderDates;
    private CustomerBO customerBO;
    private WarrantyBO warrantyBO;
    private CustomerOrdersBO customerOrdersBO;
    private ArrayList<CustomerDTO> allCustomer;
    private ArrayList<String> customerNames;
    private ArrayList<String> nic;
    @FXML
    private JFXTextField txtPeriod;
    private boolean rButton = true;
    @FXML
    private JFXSpinner spnCustomer;

    @FXML
    private JFXSpinner spnOrder;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ToggleGroup t = new ToggleGroup();
        rbtnNIC.setToggleGroup(t);
        ;
        rbtnName.setToggleGroup(t);
        customerOrdersBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER_ORDERS);
        customerBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);
        warrantyBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.WARRANTY);
        tblWarrantys.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("orderID"));
        tblWarrantys.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblWarrantys.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("warrantyID"));
        tblWarrantys.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("itemName"));
        tblWarrantys.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("brand"));
        tblWarrantys.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("modelNo"));
        tblWarrantys.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("warrantyPeriod"));
        tblWarrantys.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("warrantyFrom"));
        tblWarrantys.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("WarrantyTo"));
        tblWarrantys.getColumns().get(9).setCellValueFactory(new PropertyValueFactory<>("orderQty"));
        if (rButton) {
            loadCustomerNames();
            loadCustomerToText();
        } else if (!rButton) {
            loadCustomerNames();
            loadNICToText();
        }
        loadOrderDates();
        loadDateToText();
    }

    @FXML
    void focusToOrder(ActionEvent event) {
        if (rButton) {
            if (Validation.nameValidate(txtSearchCustomer.getText())) {
                try {
                    CustomerDTO customer = customerBO.searchCustomer(txtSearchCustomer.getText());
                    if (customer != null) {
                        txtCustomerName.setText(customer.getCustomerName());
                        txtAddress.setText(customer.getAddress());
                        txtContact.setText(customer.getContact());
                        txtCustomerID.setText(customer.getCustomerID());
                        txtNic.setText(customer.getNic());
                        txtSearchOrderDate.requestFocus();
                        lblNotify.setText("");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Logger.getLogger(WarrantyManageController.class.getName()).log(Level.SEVERE, null, e);
                }
            } else {
                txtSearchCustomer.requestFocus();
                txtSearchCustomer.setFocusColor(Color.RED);
                txtSearchCustomer.selectAll();
                lblNotify.setText("Customer Name is Incorrect !");

            }
        } else if (!rButton) {
            if (Validation.nicValidate(txtSearchCustomer.getText())) {
                try {
                    CustomerDTO customer = customerBO.searchByNic(txtSearchCustomer.getText());
                    if (customer != null) {
                        txtCustomerName.setText(customer.getCustomerName());
                        txtAddress.setText(customer.getAddress());
                        txtContact.setText(customer.getContact());
                        txtCustomerID.setText(customer.getCustomerID());
                        txtNic.setText(customer.getNic());
                        txtSearchOrderDate.requestFocus();
                        lblNotify.setText("");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Logger.getLogger(WarrantyManageController.class.getName()).log(Level.SEVERE, null, e);
                }
            } else {
                txtSearchCustomer.requestFocus();
                txtSearchCustomer.setFocusColor(Color.RED);
                txtSearchCustomer.selectAll();
                lblNotify.setText("Customer NIC is Incorrect !");

            }
        }
    }

    @FXML
    void onMouseClick(MouseEvent event) {
        if (tblWarrantys.getSelectionModel().getSelectedIndex() != -1) {
            txtItemCode.setText(tblWarrantys.getSelectionModel().getSelectedItem().getItemCode());
            txtItemName.setText(tblWarrantys.getSelectionModel().getSelectedItem().getItemName());
            txtBrand.setText(tblWarrantys.getSelectionModel().getSelectedItem().getBrand());
            txtModelNo.setText(tblWarrantys.getSelectionModel().getSelectedItem().getModelNo());
            txtWarrantyID.setText(tblWarrantys.getSelectionModel().getSelectedItem().getWarrantyID());
            txtWarrantyFrom.setText(tblWarrantys.getSelectionModel().getSelectedItem().getWarrantyFrom());
            txtWarrantyTo.setText(tblWarrantys.getSelectionModel().getSelectedItem().getWarrantyTo());
            txtPeriod.setText(tblWarrantys.getSelectionModel().getSelectedItem().getWarrantyPeriod());
        }
    }

    @FXML
    void customerKeyReleased(KeyEvent event) {
        spnOrder.setVisible(false);
        spnCustomer.setVisible(true);
    }

    @FXML
    void orderKeyReleased(KeyEvent event) {
        spnCustomer.setVisible(false);
        spnOrder.setVisible(true);
    }

    @FXML
    void saveWarranty(ActionEvent event) {
        if (txtPeriod.getText().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Fill the Form Correctly !", ButtonType.OK);
            a.show();
        } else {
            if (Validation.orderQty(txtPeriod.getText())) {
//                txtWarrantyTo.setText(LocalDate.parse(txtWarrantyFrom.getText()).plusMonths(Long.parseLong(txtPeriod.getText())).toString());
                WarrantyDTO warrantyDTO = new WarrantyDTO(txtWarrantyID.getText(), txtPeriod.getText(), txtWarrantyFrom.getText(), txtWarrantyTo.getText());
                lblNotify.setText("");
                try {
                    boolean updateWarranty = warrantyBO.updateWarranty(warrantyDTO);
                    if (updateWarranty) {
                        searchWarranty(event);
                        clearTextFileds();
                        Alert a = new Alert(Alert.AlertType.INFORMATION, "Successfully updated !", ButtonType.OK);
                        a.show();
                        Notifications notificationsn = Notifications.create().title("Warranty").text("Updated existing Warranty !").hideAfter(Duration.seconds(3)).position(Pos.BOTTOM_RIGHT);
                        notificationsn.darkStyle();
                        notificationsn.showInformation();
                    } else {
                        Alert a = new Alert(Alert.AlertType.ERROR, "Not Updated !", ButtonType.OK);
                        a.show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Logger.getLogger(WarrantyManageController.class.getName()).log(Level.SEVERE, null, e);
                }
            } else {
                txtPeriod.requestFocus();
                txtPeriod.selectAll();
                txtPeriod.setFocusColor(Color.RED);
                lblNotify.setText("Period format is Incorrect !");
            }
        }
    }

    @FXML
    void searchWarranty(ActionEvent event) {
        if (Validation.dateValidation(txtSearchOrderDate.getText())) {
            try {
                ArrayList<ManageWarrantyTableDTO> allWarrantys = warrantyBO.getAllWarrantys(txtCustomerID.getText(), txtSearchOrderDate.getText());
                tblWarrantys.setItems(FXCollections.observableArrayList(allWarrantys));
                lblNotify.setText("");
            } catch (Exception e) {
                e.printStackTrace();
                Logger.getLogger(WarrantyManageController.class.getName()).log(Level.SEVERE, null, e);
            }
        } else {
            txtSearchOrderDate.requestFocus();
            txtSearchOrderDate.selectAll();
            txtSearchOrderDate.setFocusColor(Color.RED);
            lblNotify.setText("Order Date format is Incorrect !");
        }
    }

    @FXML
    void updateWarranty(ActionEvent event) {
        txtPeriod.setDisable(false);
        txtPeriod.requestFocus();
        txtPeriod.selectAll();
    }

    void loadCustomerNames() {
        try {
            allCustomer = customerBO.getAllCustomer();
            customerNames = new ArrayList<>();
            nic = new ArrayList<>();
            for (CustomerDTO c : allCustomer) {
                customerNames.add(c.getCustomerName());
                nic.add(c.getNic());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(WarrantyManageController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    void loadCustomerToText() {
        TextFields.bindAutoCompletion(txtSearchCustomer, customerNames);
    }

    void loadNICToText() {
        TextFields.bindAutoCompletion(txtSearchCustomer, nic);
    }

    void loadOrderDates() {
        try {
            customerOrderDates = customerOrdersBO.getCustomerOrderDates();
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(WarrantyManageController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void byName(ActionEvent event) {
        if (rbtnName.isSelected()) {
            rButton = true;
            loadCustomerToText();
        }
    }

    @FXML
    void searchByNIC(ActionEvent event) {
        if (rbtnNIC.isSelected()) {
            rButton = false;
            loadNICToText();
        }
    }

    void loadDateToText() {
        TextFields.bindAutoCompletion(txtSearchOrderDate, customerOrderDates);
    }

    void clearTextFileds() {
        txtItemCode.clear();
        txtItemName.clear();
        txtBrand.clear();
        txtModelNo.clear();
        txtWarrantyID.clear();
        txtWarrantyFrom.clear();
        txtWarrantyTo.clear();
        txtPeriod.clear();
        txtPeriod.setDisable(true);
    }
}
