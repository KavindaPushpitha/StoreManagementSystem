package lk.gamage.stockmgt.model;

public class OrderDetailDTO {
    private String orderID;
    private String itemCode;
    private String warrantyID;
    private double unitPrice;
    private int orderQty;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String orderID, String itemCode, String warrantyID, double unitPrice, int orderQty) {
        this.orderID = orderID;
        this.itemCode = itemCode;
        this.warrantyID = warrantyID;
        this.unitPrice = unitPrice;
        this.orderQty = orderQty;
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
        return "OrderDetailDTO{" +
                "orderID='" + orderID + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", warrantyID='" + warrantyID + '\'' +
                ", unitPrice=" + unitPrice +
                ", orderQty=" + orderQty +
                '}';
    }
}
