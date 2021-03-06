package lk.gamage.stockmgt.model;

public class WarrantyDTO {
    private String warrantyID;
    private String warrantyPeriod;
    private String warrantyFrom;
    private String warrantyTo;

    public WarrantyDTO() {
    }

    public WarrantyDTO(String warrantyID, String warrantyPeriod, String warrantyFrom, String warrantyTo) {
        this.warrantyID = warrantyID;
        this.warrantyPeriod = warrantyPeriod;
        this.warrantyFrom = warrantyFrom;
        this.warrantyTo = warrantyTo;
    }

    public String getWarrantyID() {
        return warrantyID;
    }

    public void setWarrantyID(String warrantyID) {
        this.warrantyID = warrantyID;
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

    @Override
    public String toString() {
        return "WarrantyDTO{" +
                "warrantyID='" + warrantyID + '\'' +
                ", warrantyPeriod='" + warrantyPeriod + '\'' +
                ", warrantyFrom='" + warrantyFrom + '\'' +
                ", warrantyTo='" + warrantyTo + '\'' +
                '}';
    }
}
