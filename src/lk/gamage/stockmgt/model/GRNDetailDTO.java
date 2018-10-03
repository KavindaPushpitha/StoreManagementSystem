package lk.gamage.stockmgt.model;

public class GRNDetailDTO {
    private String grnID;
    private String itemCode;
    private String stockID;
    private double buyingPrice;
    private int qty;

    public GRNDetailDTO() {
    }

    public GRNDetailDTO(String grnID, String itemCode, String stockID, double buyingPrice, int qty) {
        this.grnID = grnID;
        this.itemCode = itemCode;
        this.stockID = stockID;
        this.buyingPrice = buyingPrice;
        this.qty = qty;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getGrnID() {
        return grnID;
    }

    public void setGrnID(String grnID) {
        this.grnID = grnID;
    }

    public String getStockID() {
        return stockID;
    }

    public void setStockID(String stockID) {
        this.stockID = stockID;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    @Override
    public String toString() {
        return "GRNDetailDTO{" +
                "grnID='" + grnID + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", stockID='" + stockID + '\'' +
                ", buyingPrice=" + buyingPrice +
                ", qty=" + qty +
                '}';
    }
}
