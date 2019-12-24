/*
 * Copyright (C), 2013-2018, 上海赛可电子商务有限公司
 * FileName: McDicEnum.java
 * Author:   chenliang
 * Date:     2018年12月21日 下午1:05:00
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.smil.dcs.enums.mc;

/**
 * 商品字典枚举类<br> 
 *
 * @author chenliang
 */
public class McDicEnum {
    /** 字典类型枚举. */
    public enum TypeEnum {
        /** 促销类型 */
        PROMTYPE("PT", "促销类型"),
        IF_MARGIN("IF_MARGIN", "运费费率类型"),
        ORDER_TYPE("ORDER_TYPE", "订单类型类型");

        private String code;
        private String value;

        TypeEnum(String code, String value) {
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
    
    /** 字典类型枚举. */
    public enum PromTypeEnum {
        /** 促销类型 */
        PROMTYPE_CA("5", "Campaign"),
        PROMTYPE_PR("6", "Promotion"),
        PROMTYPE_NV("7", "New Vehicle");

        private String code;
        private String value;

        PromTypeEnum(String code, String value) {
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
        public static PromTypeEnum getEnum(String code) {
            PromTypeEnum[] values = PromTypeEnum.values();
            for (PromTypeEnum dicTypeEnum : values) {
                if (dicTypeEnum.getCode().equals(code)) {
                    return dicTypeEnum;
                }
            }
            return null;
        }
    }
    
    
    /** 字典类型枚举. */
    public enum SalesOrderTypeEnum {
        /** 促销类型 */
        URGENT("3", "Urgent"),
        VOR("4", "VOR");

        private String code;
        private String value;

        SalesOrderTypeEnum(String code, String value) {
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
        public static SalesOrderTypeEnum getEnum(String code) {
            SalesOrderTypeEnum[] values = SalesOrderTypeEnum.values();
            for (SalesOrderTypeEnum dicTypeEnum : values) {
                if (dicTypeEnum.getCode().equals(code)) {
                    return dicTypeEnum;
                }
            }
            return null;
        }
    }
    
    /** 字典类型枚举. */
    public enum OrderTypeEnum {
        /** 促销类型 */
        STOCK("1", "Stock"),
        SPECIAL("2", "Special"),
        URGENT("3", "Urgent"),
        VOR("4", "VOR"),
        CAMPAIGN("5", "Campaign"),
        PROMOTION("6", "Promotion"),
        NEW_VEHICLE("7", "New Vehicle");
        
        private String code;
        private String value;

        OrderTypeEnum(String code, String value) {
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
        public static OrderTypeEnum getEnum(String code) {
            OrderTypeEnum[] values = OrderTypeEnum.values();
            for (OrderTypeEnum dicTypeEnum : values) {
                if (dicTypeEnum.getCode().equals(code)) {
                    return dicTypeEnum;
                }
            }
            return null;
        }
    }

    /** 商品定时刷新状态枚举. */
    public enum RlmdsetmStatusEnum {
        FAILD("0", "刷新失败"),
        SUCESS("1", "刷新成功");

        private String code;
        private String value;

        RlmdsetmStatusEnum(String code, String value) {
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

    /** 商品定时刷新枚举. */
    public enum RlmdsetMdseEnum {
        STARTTIME("STARTTIME", "起始时间"),
        ENDTIME("ENDTIME", "结束时间"),
        STATUS("STATUS", "状态");

        private String code;
        private String value;

        RlmdsetMdseEnum(String code, String value) {
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
}
