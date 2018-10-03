package lk.gamage.stockmgt.model;

import java.sql.Date;
import java.util.ArrayList;

public class GRNDTO {
    private String grnID;
    private String supplierOrderID;
    private String grnDate;
    private double supplierPayment;
    private String status;
    private ArrayList<GRNDetailDTO> grnDetailDTOS;

    public GRNDTO() {
    }

    public GRNDTO(String grnID, String supplierOrderID, String grnDate, double supplierPayment, String status, ArrayList<GRNDetailDTO> grnDetailDTOS) {
        this.grnID = grnID;
        this.supplierOrderID = supplierOrderID;
        this.grnDate = grnDate;
        this.supplierPayment = supplierPayment;
        this.status = status;
        this.grnDetailDTOS = grnDetailDTOS;
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

    public ArrayList<GRNDetailDTO> getGrnDetailDTOS() {
        return grnDetailDTOS;
    }

    public void setGrnDetailDTOS(ArrayList<GRNDetailDTO> grnDetailDTOS) {
        this.grnDetailDTOS = grnDetailDTOS;
    }

    @Override
    public String toString() {
        return "GRNDTO{" +
                "grnID='" + grnID + '\'' +
                ", supplierOrderID='" + supplierOrderID + '\'' +
                ", grnDate='" + grnDate + '\'' +
                ", supplierPayment=" + supplierPayment +
                ", status='" + status + '\'' +
                ", grnDetailDTOS=" + grnDetailDTOS +
                '}';
    }
}
