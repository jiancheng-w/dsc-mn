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
package com.smil.dcs.enums.wms;

/**
 * 商品字典枚举类<br> 
 *
 * @author chenliang
 */
public class WmsDicEnum {
    
    /** 字典类型. */
    public enum DicTypeEnum {
        SMPLE("SMPLE", "样例，有其它值时删除该样例"),
        GOODSTOCVS("GOODSTOCVS", "商品标准数据发送到HELLMAN_TIME"),
        SS_STATUS("SS_STATUS", "货物差异状态");

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
