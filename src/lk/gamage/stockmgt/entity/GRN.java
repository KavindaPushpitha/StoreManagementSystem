package lk.gamage.stockmgt.entity;

public class GRN {
    private String grnID;
    private String supplierOrderID;
    private String grnDate;
    private double supplierPayment;
    private String status;

    public GRN() {
    }

    public GRN(String grnID, String supplierOrderID, String grnDate, double supplierPayment, String status) {
        this.grnID = grnID;
        this.supplierOrderID = supplierOrderID;
        this.grnDate = grnDate;
        this.supplierPayment = supplierPayment;
        this.status = status;
    }

    public String getGrnID() {
        return grnID;
    }

    public void setGrnID(String grnID) {
        this.grnID = grnID;
    }

    public String getSupplierOrderID() {
        return supplierOrderID;
    }

    public void setSupplierOrderID(String supplierOrderID) {
        this.supplierOrderID = supplierOrderID;
    }

    public String getGrnDate() {
        return grnDate;
    }

    public void setGrnDate(String grnDate) {
        this.grnDate = grnDate;
    }

    public double getSupplierPayment() {
        return supplierPayment;
    }

    public void setSupplierPayment(double supplierPayment) {
        this.supplierPayment = supplierPayment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "GRN{" +
                "grnID='" + grnID + '\'' +
                ", supplierOrderID='" + supplierOrderID + '\'' +
                ", grnDate='" + grnDate + '\'' +
                ", supplierPayment=" + supplierPayment +
                ", status='" + status + '\'' +
                '}';
    }
}
