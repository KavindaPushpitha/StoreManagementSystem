package lk.gamage.stockmgt.entity;

public class CustomerOrders {
    private String orderID;
    private String customerID;
    private String orderDate;
    private double customerPayment;

    public CustomerOrders() {
    }

    public CustomerOrders(String orderID, String customerID, String orderDate, double customerPayment) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.orderDate = orderDate;
        this.customerPayment = customerPayment;
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

    @Override
    public String toString() {
        return "CustomerOrders{" +
                "orderID='" + orderID + '\'' +
                ", customerID='" + customerID + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", customerPayment=" + customerPayment +
                '}';
    }
}
