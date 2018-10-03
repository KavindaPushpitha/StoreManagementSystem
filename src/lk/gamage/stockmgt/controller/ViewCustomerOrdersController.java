package lk.gamage.stockmgt.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import lk.gamage.stockmgt.business.BOFactory;
import lk.gamage.stockmgt.business.custom.CustomerBO;
import lk.gamage.stockmgt.business.custom.CustomerOrdersBO;
import lk.gamage.stockmgt.business.custom.impl.CustomerBOImpl;
import lk.gamage.stockmgt.business.custom.impl.CustomerOrdersBOImpl;
import lk.gamage.stockmgt.common.Validation;
import lk.gamage.stockmgt.model.CustomerDTO;
import lk.gamage.stockmgt.model.ViewCustomerOrderTableDTO;
import lk.gamage.stockmgt.model.ViewSupplierOrderTableDTO;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewCustomerOrdersController implements Initializable {
    @FXML
    private JFXTextField txtSearchCustomer;

    @FXML
    private JFXButton btnPrint;

    @FXML
    private TableView<ViewCustomerOrderTableDTO> tblCustomerOrders;

    @FXML
    private JFXTextField txtCustomerID;

    @FXML
    private JFXTextField txtCustomerName;

    @FXML
    private JFXTextField txtCustomerAddress;

    @FXML
    private JFXTextField txtCustomerNIC;

    @FXML
    private JFXSpinner spnSpinner;

    @FXML
    private JFXTextField txtDOB;
    private ArrayList<CustomerDTO> allCustomer;
    private ArrayList<String> customerNames;
    @FXML
    private JFXTextField txtTelephone;
    private CustomerBO customerBO;
    private CustomerOrdersBO customerOrdersBO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customerBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);
        customerOrdersBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER_ORDERS);
        tblCustomerOrders.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblCustomerOrders.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("orderID"));
        tblCustomerOrders.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("itemName"));
        tblCustomerOrders.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("brand"));
        tblCustomerOrders.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("modelNo"));
        tblCustomerOrders.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        tblCustomerOrders.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblCustomerOrders.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("orderQty"));
        loadAllCustomeres();
        loadCustomerNames();
    }

    @FXML
    void printOutput(ActionEvent event) {

    }

    @FXML
    void searchCustomer(ActionEvent event) {
        if (Validation.nameValidate(txtSearchCustomer.getText())) {
            try {
                CustomerDTO customerDTO = customerBO.searchCustomer(txtSearchCustomer.getText());
                if (customerDTO != null) {
                    txtCustomerID.setText(customerDTO.getCustomerID());
                    txtCustomerName.setText(customerDTO.getCustomerName());
                    txtCustomerAddress.setText(customerDTO.getAddress());
                    txtTelephone.setText(customerDTO.getContact());
                    txtCustomerNIC.setText(customerDTO.getNic());
                    txtDOB.setText(customerDTO.getDob());
                    spnSpinner.setVisible(false);
                    getAllOrderDetails();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Logger.getLogger(ViewCustomerOrdersController.class.getName()).log(Level.SEVERE, null, e);
            }
        } else {
            txtSearchCustomer.requestFocus();
            txtSearchCustomer.setFocusColor(Color.RED);
        }

    }

    public void loadAllCustomeres() {
        try {
            allCustomer = customerBO.getAllCustomer();
            customerNames = new ArrayList<>();
            for (CustomerDTO customerDTO : allCustomer) {
                customerNames.add(customerDTO.getCustomerName());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(ViewCustomerOrdersController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    void loadCustomerNames() {
        TextFields.bindAutoCompletion(txtSearchCustomer, customerNames);
    }

    @FXML
    void keyReleased(KeyEvent event) {
        spnSpinner.setVisible(true);
    }

    void getAllOrderDetails() {
        try {
            ArrayList<ViewCustomerOrderTableDTO> allCustomerOrder = customerOrdersBO.getAllCustomerOrder(txtCustomerID.getText());
            tblCustomerOrders.setItems(FXCollections.observableArrayList(allCustomerOrder));
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(ViewCustomerOrdersController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
