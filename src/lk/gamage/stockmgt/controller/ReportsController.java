package lk.gamage.stockmgt.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import lk.gamage.stockmgt.db.DBConnection;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReportsController implements Initializable {

    @FXML
    private JFXButton btnItemProfit;
    @FXML
    private JFXButton btnCustomerList;

    @FXML
    private JFXButton btnSupplierList;

    @FXML
    private JFXButton btnInventoryList;

    @FXML
    private JFXButton btnDamageItemList;

    @FXML
    private JFXButton btnReturnItemList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    void loadReports(String url) throws SQLException, IOException, ClassNotFoundException, JRException {
        InputStream resourceAsStream = getClass().getResourceAsStream(url);

        HashMap map = new HashMap();
        JasperPrint jasperPrint = JasperFillManager.fillReport(resourceAsStream, map, DBConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint, false);

    }

    @FXML
    void reportSupplierList(ActionEvent event) {
        try {
            loadReports("/lk/gamage/stockmgt/reports/SupplierList1.jasper");
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void CustomerReport(ActionEvent event) {
        try {
            loadReports("/lk/gamage/stockmgt/reports/CustomerList.jasper");
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void loadInventoryList(ActionEvent event) {
        try {
            loadReports("/lk/gamage/stockmgt/reports/InventoryList.jasper");
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void loadDamageList(ActionEvent event) {
        try {
            loadReports("/lk/gamage/stockmgt/reports/DamageItem.jasper");
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void loadReturnItem(ActionEvent event) {
        try {
            loadReports("/lk/gamage/stockmgt/reports/ReturnItem.jasper");
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void loadItemProfit(ActionEvent event) {
        try {
            loadReports("/lk/gamage/stockmgt/reports/ProfitList.jasper");
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
