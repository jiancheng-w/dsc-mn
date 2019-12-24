package com.smil.dcs.enums;


/** 销售区域. */
public enum MarketAreaEnum {

    SMME("6", "SMME"),
    SME("9", "SME");

    private String code;

    private String value;

    MarketAreaEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}