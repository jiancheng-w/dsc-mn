/*
 * Copyright (C), 2013-2019, 上海赛可电子商务有限公司
 * FileName: IAmInterfaceService.java
 * Author:   chenliang
 * Date:     2019年2月23日 下午5:06:22
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.smil.dcs.service;

import java.util.List;

/**
 * am接口封装.<br> 
 *
 * @author chenliang
 */
public interface IAmInterfaceService {
    /**
     * 功能描述: <br>
     * 调用AM接口.<br>
     * 根据数据权限维度、用户code查询用户该维度下拥有的数据权限.
     *
     * @param dimType 使用AmApiBizConsts中的DIM_TYPE_ORG、DIM_TYPE_BRAND、DIM_TYPE_REGION
     * @param userCode
     * @return 参数中传入的维度下有权限的去重code集合.
     */
    List<String> queryUserDataPermission(String dimType, String userCode);
}
