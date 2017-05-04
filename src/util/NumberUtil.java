/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 *
 * @author wimde
 */
public class NumberUtil {

    public static String formatDouble(double d) {
        DecimalFormat formatter = new DecimalFormat("€ #,###.00");
        return formatter.format(d);
    }

    public static double getAsPercent(double d) {
        return Math.ceil(d * 100);
    }

}
