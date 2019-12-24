package com.smil.dcs.enums.vc;

public class VcDicEnum {

    /** 字典类型. */
    public enum DicTypeEnum {
        PUR_SETTLEMENT_STATUS("purSettlementStatus", "Purchase settlement status"),
        SALES_SETTLEMENT_STATUS("salesSettlementStatus", "Sales settlement status"),
        VAT_COUNTRY("vat_country", "vat country");

        private String code;
        private String value;

        DicTypeEnum(String code, String value) {
            this.code = code;
            this.value = value;
        }

        public String getCode() {
            return code;
        }

        public String getValue() {
            return value;
        }

        /**
         * 功能描述: <br>
         * 根据code获取枚举.
         *
         * @param code
         * @return code对应的枚举,未找到返回null.
         */
        public static DicTypeEnum getEnum(String code) {
            DicTypeEnum[] values = DicTypeEnum.values();
            for (DicTypeEnum dicTypeEnum : values) {
                if (dicTypeEnum.getCode().equals(code)) {
                    return dicTypeEnum;
                }
            }
            return null;
        }

    }
}
