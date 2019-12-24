package com.smil.dcs.utils;

import java.math.BigDecimal;

public class BigDecimalUtils {

    private BigDecimalUtils() {
    }

    public static BigDecimal add(BigDecimal a1, BigDecimal a2){
        return nullTrans(a1).add(nullTrans(a2));
    }

    public static BigDecimal subtract(BigDecimal a1,BigDecimal a2){
        return nullTrans(a1).subtract(nullTrans(a2));
    }

    public static BigDecimal divide(BigDecimal a1,BigDecimal a2){
        if(a1 == null){
            return BigDecimal.ZERO;
        }
        return nullTrans(a1).divide(nullTrans(a2));
    }

    public static BigDecimal multiply(BigDecimal a1,BigDecimal a2){
        return nullTrans(a1).multiply(nullTrans(a2));
    }

    public static BigDecimal add(BigDecimal a1,BigDecimal ... as){
        BigDecimal addRes = nullTrans(a1);
        for(BigDecimal a : as){
            addRes = addRes.add(nullTrans(a));
        }
        return addRes;
    }

    private static BigDecimal nullTrans(BigDecimal a){
        return a == null ? BigDecimal.ZERO : a;
    }
}
