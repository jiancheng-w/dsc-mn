package com.smil.dcs.converter;

public class EuropeNumberConverter {
    public static String toNormalNumberString(String numberString) {
        int commaIndex = numberString.indexOf(','), dotIndex = numberString.indexOf('.');

        if (commaIndex > -1 && dotIndex > -1) {
            throw new RuntimeException("input number string can't contains comma and dot at the same time, " + numberString);
        }

        return numberString.replace(',', '.');
    }
}
