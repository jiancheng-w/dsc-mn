/*
 * Copyright (C), 2013-2019, 上海赛可电子商务有限公司
 * FileName: AmInterfaceServiceImpl.java
 * Author:   chenliang
 * Date:     2019年2月23日 下午5:08:51
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.smil.dcs.service.impl;

import com.smil.am.api.client.AmDimClient;
import com.smil.commons.response.GlobalResponse;
import com.smil.dcs.exception.McRuntimeException;
import com.smil.dcs.service.IAmInterfaceService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * am接口封装实现.<br> 
 *
 * @author chenliang
 */
@Service
public class AmInterfaceServiceImpl implements IAmInterfaceService {
    
    /** The am dim client. */
    @Autowired
    AmDimClient amDimClient;

    /** 
     * {@inheritDoc}
     */
    @Override
    public List<String> queryUserDataPermission(String dimType, String userCode) {
        GlobalResponse<List<String>> userDataPermission = amDimClient.queryUserDataPermission(dimType, userCode);
        if (!userDataPermission.getSuccess()) {
            throw new McRuntimeException(null == userDataPermission.getErrorCode() ? null : userDataPermission.getErrorCode().toString(), userDataPermission.getErrorMsg());
        }
        if (null == userDataPermission.getData() || CollectionUtils.isEmpty(userDataPermission.getData())) {
            throw new McRuntimeException("Failed to get user data permission.");
        }
        return userDataPermission.getData();
    }

}
