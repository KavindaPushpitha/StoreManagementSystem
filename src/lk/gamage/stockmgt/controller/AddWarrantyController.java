package lk.gamage.stockmgt.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddWarrantyController implements Initializable {
    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXTextField txtWarrantyID;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXTextField txtModelNo;

    @FXML
    private JFXTextField txtOrderID;

    @FXML
    private JFXTextField txtItemName;

    @FXML
    private JFXTextField txtWarrantyPeriod;

    @FXML
    private JFXTextField txtWarrantyFrom;

    @FXML
    private JFXTextField txtWarrantyTo;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private ImageView lblClose;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void cancelWarrantyForm(ActionEvent event) {

    }

    @FXML
    void closeWarrantyForm(MouseEvent event) {
        AddWarrantyController addWarrantyController = new AddWarrantyController();
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onAction(ActionEvent event) {
//        CustomerOrderController customerOrderController=new CustomerOrderController();
//        customerOrderController.txtCash.setText(txtWarrantyID.getText());


    }


}
