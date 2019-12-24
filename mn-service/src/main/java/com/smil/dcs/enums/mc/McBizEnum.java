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
public class McBizEnum {
    
    /** 配额类型，普通配额:1, 促销配额:2. */
    public enum QuotaTypeEnum {
        /** 促销类型 */
        NORMAL_QUOTA(1, "普通配额"),
        PROM_QUOTA(2, "促销配额");

        private Integer code;
        private String value;

        QuotaTypeEnum(Integer code, String value) {
            this.code = code;
            this.value = value;
        }

        public Integer getCode() {
            return code;
        }

        public String getValue() {
            return value;
        }
    }

    /** 船税变更状态，1：变更 0：未变更 */
    public enum  IfRemarkStatus{
        /** 船税变更状态 */
        CHANGE(1, "1","已变更"),
        NO_CHANGE(0, "0","未变更");

        private Integer code;
        private String value;
        private String name;

        IfRemarkStatus(Integer code, String value, String name) {
            this.code = code;
            this.value = value;
            this.name = name;
        }

        public Integer getCode() {
            return code;
        }

        public String getValue() {
            return value;
        }
        public String getName() {
            return name;
        }
    }
}
