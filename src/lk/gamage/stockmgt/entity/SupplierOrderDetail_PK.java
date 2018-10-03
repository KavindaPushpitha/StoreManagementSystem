package lk.gamage.stockmgt.entity;

public class SupplierOrderDetail_PK {
    private String supplierOrderID;
    private String itemCode;

    public SupplierOrderDetail_PK() {
    }

    public SupplierOrderDetail_PK(String supplierOrderID, String itemCode) {
        this.supplierOrderID = supplierOrderID;
        this.itemCode = itemCode;
    }

    public String getSupplierOrderID() {
        return supplierOrderID;
    }

    public void setSupplierOrderID(String supplierOrderID) {
        this.supplierOrderID = supplierOrderID;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }
}
