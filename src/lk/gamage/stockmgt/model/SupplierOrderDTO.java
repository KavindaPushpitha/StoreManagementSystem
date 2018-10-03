package lk.gamage.stockmgt.model;

import java.util.ArrayList;

public class SupplierOrderDTO {
    private String supplierOrderID;
    private String supplierID;
    private String supplierOrderDate;
    private ArrayList<SupplierOrderDetailDTO> supplierOrderDetailDTOS;


    public SupplierOrderDTO() {
    }

    public SupplierOrderDTO(String supplierOrderID, String supplierID, String supplierOrderDate, ArrayList<SupplierOrderDetailDTO> supplierOrderDetailDTOS) {
        this.supplierOrderID = supplierOrderID;
        this.supplierID = supplierID;
        this.supplierOrderDate = supplierOrderDate;
        this.supplierOrderDetailDTOS = supplierOrderDetailDTOS;
    }

    public String getSupplierOrderID() {
        return supplierOrderID;
    }

    public void setSupplierOrderID(String supplierOrderID) {
        this.supplierOrderID = supplierOrderID;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getSupplierOrderDate() {
        return supplierOrderDate;
    }

    public void setSupplierOrderDate(String supplierOrderDate) {
        this.supplierOrderDate = supplierOrderDate;
    }

    public ArrayList<SupplierOrderDetailDTO> getSupplierOrderDetailDTOS() {
        return supplierOrderDetailDTOS;
    }

    public void setSupplierOrderDetailDTOS(ArrayList<SupplierOrderDetailDTO> supplierOrderDetailDTOS) {
        this.supplierOrderDetailDTOS = supplierOrderDetailDTOS;
    }

    @Override
    public String toString() {
        return "SupplierOrderDTO{" +
                "supplierOrderID='" + supplierOrderID + '\'' +
                ", supplierID='" + supplierID + '\'' +
                ", supplierOrderDate='" + supplierOrderDate + '\'' +
                ", supplierOrderDetailDTOS=" + supplierOrderDetailDTOS +
                '}';
    }
}
