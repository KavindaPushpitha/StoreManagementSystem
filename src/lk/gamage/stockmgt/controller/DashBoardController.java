package lk.gamage.stockmgt.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import lk.gamage.stockmgt.business.BOFactory;
import lk.gamage.stockmgt.business.custom.GRNBO;
import lk.gamage.stockmgt.model.GRNDTO;
import lk.gamage.stockmgt.model.StockBackDoorCloseDTO;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DashBoardController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    public AnchorPane dashRoot;

    @FXML
    private Label lblCustomerOrders;

    @FXML
    private ImageView btnViewSupplierOrders;
    @FXML
    private ImageView btnAbout;
    @FXML
    private ImageView btnViewCustomerOrders;
    @FXML
    private Label lblItemMgt;

    @FXML
    private Label lblCustomerMgt;

    @FXML
    private Label lblSupplierOrders;
    @FXML
    private ImageView btnBulb;

    @FXML
    private Label lblSupplierMgt;
    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;
    @FXML
    private Label lblWarrantyMgt;

    @FXML
    private ImageView btnDamageItems;

    @FXML
    private Label lblDamageItems;

    @FXML
    private Label lblNewGRN;

    @FXML
    private Label lblViewGRN;

    @FXML
    private ImageView btnReports;

    @FXML
    private Label lblReports;

    @FXML
    private ImageView btnStockManagement;

    @FXML
    private Label lblStockMgt;

    @FXML
    private ImageView btnReturnItems;

    @FXML
    private Label lblReturnItems;

    @FXML
    private ImageView btnCustOrders;

    @FXML
    private ImageView btnItemMgt;

    @FXML
    private ImageView btnCustomerMgt;

    @FXML
    private ImageView btnSupplierOrders;

    @FXML
    private ImageView btnSupplier;

    @FXML
    private ImageView btnWarrantyMgt;

    @FXML
    private ImageView btnNewGRN;

    @FXML
    private ImageView btnViewGRN;

    @FXML
    public static Label lblNewGRNNotification;

    @FXML
    private ImageView lblSideCustOrders;

    @FXML
    private ImageView btnSideSupOrder;

    @FXML
    private ImageView btnSideSupMgt;

    @FXML
    private ImageView btnSideCustMgt;

    @FXML
    private ImageView btnLogOut;

    @FXML
    private ImageView btnSideItemMgt;

    @FXML
    private ImageView btnSideWarrantyMgt;

    @FXML
    private ImageView btnSideDamageItem;

    @FXML
    private ImageView btnSideNewGRN;
    @FXML
    private ImageView btnAuthorizeGRN;
    @FXML
    private ImageView btnHome;

    @FXML
    private ImageView btnDashMini;

    @FXML
    private ImageView btnDashClose;
    @FXML
    public ImageView btnStockBackDoor;
    @FXML
    private ImageView btnAccountMgt;
    private GRNBO grnbo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        grnbo = BOFactory.getInstance().getBO(BOFactory.BOTypes.GRN);
        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000), dashRoot);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(4), btnBulb);
        scaleTransition.setToX(1.3);
        scaleTransition.setToY(1.3);
        scaleTransition.setAutoReverse(true);
        scaleTransition.setCycleCount(TranslateTransition.INDEFINITE);
        scaleTransition.play();
        setDateTime();
        try {
            GRNDTO grndto = grnbo.searchGRN("Not authorized");
            if (grndto != null) {
                Notifications notificationsn = Notifications.create().title("Authorize GRN").text("New GRN is waiting for authorization !").hideAfter(Duration.seconds(3)).position(Pos.BOTTOM_RIGHT);
                notificationsn.darkStyle();
                notificationsn.showInformation();
            } else {
//                Notifications notificationsn=Notifications.create().title("Authorize GRN").text("No GRN to Authorize !").hideAfter(Duration.seconds(3)).position(Pos.BOTTOM_RIGHT);
//                notificationsn.darkStyle();
//                notificationsn.showInformation();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    @FXML
    void loadReports(MouseEvent event) {
        try {
            loadPane("/lk/gamage/stockmgt/view/Reports.fxml", dashRoot);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    @FXML
    void closeDashBoard(MouseEvent event) {
        Alert really = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to exit ?", ButtonType.NO, ButtonType.YES);
        Optional<ButtonType> result = really.showAndWait();
        if (result.get() == ButtonType.YES) {
            System.exit(2);
            StockBackDoorCloseDTO.setClicked(true);
        }


    }

    @FXML
    void minimizeDashBoard(MouseEvent event) {
        Stage stage = (Stage) ((ImageView) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void loadStockBackDoor(MouseEvent event) {
        try {
            if (!StockBackDoorCloseDTO.isClicked())
                loadPane("/lk/gamage/stockmgt/view/StockBackDoor.fxml", dashRoot);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void loadCustomerOrders(MouseEvent event) {
        try {
            loadPane("/lk/gamage/stockmgt/view/CustomerOrders.fxml", dashRoot);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    @FXML
    void loadMangeCustomer(MouseEvent event) {
        try {
            loadPane("/lk/gamage/stockmgt/view/ManageCustomers.fxml", dashRoot);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    @FXML
    void loadViewCustomerOrders(MouseEvent event) {
        try {
            loadPane("/lk/gamage/stockmgt/view/ViewCustomerOrders.fxml", dashRoot);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void loadDashBoard(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(this.getClass().getResource("/lk/gamage/stockmgt/view/DashBoard.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) this.dashRoot.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void loadSupplier(MouseEvent event) {
        try {
            loadPane("/lk/gamage/stockmgt/view/SupplierDetails.fxml", dashRoot);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    @FXML
    void loadAccountMgt(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(this.getClass().getResource("/lk/gamage/stockmgt/view/Accountmgt.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) this.root.getScene().getWindow();

            stage.setScene(scene);
            stage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void loadSupplierOrders(MouseEvent event) {
        try {
            loadPane("/lk/gamage/stockmgt/view/SupplierOrders.fxml", dashRoot);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    @FXML
    void loadWarrantyMgt(MouseEvent event) {
        try {
            loadPane("/lk/gamage/stockmgt/view/WarrantyManagement.fxml", dashRoot);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    @FXML
    void loadAbout(MouseEvent event) {
        try {
            Parent parent = FXMLLoader.load(this.getClass().getResource("/lk/gamage/stockmgt/view/About.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) this.root.getScene().getWindow();

            stage.setScene(scene);
            stage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void loadNewGRN(MouseEvent event) {
        try {
            loadPane("/lk/gamage/stockmgt/view/AddNewGRN.fxml", dashRoot);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    @FXML
    void loadViewGRN(MouseEvent event) {
        try {
            loadPane("/lk/gamage/stockmgt/view/ViewGRNs.fxml", dashRoot);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    @FXML
    void loadManageItem(MouseEvent event) {
        try {
            loadPane("/lk/gamage/stockmgt/view/ManageItems.fxml", dashRoot);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    @FXML
    void loadDamageItems(MouseEvent event) {
        try {
            loadPane("/lk/gamage/stockmgt/view/DamageItem.fxml", dashRoot);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    @FXML
    void loadStockManagement(MouseEvent event) {
        try {
            loadPane("/lk/gamage/stockmgt/view/StockManagement.fxml", dashRoot);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, e);
        }


    }

    @FXML
    void loadAuthorizeGRN(MouseEvent event) {
        try {
            loadPane("/lk/gamage/stockmgt/view/AuthorizeGRN.fxml", dashRoot);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    public void loadPane(String url, AnchorPane anchorPane) {
        AnchorPane pane = null;
        try {
            pane = FXMLLoader.load(this.getClass().getResource(url));
        } catch (IOException e) {
            e.printStackTrace();
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, e);
        }
        anchorPane.getChildren().setAll(pane);
        Scene scene = dashRoot.getScene();
        //TranslateTransition transition=new TranslateTransition(Duration.millis(1000),scene.getRoot());
        //transition.setFromX(-scene.getWidth());
        //transition.setToX(0);
        //transition.play();
        FadeTransition fadeIn = new FadeTransition(Duration.millis(1000), dashRoot);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

    }

    private void setDateTime() {
        Timeline newTimeLine = new Timeline(new KeyFrame(Duration.seconds(0), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                lblDate.setText(new SimpleDateFormat("YYYY-MM-dd").format(new Date()));
                lblTime.setText(new SimpleDateFormat("hh:mm:ss a").format(new Date()));
            }

        }), new KeyFrame(Duration.seconds(1)));
        newTimeLine.setCycleCount(Animation.INDEFINITE);
        newTimeLine.play();
    }

    @FXML
    void loadCustMgtForSide(MouseEvent event) {
        loadMangeCustomer(event);
    }

    @FXML
    void loadCustOrdersForSide(MouseEvent event) {
        loadCustomerOrders(event);
    }

    @FXML
    void loadDamageItemForSide(MouseEvent event) {
        loadDamageItems(event);
    }

    @FXML
    void loadItemMgtForSide(MouseEvent event) {
        loadManageItem(event);
    }

    @FXML
    void loadNewGRNForSide(MouseEvent event) {
        loadNewGRN(event);
    }

    @FXML
    void loadSupMgtForSide(MouseEvent event) {
        loadSupplier(event);
    }

    @FXML
    void loadViewSupplierOrders(MouseEvent event) {
        try {
            loadPane("/lk/gamage/stockmgt/view/ViewSupplierOrders.fxml", dashRoot);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void loadSupOrdersForSide(MouseEvent event) {
        loadSupplierOrders(event);
    }

    @FXML
    void loadWarrantyMgtForSide(MouseEvent event) {
        loadWarrantyMgt(event);
    }

    @FXML
    void loadReturnItem(MouseEvent event) {
        try {
            loadPane("/lk/gamage/stockmgt/view/ReturnItems.fxml", dashRoot);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void logOut(MouseEvent event) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Do you really want to Logout?", ButtonType.NO, ButtonType.YES);
        Optional<ButtonType> result = a.showAndWait();
        if (result.get() == ButtonType.YES) {
            System.exit(2);
        }
    }

    @FXML
    void mouseEntered(MouseEvent event) {

        if (event.getSource() instanceof ImageView) {
            ImageView image = (ImageView) event.getSource();
            String id = image.getId();


            ScaleTransition sca = new ScaleTransition(Duration.millis(200), image);
            sca.setToX(1.2);
            sca.setToY(1.2);
            sca.play();

            DropShadow d = new DropShadow();
            d.setColor(Color.SKYBLUE);
            d.setHeight(20);
            d.setWidth(20);
            d.setRadius(20);
            image.setEffect(d);

        }
    }

    @FXML
    void mouseExited(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView image = (ImageView) event.getSource();
            String id = image.getId();

            image.setEffect(null);

            ScaleTransition s = new ScaleTransition(Duration.millis(500), image);
            s.setToX(1.0);
            s.setToY(1.0);
            s.play();

        }
    }

    @FXML
    void sideButtonsMouseEntered(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView image = (ImageView) event.getSource();
            String id = image.getId();

            ScaleTransition sca = new ScaleTransition(Duration.millis(500), image);
            sca.setToX(1.2);
            sca.setToY(1.2);
            sca.play();

            DropShadow d = new DropShadow();
            d.setColor(Color.BLACK);
            d.setHeight(10);
            d.setWidth(10);
            d.setRadius(10);
            image.setEffect(d);

        }
    }

    @FXML
    void sideButtonsMouseExited(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView image = (ImageView) event.getSource();
            String id = image.getId();

            image.setEffect(null);

            ScaleTransition s = new ScaleTransition(Duration.millis(300), image);
            s.setToX(1.0);
            s.setToY(1.0);
            s.play();

        }
    }

    public void setText(String message) {
        lblNewGRNNotification.setText("New GRN");
    }

}


