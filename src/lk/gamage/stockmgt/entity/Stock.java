package lk.gamage.stockmgt.entity;

public class Stock {
    private String stockID;
    private String itemCode;
    private int qtyOnHand;
    private double sellingPrice;

    public Stock() {
    }

    public Stock(String stockID, String itemCode, int qtyOnHand, double sellingPrice) {
        this.stockID = stockID;
        this.itemCode = itemCode;
        this.qtyOnHand = qtyOnHand;
        this.sellingPrice = sellingPrice;
    }

    public String getStockID() {
        return stockID;
    }

    public void setStockID(String stockID) {
        this.stockID = stockID;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "stockID='" + stockID + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", qtyOnHand=" + qtyOnHand +
                ", sellingPrice=" + sellingPrice +
                '}';
    }
}
