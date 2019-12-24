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

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.stream.Stream;

/**
 * 商品字典枚举类<br> 
 *
 * @author chenliang
 */
public class SysBizEnum {
    /** 编号规则类型. <br>
     *   每个类型的具体编号见表：sys_num_rule
     * */
    public enum RuleTypeEnum {
        EXAMPLE1 ("EXAMPLE1", "EXAMPLE1"),
        EXAMPLE2 ("EXAMPLE2", "EXAMPLE2"),
        PO ("PO", "Purchasae order (SMIL part)"),
        ASN ("ASN", "Advanceed Shipping Notice"),
        PRI ("PRI", "Purchase Invoice"),
        SO ("SO", "Sales order"),
        DN ("DN", "Delivery Note"),
        CI ("CI", "Commercial Invoice"),
        POC ("POC", "Purchase Claim (for Purchase order)"),
        SOC ("SOC", "Purchase Claim (for Sales order)"),
        BOE ("BOE", "BOE report"),
        PAI("PA","Payment Advice"),
        DA ("DA", "DELIVERY ADVICE"),
        PAYMENT_INFO ("PAYMENT_INFO", "PAYMENT_INFO"),
        PAYMENT ("PAYMENT", "PAYMENT"),
        PAYMENT_ADVANCE ("PAYMENT_ADVANCE", "PAYMENT_ADVANCE"),
        CAC_DR_OUTCOME ("CAC_DR_OUTCOME", "CAC_DR_OUTCOME"),
        CAC_DR_INCOME ("CAC_DR_INCOME", "CAC_DR_INCOME"),
        CAC_CR_OUTCOME ("CAC_CR_OUTCOME", "CAC_CR_OUTCOME"),
        CAC_CR_INCOME ("CAC_CR_INCOME", "CAC_CR_INCOME"),
        CAT_CUST_ACC("CAC_CUST_ACC", "CAC_CUST_ACC"),
        LM_LOG_LINE("LM_LOG_LINE", "Logistics Line No Rule"),
        SVO("SVO", "sales voucher number"),
        PVO("PVO","purchase voucher number"),
        EVO("EVO", "Expense Voucher Number"),
        PMV("PMV", "Payment Voucher Number"),
        INBOUND("INBOUND", "wms inbound number"),
        PAYMENT_RECHARGE("PAYMENT_RECHARGE","PAYMENT_RECHARGE"),
        INBOUND_ASN("INBOUND_ASN","INBOUND ASN NUMBER"),
        INBOUND_ASN_ITEM("INBOUND_ASN_ITEM","INBOUND ASN ITEM NUMBER"),
        INBOUND_ASN_PKL("INBOUND_ASN_PKL","INBOUND ASN PKL NUMBER"),
        PUR_ORDER_ITEM("PUR_ORDER_ITEM","PURCHASE ORDER ITEM NO"),
        PUR_OG_ORDER("PUR_OG_ORDER","PURCHASE ORIGINAL ORDER NO"),
        PUR_OG_ORDER_ITEM("PUR_OG_ORDER_ITEM","PURCHASE ORIGINAL ORDER ITEM NO"),
        SALES_ORDER_ITEM("SALES_ORDER_ITEM","SALES ORDER ITEM NO"),
        SALES_OG_ORDER("SALES_OG_ORDER","SALES ORIGINAL ORDER NO"),
        SALES_OG_ORDER_ITEM("SALES_OG_ORDER_ITEM","SALES ORIGINAL ORDER ITEM NO"),
        OUTBOUND("OUTBOUND", "wms outbound number"),
        PI("PI", "pi number"),
        PII("PII", "pi item number"),
        CI_ITEM("CI_ITEM","Commercial Invoice item number"),
        SALES_PRICE_VERSION("SPV", "Sales Price Version"),
        CUSTOMER_DECLARATION_NOTICE("CDN", "Customer Declaration Notice");

        private String code;
        private String value;

        RuleTypeEnum(String code, String value) {
            this.code = code;
            this.value = value;
        }
        
        private static Map<String, RuleTypeEnum> enumMap = Maps.newHashMap();
        static {
            Stream.of(RuleTypeEnum.values()).forEach(obj ->  enumMap.put(obj.getCode(), obj));
        }
        public static RuleTypeEnum getEnumByCode(String code) {  
            return StringUtils.isBlank(code) ? null : enumMap.get(code);   
        }  

        public String getCode() {
            return code;
        }

        public String getValue() {
            return value;
        }
    }
    
    /** 任务类型. */
    public enum TaskTypeEnum {
        MENU_AM("MENU_AM", "AM菜单提示"),
        CLASS_BIG("CLASS_BIG", "大类提示"),
        CLASS_SMALL("CLASS_SMALL", "小类提示");

        private String code;
        private String value;

        TaskTypeEnum(String code, String value) {
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
