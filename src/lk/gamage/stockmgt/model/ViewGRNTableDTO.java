package lk.gamage.stockmgt.model;

public class ViewGRNTableDTO {
    private String grnID;
    private String supplierOrderID;
    private String supplierName;
    private String companyName;
    private String supplierOrderDate;
    private String grnDate;
    private double suppplierPayemnt;

    public ViewGRNTableDTO() {
    }

    public ViewGRNTableDTO(String grnID, String supplierOrderID, String supplierName, String companyName, String supplierOrderDate, String grnDate, double suppplierPayemnt) {
        this.grnID = grnID;
        this.supplierOrderID = supplierOrderID;
        this.supplierName = supplierName;
        this.companyName = companyName;
        this.supplierOrderDate = supplierOrderDate;
        this.grnDate = grnDate;
        this.suppplierPayemnt = suppplierPayemnt;
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

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSupplierOrderDate() {
        return supplierOrderDate;
    }

    public void setSupplierOrderDate(String supplierOrderDate) {
        this.supplierOrderDate = supplierOrderDate;
    }

    public String getGrnDate() {
        return grnDate;
    }

    public void setGrnDate(String grnDate) {
        this.grnDate = grnDate;
    }

    public double getSuppplierPayemnt() {
        return suppplierPayemnt;
    }

    public void setSuppplierPayemnt(double suppplierPayemnt) {
        this.suppplierPayemnt = suppplierPayemnt;
    }

    @Override
    public String toString() {
        return "ViewGRNTableDTO{" +
                "grnID='" + grnID + '\'' +
                ", supplierOrderID='" + supplierOrderID + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", supplierOrderDate='" + supplierOrderDate + '\'' +
                ", grnDate='" + grnDate + '\'' +
                ", suppplierPayemnt=" + suppplierPayemnt +
                '}';
    }
}
