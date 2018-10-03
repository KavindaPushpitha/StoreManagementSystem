package lk.gamage.stockmgt.entity;

public class SupplierOrders {
    private String supplierOrderID;
    private String supplierID;
    private String orderDate;

    public SupplierOrders() {
    }

    public SupplierOrders(String supplierOrderID, String supplierID, String orderDate) {
        this.supplierOrderID = supplierOrderID;
        this.supplierID = supplierID;
        this.orderDate = orderDate;
    }

    public String getSupplierOrderID() {
        return supplierOrderID;
    }

    public void setSupplierOrderID(String supplierOrderID) {
        this.supplierOrderID = supplierOrderID;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "SupplierOrders{" +
                "supplierOrderID='" + supplierOrderID + '\'' +
                ", supplierID='" + supplierID + '\'' +
                ", orderDate='" + orderDate + '\'' +
                '}';
    }
}
