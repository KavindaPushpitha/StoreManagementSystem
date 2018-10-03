package sample;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

public class Test {
    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY/MM/dd");
        String today = dateFormat.format(cal.getTime());
        cal.add(Calendar.MONTH, Integer.parseInt("6"));
        String after = dateFormat.format(cal.getTime());
        System.out.println(after);


    }
}
