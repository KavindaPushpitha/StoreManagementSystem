package lk.gamage.stockmgt.model;

public class ManageWarrantyTableDTO {
    private String orderID;
    private String itemCode;
    private String warrantyID;
    private String itemName;
    private String brand;
    private String modelNo;
    private String warrantyPeriod;
    private String warrantyFrom;
    private String warrantyTo;
    private int orderQty;

    public ManageWarrantyTableDTO() {
    }

    public ManageWarrantyTableDTO(String orderID, String itemCode, String warrantyID, String itemName, String brand, String modelNo, String warrantyPeriod, String warrantyFrom, String warrantyTo, int orderQty) {
        this.orderID = orderID;
        this.itemCode = itemCode;
        this.warrantyID = warrantyID;
        this.itemName = itemName;
        this.brand = brand;
        this.modelNo = modelNo;
        this.warrantyPeriod = warrantyPeriod;
        this.warrantyFrom = warrantyFrom;
        this.warrantyTo = warrantyTo;
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

    public String getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(String warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    public String getWarrantyFrom() {
        return warrantyFrom;
    }

    public void setWarrantyFrom(String warrantyFrom) {
        this.warrantyFrom = warrantyFrom;
    }

    public String getWarrantyTo() {
        return warrantyTo;
    }

    public void setWarrantyTo(String warrantyTo) {
        this.warrantyTo = warrantyTo;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    @Override
    public String toString() {
        return "ManageWarrantyTableDTO{" +
                "orderID='" + orderID + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", warrantyID='" + warrantyID + '\'' +
                ", itemName='" + itemName + '\'' +
                ", brand='" + brand + '\'' +
                ", modelNo='" + modelNo + '\'' +
                ", warrantyPeriod='" + warrantyPeriod + '\'' +
                ", warrantyFrom='" + warrantyFrom + '\'' +
                ", warrantyTo='" + warrantyTo + '\'' +
                ", orderQty=" + orderQty +
                '}';
    }
}
