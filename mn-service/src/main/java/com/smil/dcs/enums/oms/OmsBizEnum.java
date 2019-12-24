package com.smil.dcs.enums.oms;

public class OmsBizEnum {

    public enum TransModeEnum{

        SEA("Sea","Sea"),
        AIR("Air","Air"),
        EXPRESS("Express","Express");

        /** The code. */
        private String code;

        /** The value. */
        private String value;

        /**
         * Instantiates a new trans mode enum.
         *
         * @param code  the code
         * @param value the value
         */
        TransModeEnum(String code, String value) {
            this.code = code;
            this.value = value;
        }

        /**
         * Gets the code.
         *
         * @return the code
         */
        public String getCode() {
            return code;
        }

        /**
         * Gets the value.
         *
         * @return the value
         */
        public String getValue() {
            return value;
        }

    }
    
    
    

    /**
     * 品牌编号枚举
     */
    public enum BrandNoEnum {
        BRAND_UNKNOWN("", "UNKNOWN"),
        BRAND_MG("A", "MG"),
        BRAND_MAXUS("B", "Maxus");

        private String code;
        private String name;

        BrandNoEnum(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        /**
         * 功能描述: <br>
         * 根据code获取枚举.
         *
         * @param name
         * @return name对应的枚举, 未找到返回null.
         */
        public static BrandNoEnum getEnum(String name) {
            BrandNoEnum[] values = BrandNoEnum.values();
            for (BrandNoEnum brandNoEnum : values) {
                if (brandNoEnum.getName().equals(name)) {
                    return brandNoEnum;
                }
            }
            return BRAND_UNKNOWN;
        }
    }

    /**
     * SMME采购订单状态枚举
     */
    public enum PurOrderStatusEnum {
        REJECTED((byte)-2, "Rejected"),
        INTERNAL_REJECTED((byte)-1, "Internal rejected"),
        CANCELED((byte)0, "Canceled"),
        SAVED((byte)1, "Saved"),
        SUBMITTED((byte)2, "Submitted"),
        INTERNAL_APPROVED((byte)3, "Internal approved"),
        APPROVED((byte)4, "Approved"),
        PARTIALLY_PACKED((byte)5, "Partially packed"),
        PACKED((byte)6, "Packed"),
        PARTIALLY_SHIPPED((byte)7, "Partially shipped"),
        SHIPPED((byte)8, "Shipped"),
        PARTIALLY_RECEIVED((byte)9, "Partially received"),
        RECEIVED((byte)10, "Received");

        private Byte value;
        private String name;

        PurOrderStatusEnum(Byte value, String name) {
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
        public static PurOrderStatusEnum getEnum(Byte value) {
            PurOrderStatusEnum[] values = PurOrderStatusEnum.values();
            for (PurOrderStatusEnum purOrderStatusEnum : values) {
                if (purOrderStatusEnum.getValue().equals(value)) {
                    return purOrderStatusEnum;
                }
            }
            return null;
        }
    }

    /**
     * SMME采购订单子项状态枚举
     */
    public enum PurOrderItemStatusEnum {
        CANCELED((byte)0, "Canceled"),
        CREATED((byte)1, "Created"),
        APPROVED((byte)2, "Approved"),
        PACKED((byte)3, "Packed"),
        SHIPPED((byte)4, "Shipped"),
        RECEIVED((byte)5, "Received"),
        DAMAGED((byte)6, "Damaged");

        private Byte value;
        private String name;

        PurOrderItemStatusEnum(Byte value, String name) {
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
        public static PurOrderItemStatusEnum getEnum(Byte value) {
            PurOrderItemStatusEnum[] values = PurOrderItemStatusEnum.values();
            for (PurOrderItemStatusEnum purOderStatusEnum : values) {
                if (purOderStatusEnum.getValue().equals(value)) {
                    return purOderStatusEnum;
                }
            }
            return null;
        }
    }

    /**
     * 销售订单状态枚举
     */
    public enum SalesOrderStatusEnum {
        REJECTED((byte)-1, "Rejected"),
        CLOSED((byte)0, "Closed"),
        SUBMITTED((byte)1, "Submitted"),
        APPROVED((byte)2, "Approved"),
        PARTIALLY_INVOICED((byte)3, "Partially invoiced"),
        INVOICED((byte)4, "Invoiced"),
        PARTIALLY_SHIPPED((byte)5, "Partially Shipped"),
        SHIPPED((byte)6, "Shipped"),
        PARTIALLY_DELIVERED((byte)7, "Partially delivered"),
        DELIVERED((byte)8, "Delivered");

        private Byte value;
        private String name;

        SalesOrderStatusEnum(Byte value, String name) {
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
        public static SalesOrderStatusEnum getEnum(Byte value) {
            SalesOrderStatusEnum[] values = SalesOrderStatusEnum.values();
            for (SalesOrderStatusEnum salesOrderStatusEnum : values) {
                if (salesOrderStatusEnum.getValue().equals(value)) {
                    return salesOrderStatusEnum;
                }
            }
            return null;
        }
    }

    /**
     * 销售订单子项状态枚举
     */
    public enum SalesOrderItemStatusEnum {
        NOT_FOUND((byte)-99,""),
        CANCELED((byte)0, "Canceled"),
        INVOICED((byte)1, "Invoiced"),
        DELIVERED((byte)2, "Delivered"),
        PENDING((byte)3, "Pending"),
        NOTE((byte)4, "Note"),
        PACKED((byte)5, "Packed");

        private Byte value;
        private String name;

        SalesOrderItemStatusEnum(Byte value, String name) {
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
        public static SalesOrderItemStatusEnum getEnum(Byte value) {
            SalesOrderItemStatusEnum[] values = SalesOrderItemStatusEnum.values();
            for (SalesOrderItemStatusEnum salesOrderItemStatusEnum : values) {
                if (salesOrderItemStatusEnum.getValue().equals(value)) {
                    return salesOrderItemStatusEnum;
                }
            }
            return NOT_FOUND;
        }
    }

    /**
     * 销售类型枚举
     */
    public enum SalesTypeEnum {
        SPARE_PARTS((byte)1, "Spare parts"),
        ALLIED_PRODUCT((byte)2, "Allied product");

        private Byte value;
        private String name;

        SalesTypeEnum(Byte value, String name) {
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
        public static SalesTypeEnum getEnum(Byte value) {
            SalesTypeEnum[] values = SalesTypeEnum.values();
            for (SalesTypeEnum salesTypeEnum : values) {
                if (salesTypeEnum.getValue().equals(value)) {
                    return salesTypeEnum;
                }
            }
            return null;
        }
    }

    /**
     * 订单类型枚举
     */
    public enum OrderTypeEnum {
        NOT_FOUND("-99",""),
        NORMAL("1", "Normal"),
        VOR("2", "VOR"),
        FORWARD("3", "Forward");

        private String value;
        private String name;

        OrderTypeEnum(String value, String name) {
            this.value = value;
            this.name = name;
        }

        public String getValue() {
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
        public static OrderTypeEnum getEnum(String value) {
            OrderTypeEnum[] values = OrderTypeEnum.values();
            for (OrderTypeEnum orderTypeEnum : values) {
                if (orderTypeEnum.getValue().equals(value)) {
                    return orderTypeEnum;
                }
            }
            return NOT_FOUND;
        }
    }
    
    /**
     * Bo otw ord enum
     */
    public enum BoTypeEnum {
        OTW((byte)1, "OTW"),
        ORD((byte)2, "ORD");
    
        private Byte value;
        private String name;

        BoTypeEnum(Byte value, String name) {
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
        public static BoTypeEnum getEnum(Byte value) {
            BoTypeEnum[] values = BoTypeEnum.values();
            for (BoTypeEnum boTypeEnum : values) {
                if (boTypeEnum.getValue().equals(value)) {
                    return boTypeEnum;
                }
            }
            return null;
        }
    }
}
