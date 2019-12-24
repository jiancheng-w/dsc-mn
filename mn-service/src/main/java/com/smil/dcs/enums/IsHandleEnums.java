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
 * 是否手工枚举配置<br>
 *
 * @author duankk
 */
public enum IsHandleEnums {

    NO((byte) 0, "否"),
    YES((byte) 1, "是");

    private Byte code;
    private String desc;

    IsHandleEnums(Byte code, String desc) {
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

