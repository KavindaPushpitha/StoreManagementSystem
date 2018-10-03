package lk.gamage.stockmgt.model;


import java.sql.Date;
import java.util.ArrayList;

public class OrdersDTO {
    String orderID;
    String customerID;
    String orderDate;
    double customerPayment;
    ArrayList<WarrantyDTO> warrantyDTOS;
    ArrayList<OrderDetailDTO> orderDetailDTOS;

    public OrdersDTO() {
    }

    public OrdersDTO(String orderID, String customerID, String orderDate, double customerPayment, ArrayList<WarrantyDTO> warrantyDTOS, ArrayList<OrderDetailDTO> orderDetailDTOS) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.orderDate = orderDate;
        this.customerPayment = customerPayment;
        this.warrantyDTOS = warrantyDTOS;
        this.orderDetailDTOS = orderDetailDTOS;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public double getCustomerPayment() {
        return customerPayment;
    }

    public void setCustomerPayment(double customerPayment) {
        this.customerPayment = customerPayment;
    }

    public ArrayList<WarrantyDTO> getWarrantyDTOS() {
        return warrantyDTOS;
    }

    public void setWarrantyDTOS(ArrayList<WarrantyDTO> warrantyDTOS) {
        this.warrantyDTOS = warrantyDTOS;
    }

    public ArrayList<OrderDetailDTO> getOrderDetailDTOS() {
        return orderDetailDTOS;
    }

    public void setOrderDetailDTOS(ArrayList<OrderDetailDTO> orderDetailDTOS) {
        this.orderDetailDTOS = orderDetailDTOS;
    }

    @Override
    public String toString() {
        return "OrdersDTO{" +
                "orderID='" + orderID + '\'' +
                ", customerID='" + customerID + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", customerPayment=" + customerPayment +
                ", warrantyDTOS=" + warrantyDTOS +
                ", orderDetailDTOS=" + orderDetailDTOS +
                '}';
    }
}
