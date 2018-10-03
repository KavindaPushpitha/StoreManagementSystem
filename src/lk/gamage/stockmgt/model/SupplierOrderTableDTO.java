package lk.gamage.stockmgt.model;

public class SupplierOrderTableDTO {
    private String itemCode;
    private String itemName;
    private String modelNo;
    private String brand;
    private int orderQty;

    public SupplierOrderTableDTO() {
    }

    public SupplierOrderTableDTO(String itemCode, String itemName, String modelNo, String brand, int orderQty) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.modelNo = modelNo;
        this.brand = brand;
        this.orderQty = orderQty;
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

    public String getModelNo() {
        return modelNo;
    }

    public void setModelNo(String modelNo) {
        this.modelNo = modelNo;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    @Override
    public String toString() {
        return "SupplierOrderTableDTO{" +
                "itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", modelNo='" + modelNo + '\'' +
                ", brand='" + brand + '\'' +
                ", orderQty=" + orderQty +
                '}';
    }
}
