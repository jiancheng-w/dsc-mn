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
package com.smil.dcs.enums.vc;

/**
 * 凭证类型枚举配置<br>
 *
 * @author duankk
 */
public enum VoucherTypeEnums {

    PURCHASE((byte) 1, "采购"),
    PAYMENT((byte) 2, "付款"),
    SALES((byte) 3, "销售");

    private Byte code;
    private String desc;

    VoucherTypeEnums(Byte code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Byte getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}

