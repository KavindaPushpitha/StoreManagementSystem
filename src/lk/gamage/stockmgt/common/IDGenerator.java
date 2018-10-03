package lk.gamage.stockmgt.common;

import java.text.NumberFormat;

public class IDGenerator {
    public static String getNewID(String tableName, String colName, String prifix) throws Exception {
        String lastId = IdController.getLastID(tableName, colName);
        if (lastId != null) {
            int id = Integer.parseInt(lastId.split(prifix)[1]);
            id++;
            NumberFormat numberFormat = NumberFormat.getIntegerInstance();
            numberFormat.setMinimumIntegerDigits(3);
            numberFormat.setGroupingUsed(false);
            String newID = numberFormat.format(id);
            return prifix + newID;
        } else {
            return prifix + "001";
        }
    }
}
