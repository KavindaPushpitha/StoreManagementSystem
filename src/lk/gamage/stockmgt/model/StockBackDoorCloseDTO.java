package lk.gamage.stockmgt.model;

public class StockBackDoorCloseDTO {
    private static boolean clicked=false;

    public StockBackDoorCloseDTO() {
    }

    public static boolean isClicked() {
        return clicked;
    }

    public static void setClicked(boolean clicked) {
        StockBackDoorCloseDTO.clicked = clicked;
    }
}
