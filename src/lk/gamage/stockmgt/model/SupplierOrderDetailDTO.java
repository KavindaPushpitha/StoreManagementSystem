package lk.gamage.stockmgt.model;

public class SupplierOrderDetailDTO {
    private String supplierOrderID;
    private String itemCode;
    private int orderQty;

    public SupplierOrderDetailDTO() {
    }

    public SupplierOrderDetailDTO(String supplierOrderID, String itemCode, int orderQty) {
        this.supplierOrderID = supplierOrderID;
        this.itemCode = itemCode;
        this.orderQty = orderQty;
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

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    @Override
    public String toString() {
        return "SupplierOrderDetailDTO{" +
                "supplierOrderID='" + supplierOrderID + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", orderQty=" + orderQty +
                '}';
    }
}
