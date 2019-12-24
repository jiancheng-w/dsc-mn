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
package com.smil.dcs.enums.sys;

/**
 * 商品字典枚举类<br> 
 *
 * @author chenliang
 */
public class SysDicEnum {
    
    /** 字典类型. */
    public enum DicTypeEnum {
        NUM_RULE("NUM_RULE", "编号规则");

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
    }
    
    /** 编号编码类型. */
    public enum CodingEnum {
        /** 
         * 品牌.<br>
         * <pre>
         * Brand    Code
         * MG        A
         * Maxus     B
         * </pre>
         *  */
        BC("BC", "Brand"),
        /**
         * 运输方式.<br>
         * <pre>
         * Ship mode    Code
         * Sea           1
         * Air           2
         * Direct Sea    3
         * Direct Air    4
         * Emergency     5
         * Express		 5
         * </pre>
         */
        SM("SM", "Ship mode"),
        /**
         * 客户名称.<br>
         * <pre>
         * Customer Name     Code
         * SMME                0
         * Direct – Bahrain    1
         * Direct – Kuwait     2
         * Direct – Oman       3
         * Direct – KSA        4
         * Direct – UAE        5
         * </pre>
         */
        DN("DN", "Customer name"),
        /**
         * Market Area.
         * <pre>
         * Market Area  Code
         * SMME          6
         * </pre>
         */
        MA("MA", "Market Area"),
        /**
         * 订单类型.
         * <pre>
         * Order Type               Code
         * Stock Order                1
         * Urgent Order               2
         * VOR- Vehicle Off Road      3
         * Special Order              4
         * New Vehicle Order          5
         * Campain order              6
         * Promotion order            7
         * </pre>
         */
        OT("OT", "Order Type"),
        /**
         * 运输类型.
         * <pre>
         * Ship Type    Code
         * Normal        A
         * Urgent        B
         * VOR           C
         * </pre>
         */
        ST("ST", "Ship Type"),
        /**
         * 客户短名.
         * <pre>
         * Customer Name         DT short code
         * KSA- Tajeer               TAJ
         * Oman-Mohsin Darwish       MOH
         * </pre>
         */
        DC("DC", "Customer short name");

        /** 用于字符替换copy，顺序固定. */
        protected static final String[] CODING_PATTERN = {"{BC}","{SM}","{DN}","{MA}","{OT}","{ST}","{DC}"};
        public static String[] getCodingPattern() {
            return CODING_PATTERN;
        }
        
        private String code;
        private String value;

        CodingEnum(String code, String value) {
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
