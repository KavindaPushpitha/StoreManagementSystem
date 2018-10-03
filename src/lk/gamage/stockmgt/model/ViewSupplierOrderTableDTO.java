package lk.gamage.stockmgt.model;

public class ViewSupplierOrderTableDTO {
    private String itemCode;
    private String itemName;
    private String brand;
    private String modelNo;
    private String supplierOrderID;
    private String orderDate;
    private int qty;

    public ViewSupplierOrderTableDTO() {
    }

    public ViewSupplierOrderTableDTO(String itemCode, String itemName, String brand, String modelNo, String supplierOrderID, String orderDate, int qty) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.brand = brand;
        this.modelNo = modelNo;
        this.supplierOrderID = supplierOrderID;
        this.orderDate = orderDate;
        this.qty = qty;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
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

    public String getSupplierOrderID() {
        return supplierOrderID;
    }

    public void setSupplierOrderID(String supplierOrderID) {
        this.supplierOrderID = supplierOrderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "ViewSupplierOrderTableDTO{" +
                "itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", brand='" + brand + '\'' +
                ", modelNo='" + modelNo + '\'' +
                ", supplierOrderID='" + supplierOrderID + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", qty=" + qty +
                '}';
    }
}
