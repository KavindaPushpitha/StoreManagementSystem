package lk.gamage.stockmgt.model;

public class AddNewGRNTableDTO {
    private String stockID;
    private String itemCode;
    private String itemName;
    private double buyingPrice;
    private int qty;
    private double amount;

    public AddNewGRNTableDTO() {
    }

    public AddNewGRNTableDTO(String stockID, String itemCode, String itemName, double buyingPrice, int qty, double amount) {
        this.stockID = stockID;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.buyingPrice = buyingPrice;
        this.qty = qty;
        this.amount = amount;
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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "AddNewGRNTableDTO{" +
                "stockID='" + stockID + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", buyingPrice=" + buyingPrice +
                ", qty=" + qty +
                ", amount=" + amount +
                '}';
    }
}
