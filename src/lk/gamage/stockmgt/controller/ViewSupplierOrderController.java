package lk.gamage.stockmgt.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import lk.gamage.stockmgt.business.BOFactory;
import lk.gamage.stockmgt.business.custom.SupplierBO;
import lk.gamage.stockmgt.business.custom.SupplierOrderBO;
import lk.gamage.stockmgt.business.custom.impl.SupplierBOImpl;
import lk.gamage.stockmgt.business.custom.impl.SupplierOrderBOImpl;
import lk.gamage.stockmgt.common.Validation;
import lk.gamage.stockmgt.model.SupplierDTO;
import lk.gamage.stockmgt.model.SupplierOrderDTO;
import lk.gamage.stockmgt.model.ViewSupplierOrderTableDTO;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewSupplierOrderController implements Initializable {
    @FXML
    private JFXTextField txtSearchSupplier;

    @FXML
    private TableView<ViewSupplierOrderTableDTO> tblSupplierOrders;

    @FXML
    private JFXTextField txtSupplierID;

    @FXML
    private JFXTextField txtSupplierName;

    @FXML
    private JFXTextField txtSupplierAddresss;

    @FXML
    private JFXTextField txtSupplierTelephone;

    @FXML
    private JFXTextField txtCompanyName;

    @FXML
    private JFXTextField txtCompanyAddress;
    private ArrayList<SupplierDTO> allSupplier;
    private ArrayList<String> supplierNames;
    @FXML
    private JFXTextField txtCompanyTelephone;
    private SupplierBO supplierBO;
    private SupplierOrderBO supplierOrderBO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        supplierOrderBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.SUPPLIER_ORDER);
        supplierBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.SUPPLIER);
        tblSupplierOrders.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblSupplierOrders.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("itemName"));
        tblSupplierOrders.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("brand"));
        tblSupplierOrders.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("modelNo"));
        tblSupplierOrders.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("supplierOrderID"));
        tblSupplierOrders.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        tblSupplierOrders.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("qty"));
        loadAllSuppliers();
        loadSupplierNames();
    }

    void loadAllSuppliers() {
        try {
            allSupplier = supplierBO.getAllSupplier();
            supplierNames = new ArrayList<>();
            for (SupplierDTO supplierDTO : allSupplier) {
                supplierNames.add(supplierDTO.getSupplierName());
            }

        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(ViewSupplierOrderController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    void loadSupplierNames() {
        TextFields.bindAutoCompletion(txtSearchSupplier, supplierNames);
    }

    @FXML
    void searchSupplier(ActionEvent event) {
        if (Validation.nameValidate(txtSearchSupplier.getText())) {
            try {
                SupplierDTO supplierDTO = supplierBO.searchSupplier(txtSearchSupplier.getText());
                if (supplierDTO != null) {
                    txtSupplierID.setText(supplierDTO.getSupplierID());
                    txtSupplierName.setText(supplierDTO.getSupplierName());
                    txtSupplierAddresss.setText(supplierDTO.getAddress());
                    txtSupplierTelephone.setText(supplierDTO.getContact());
                    txtCompanyName.setText(supplierDTO.getCompanyName());
                    txtCompanyAddress.setText(supplierDTO.getCompanyAddress());
                    txtCompanyTelephone.setText(supplierDTO.getCompanyContact());
                    tableLoad();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Logger.getLogger(ViewSupplierOrderController.class.getName()).log(Level.SEVERE, null, e);
            }
        } else {
            txtSearchSupplier.requestFocus();
            txtSearchSupplier.setFocusColor(Color.RED);
        }
    }

    void tableLoad() {
        try {
            ArrayList<ViewSupplierOrderTableDTO> allSupplierOrder = supplierOrderBO.getAllSupplierOrder(txtSupplierID.getText());
            tblSupplierOrders.setItems(FXCollections.observableArrayList(allSupplierOrder));
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(ViewSupplierOrderController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
