/*
 * Copyright (C), 2013-2018, 上海赛可电子商务有限公司
 * FileName: McBizConsts.java
 * Author:   chenliang
 * Date:     2018年12月21日 上午11:35:16
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.smil.dcs.enums.mc;

/**
 * 商品常量类<br>
 * .
 *
 * @author chenliang
 */
public class McBizConsts {
    
    /**
     * Instantiates a new mc biz consts.
     */
    private McBizConsts() {
        super();
    }

    /** 百分比四舍五入到小数点4位. */
    public static final int ROUNDING_PRE = 4;
    
    /** 金额四舍五入到小数点2位. */
    public static final int ROUNDING_CUR = 2;

    /** 数据库保留8位 */
    public static final int ROUNDING_DB = 8;
}
