package lk.gamage.stockmgt.entity;

public class OrderDetail_PK {
    private String orderID;
    private String itemCode;

    public OrderDetail_PK() {
    }

    public OrderDetail_PK(String orderID, String itemCode) {
        this.orderID = orderID;
        this.itemCode = itemCode;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }
}
