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
package com.smil.dcs.enums.lm;

/**
 * 商品字典枚举类<br> 
 *
 * @author chenliang
 */
public class LmDicEnum {
    
    /** 字典类型. */
    public enum DicTypeEnum {
        CARGO_TYPE("cargoType", "货物类型"),
        PURCHASE_ORDER_TYPE("purchaseOrderType", "采购订单类型"),
        SALES_ORDER_TYPE("salesOrderType", "销售订单类型"),
        DELIVERY_NOTE_STATUS("DeliveryNoteStatus", "DeliveryNoteStatus开票状态"),
        LEAD_TIME_COUNTRY("leadTimeCountry","发运国家时间"),
        NOTICE_CI_DATA("noticeCiData","采购订单供应商数据"),
        ;

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
