package lk.gamage.stockmgt.entity;

public class GRNDetail {
    private GRNDetail_PK grnDetail_pk;
    private double buyingPrice;
    private int qty;

    public GRNDetail() {
    }

    public GRNDetail(GRNDetail_PK grnDetail_pk, double buyingPrice, int qty) {
        this.grnDetail_pk = grnDetail_pk;
        this.buyingPrice = buyingPrice;
        this.qty = qty;
    }

    public GRNDetail(String grnID, String itemCode, String stockID, double buyingPrice, int qty) {
        this.grnDetail_pk = new GRNDetail_PK(grnID, itemCode, stockID);
        this.buyingPrice = buyingPrice;
        this.qty = qty;
    }

    public GRNDetail_PK getGrnDetail_pk() {
        return grnDetail_pk;
    }

    public void setGrnDetail_pk(GRNDetail_PK grnDetail_pk) {
        this.grnDetail_pk = grnDetail_pk;
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

    @Override
    public String toString() {
        return "GRNDetail{" +
                "grnDetail_pk=" + grnDetail_pk +
                ", buyingPrice=" + buyingPrice +
                ", qty=" + qty +
                '}';
    }
}
