package util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 *
 * @author wimde
 */
public class NumberUtil {

    public static double convertToDouble(String s) {
        String res = s.replace("€", "").trim().replace(".", "").replace(",", ".");
        return Double.parseDouble(res);
    }

    public static String formatDouble(double d) {
        DecimalFormat formatter = new DecimalFormat("€ #,###.00;€ -#,###.00");
        if (d == 0) {
            return "€ 0.00";
        }
        return formatter.format(d);
    }

    public static double getAsPercent(double d) {
        return Math.ceil(d * 100);
    }

}
