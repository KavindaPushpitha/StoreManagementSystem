package lk.gamage.stockmgt.entity;

public class GRNDetail_PK {
    private String grnID;
    private String itemCode;
    private String stockID;

    public GRNDetail_PK() {
    }

    public GRNDetail_PK(String grnID, String itemCode, String stockID) {
        this.grnID = grnID;
        this.itemCode = itemCode;
        this.stockID = stockID;
    }

    public String getGrnID() {
        return grnID;
    }

    public void setGrnID(String grnID) {
        this.grnID = grnID;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getStockID() {
        return stockID;
    }

    public void setStockID(String stockID) {
        this.stockID = stockID;
    }
}
