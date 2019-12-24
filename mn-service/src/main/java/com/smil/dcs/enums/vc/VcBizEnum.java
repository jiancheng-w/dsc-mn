package com.smil.dcs.enums.vc;

public class VcBizEnum {

    /**
     * 采购凭证状态枚举
     */
    public enum PurSettlementStatusEnum {
        NOT_FOUND((byte)-99,""),
        POSTED_ERROR((byte)0, "Posted error"),
        POSTED_SUCCESS((byte)1, "Posted success");

        private Byte value;
        private String name;

        PurSettlementStatusEnum(Byte value, String name) {
            this.value = value;
            this.name = name;
        }

        public Byte getValue() {
            return value;
        }

        public String getName() {
            return name;
        }

        /**
         * 功能描述: <br>
         * 根据value获取枚举.
         *
         * @param value
         * @return value对应的枚举, 未找到返回null.
         */
        public static PurSettlementStatusEnum getEnum(Byte value) {
            PurSettlementStatusEnum[] values = PurSettlementStatusEnum.values();
            for (PurSettlementStatusEnum purSettlementStatusEnum : values) {
                if (purSettlementStatusEnum.getValue().equals(value)) {
                    return purSettlementStatusEnum;
                }
            }
            return NOT_FOUND;
        }
    }

    /**
     * 销售凭证状态枚举
     */
    public enum VoucherDataStatusEnum {
        NOT_FOUND((byte)-99,""),
        POSTED_ERROR((byte)-1, "Posted error"),
        CREATED((byte)1, "Created"),
        POSTED_SUCCESS((byte)2, "Posted Success");

        private Byte value;
        private String name;

        VoucherDataStatusEnum(Byte value, String name) {
            this.value = value;
            this.name = name;
        }

        public Byte getValue() {
            return value;
        }

        public String getName() {
            return name;
        }

        /**
         * 功能描述: <br>
         * 根据value获取枚举.
         *
         * @param value
         * @return value对应的枚举, 未找到返回NOT_FOUND
         */
        public static VoucherDataStatusEnum getEnum(Byte value) {
            VoucherDataStatusEnum[] values = VoucherDataStatusEnum.values();
            for (VoucherDataStatusEnum voucherDataStatusEnum : values) {
                if (voucherDataStatusEnum.getValue().equals(value)) {
                    return voucherDataStatusEnum;
                }
            }
            return NOT_FOUND;
        }
    }

    /**
     * 费用校验单状态枚举
     */
    public enum ExpenseVerifyOrderDataStatusEnum {
        NOT_FOUND((byte)-99,""),
        POST_ERROR((byte)-2, "Post Error"),
        OA_REJECTED((byte)-1, "OA Rejected"),
        SAVED((byte)0, "Saved"),
        SUBMITTED((byte)1, "Submitted"),
        POSTED((byte)2, "OA Approved"),
        OA_APPROVED((byte)3, "Posted Success"),
        PAID((byte)4, "Paid");

        private Byte value;
        private String name;

        ExpenseVerifyOrderDataStatusEnum(Byte value, String name) {
            this.value = value;
            this.name = name;
        }

        /**
         * 功能描述: <br>
         * 根据value获取枚举.
         *
         * @param value
         * @return value对应的枚举, 未找到返回NOT_FOUND
         */
        public static ExpenseVerifyOrderDataStatusEnum getEnum(Byte value) {
            ExpenseVerifyOrderDataStatusEnum[] values = ExpenseVerifyOrderDataStatusEnum.values();
            for (ExpenseVerifyOrderDataStatusEnum expenseVerifyOrderDataStatusEnum : values) {
                if (expenseVerifyOrderDataStatusEnum.getValue().equals(value)) {
                    return expenseVerifyOrderDataStatusEnum;
                }
            }
            return NOT_FOUND;
        }

        public Byte getValue() {
            return value;
        }

        public String getName() {
            return name;
        }
    }

}
