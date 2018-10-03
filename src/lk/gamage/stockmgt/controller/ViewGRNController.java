package lk.gamage.stockmgt.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import lk.gamage.stockmgt.business.BOFactory;
import lk.gamage.stockmgt.business.custom.GRNBO;
import lk.gamage.stockmgt.business.custom.GRNDetailBO;
import lk.gamage.stockmgt.business.custom.SupplierBO;
import lk.gamage.stockmgt.business.custom.impl.GRNBOImpl;
import lk.gamage.stockmgt.business.custom.impl.GRNDetailBOImpl;
import lk.gamage.stockmgt.business.custom.impl.SupplierBOImpl;
import lk.gamage.stockmgt.common.Validation;
import lk.gamage.stockmgt.model.AuthorizeGRNTableDTO;
import lk.gamage.stockmgt.model.GRNDTO;
import lk.gamage.stockmgt.model.SupplierDTO;
import lk.gamage.stockmgt.model.ViewGRNTableDTO;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewGRNController implements Initializable {
    @FXML
    private JFXTextField txtSearch;

    @FXML
    private TableView<ViewGRNTableDTO> tblGrnS;

    @FXML
    private TableView<AuthorizeGRNTableDTO> tblGrnDetail;

    @FXML
    private JFXTextField txtGrnId;

    @FXML
    private JFXTextField txtSupplierOrderID;
    @FXML
    private Label txtNotify;
    @FXML
    private JFXTextField txtSupplierName;
    @FXML
    private JFXTextField txtFromSupplierName;
    @FXML
    private JFXTextField txtTotalPayment;
    private ArrayList<SupplierDTO> allSupplier;
    private ArrayList<String> supplierNames;
    private ArrayList<GRNDTO> allGRN;
    private ArrayList<String> grnDates;
    private SupplierBO supplierBO;
    private GRNDetailBO grnDetailBO;
    private GRNBO grnbo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        supplierBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.SUPPLIER);
        grnDetailBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.GRN_DETAIL);
        grnbo = BOFactory.getInstance().getBO(BOFactory.BOTypes.GRN);
        tblGrnS.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("grnID"));
        tblGrnS.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("supplierOrderID"));
        tblGrnS.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        tblGrnS.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("companyName"));
        tblGrnS.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("supplierOrderDate"));
        tblGrnS.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("grnDate"));
        tblGrnS.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("suppplierPayemnt"));

        tblGrnDetail.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblGrnDetail.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("stockID"));
        tblGrnDetail.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("itemName"));
        tblGrnDetail.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("brand"));
        tblGrnDetail.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("modelNo"));
        tblGrnDetail.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblGrnDetail.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("buyingPrice"));
        loadSupplierNames();
        loadToSupplierTxt();
        getAllGRNDates();
        loadGRNDates();
    }

    @FXML
    void onMouseClicked(MouseEvent event) {
        if (tblGrnS.getSelectionModel().getSelectedIndex() != -1) {
            txtGrnId.setText(tblGrnS.getSelectionModel().getSelectedItem().getGrnID());
            txtSupplierOrderID.setText(tblGrnS.getSelectionModel().getSelectedItem().getSupplierOrderID());
            txtSupplierName.setText(tblGrnS.getSelectionModel().getSelectedItem().getSupplierName());
            txtTotalPayment.setText(tblGrnS.getSelectionModel().getSelectedItem().getSuppplierPayemnt() + "");
            try {
                ArrayList<AuthorizeGRNTableDTO> allGRNDetail = grnDetailBO.getAllGRNDetail(txtGrnId.getText());
                tblGrnDetail.setItems(FXCollections.observableArrayList(allGRNDetail));
            } catch (Exception e) {
                e.printStackTrace();
                Logger.getLogger(ViewGRNController.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    @FXML
    void searchGRNs(ActionEvent event) {
        if (Validation.nameValidate(txtFromSupplierName.getText())) {
            try {
                ArrayList<ViewGRNTableDTO> allGRNQuery = grnbo.getAllGRNQuery(txtSearch.getText(), txtFromSupplierName.getText());
                tblGrnS.setItems(FXCollections.observableArrayList(allGRNQuery));
            } catch (Exception e) {
                e.printStackTrace();
                Logger.getLogger(ViewGRNController.class.getName()).log(Level.SEVERE, null, e);
            }
        } else {
            txtFromSupplierName.requestFocus();
            txtFromSupplierName.setFocusColor(Color.RED);
        }

    }

    void loadSupplierNames() {
        try {
            allSupplier = supplierBO.getAllSupplier();
            supplierNames = new ArrayList<>();
            for (SupplierDTO supplierDTO : allSupplier) {
                supplierNames.add(supplierDTO.getSupplierName());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(ViewGRNController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    void loadToSupplierTxt() {
        TextFields.bindAutoCompletion(txtFromSupplierName, supplierNames);
    }

    void getAllGRNDates() {
        try {
            allGRN = grnbo.getAllGRN();
            grnDates = new ArrayList<>();
            for (GRNDTO grndto : allGRN) {
                grnDates.add(grndto.getGrnDate());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(ViewGRNController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    void loadGRNDates() {
        TextFields.bindAutoCompletion(txtSearch, grnDates);
    }

    @FXML
    void focusChangeToSupplierName(ActionEvent event) {
        if (Validation.dateValidation(txtSearch.getText())) {
            txtFromSupplierName.requestFocus();
            txtNotify.setText("");
        } else {
            txtSearch.requestFocus();
            txtSearch.setFocusColor(Color.RED);
            txtSearch.selectAll();
            txtNotify.setText("Date pattern is incorrect (YYYY-MM-DD)");
        }
    }

}
