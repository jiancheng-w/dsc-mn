package com.smil.dcs.service;

import com.smil.dcs.common.UserUtils;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;

public abstract class DcsJobHandler extends IJobHandler {

    public abstract ReturnT<String> run(String var1) throws Exception;

    @Override
    public ReturnT<String> execute(String var1) throws Exception {
        setUserInfo();
        return run(var1);
    }

    private void setUserInfo() {
        UserUtils.User userInfo = UserUtils.createDefaultUser("xxl-job");

        UserUtils.setUserInfo(userInfo);
    }
}
