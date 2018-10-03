package lk.gamage.stockmgt.entity;

public class SupplierOrderDetail {
    private SupplierOrderDetail_PK supplierOrderDetail_pk;
    int qty;

    public SupplierOrderDetail() {
    }

    public SupplierOrderDetail(SupplierOrderDetail_PK supplierOrderDetail_pk, int qty) {
        this.supplierOrderDetail_pk = supplierOrderDetail_pk;
        this.qty = qty;
    }

    public SupplierOrderDetail(String supplierOrderID, String itemCode, int qty) {
        this.supplierOrderDetail_pk = new SupplierOrderDetail_PK(supplierOrderID, itemCode);
        this.qty = qty;
    }

    public SupplierOrderDetail_PK getSupplierOrderDetail_pk() {
        return supplierOrderDetail_pk;
    }

    public void setSupplierOrderDetail_pk(SupplierOrderDetail_PK supplierOrderDetail_pk) {
        this.supplierOrderDetail_pk = supplierOrderDetail_pk;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "SupplierOrderDetail{" +
                "supplierOrderDetail_pk=" + supplierOrderDetail_pk +
                ", qty=" + qty +
                '}';
    }
}
