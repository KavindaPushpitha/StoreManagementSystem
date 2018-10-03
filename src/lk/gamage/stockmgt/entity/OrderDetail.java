package lk.gamage.stockmgt.entity;

public class OrderDetail {
    private OrderDetail_PK orderDetail_pk;
    private String warrantyID;
    private double unitPrice;
    private int orderQty;

    public OrderDetail() {
    }

    public OrderDetail(OrderDetail_PK orderDetail_pk, String warrantyID, double unitPrice, int orderQty) {
        this.orderDetail_pk = orderDetail_pk;
        this.warrantyID = warrantyID;
        this.unitPrice = unitPrice;
        this.orderQty = orderQty;
    }

    public OrderDetail(String orderID, String itemCode, String warrantyID, double unitPrice, int orderQty) {
        this.orderDetail_pk = new OrderDetail_PK(orderID, itemCode);
        this.warrantyID = warrantyID;
        this.unitPrice = unitPrice;
        this.orderQty = orderQty;
    }

    public OrderDetail_PK getOrderDetail_pk() {
        return orderDetail_pk;
    }

    public void setOrderDetail_pk(OrderDetail_PK orderDetail_pk) {
        this.orderDetail_pk = orderDetail_pk;
    }

    public String getWarrantyID() {
        return warrantyID;
    }

    public void setWarrantyID(String warrantyID) {
        this.warrantyID = warrantyID;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderDetail_pk=" + orderDetail_pk +
                ", warrantyID='" + warrantyID + '\'' +
                ", unitPrice=" + unitPrice +
                ", orderQty=" + orderQty +
                '}';
    }
}
