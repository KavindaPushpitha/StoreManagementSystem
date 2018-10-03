package lk.gamage.stockmgt.model;

public class ViewCustomerOrderTableDTO {
    private String itemCode;
    private String orderID;
    private String itemName;
    private String brand;
    private String modelNo;
    private String orderDate;
    private double unitPrice;
    private int orderQty;

    public ViewCustomerOrderTableDTO() {
    }

    public ViewCustomerOrderTableDTO(String itemCode, String orderID, String itemName, String brand, String modelNo, String orderDate, double unitPrice, int orderQty) {
        this.itemCode = itemCode;
        this.orderID = orderID;
        this.itemName = itemName;
        this.brand = brand;
        this.modelNo = modelNo;
        this.orderDate = orderDate;
        this.unitPrice = unitPrice;
        this.orderQty = orderQty;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModelNo() {
        return modelNo;
    }

    public void setModelNo(String modelNo) {
        this.modelNo = modelNo;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
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
        return "ViewCustomerOrderTableDTO{" +
                "itemCode='" + itemCode + '\'' +
                ", orderID='" + orderID + '\'' +
                ", itemName='" + itemName + '\'' +
                ", brand='" + brand + '\'' +
                ", modelNo='" + modelNo + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", unitPrice=" + unitPrice +
                ", orderQty=" + orderQty +
                '}';
    }
}
