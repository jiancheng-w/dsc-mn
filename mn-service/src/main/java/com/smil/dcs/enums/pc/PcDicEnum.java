package com.smil.dcs.enums.pc;

public class PcDicEnum {

    /** 字典类型. */
    public enum DicTypeEnum {
        INFO_STATUS("infoStatus", "支付信息状态"),
        CONFIRM_STATUS("confirmStatus", "支付确认状态"),
        PROCESS_STATUS("processStatus","处理状态"),
        INVOICE_TYPE("invoiceType","发票类型");

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
