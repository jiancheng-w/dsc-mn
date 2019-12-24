/*
 * Copyright (C), 2013-2018, 上海赛可电子商务有限公司
 * FileName: CacheEnums.java
 * Author:   chenliang
 * Date:     2018年12月4日 下午5:49:13
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.smil.dcs.enums;

/**
 * 缓存枚举配置<br> 
 *
 * @author chenliang
 */
public class CacheEnums {
    /** 缓存命名空间.按应用分. */
    public enum CacheNamespace {
        AM, MDM, DCS, SYS, LM, WMS, CAC, OMS, PC,VC
    }
    
    /** redis缓存时间. */
    public enum CacheExpireEnum {
        /** 永远不过期. <strong style="color:red">慎用</strong> */
        SEC00(0, "SEC00"), 
        /** 5分钟过期 */
        MIN05(5*60, "MIN05"), 
        /** 10分钟过期 */
        MIN10(10*60, "MIN10"), 
        /** 30分钟过期 */
        MIN30(30*60, "MIN30"), 
        /** 1小时过期 */
        MIN60(60*60, "MIN60"), 
        /** 24小时过期 */
        HOUR24(24*60*60, "HOUR24");
        
        /** 永远不过期. <strong style="color:red">慎用</strong> */
        public static final String CACHENAME_SEC00 = "SEC00";
        /** 5分钟过期 */
        public static final String CACHENAME_MIN05 = "MIN05";
        /** 10分钟过期 */
        public static final String CACHENAME_MIN10 = "MIN10";
        /** 30分钟过期 */
        public static final String CACHENAME_MIN30 = "MIN30";
        /** 1小时过期 */
        public static final String CACHENAME_MIN60 = "MIN60";
        /** 24小时过期 */
        public static final String CACHENAME_HOUR24 = "HOUR24";

        private Integer code;
        private String value;

        CacheExpireEnum(Integer code, String value) {
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
}

