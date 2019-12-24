package com.smil.dcs.enums.oms;

public class OmsDicEnum {

    /** 字典类型. */
    public enum DicTypeEnum {
        PUR_ORDER_TYPE("purOderType", "采购订单类型"),
        PUR_ORDER_STATUS("purOderStatus", "Purchase Order Status"),
        SALES_ORDER_TYPE("salesOrderType", "销售订单类型"),
        SALES_TYPE("salesType", "销售类型"),
        PORT_OF_LOAD("portOfLoad", "Port Of Load"),
        PORT_OF_DESTINATION("portOfDestination", "Port Of Destination"),
        PUR_ORDER_CREATION_STATUS("purOrderCreationStatus", "Purchase Order Creation Status"),
        PUR_ORDER_ENQUIRY_STATUS("purOrderEnquiryStatus", "Purchase Order Enquiry Status"),
        PUR_TRANSPORTATION_MODE("transportationMode","Transportation Mode"),
        SALES_TRANSPORTATION_MODE("salesTransportationMode","sales Transportation Mode"),
        PURCHASE_TYPE("purchaseType" ,"purchase type"),
        REQ_TO_ORDER("reqToOrder" ,"request to order"),
        IS_BO("isBo" ,"is bo"),
        AVAILABILITY_STATUS("AvailabilityStatus","Availability Status"),
        CO_NO("CoNo","co company no"),
        SALES_OG_ORDER_STATUS("salesOgOrderStatus"," sales og order status");

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
