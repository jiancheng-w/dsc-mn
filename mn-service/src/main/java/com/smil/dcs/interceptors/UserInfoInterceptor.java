package com.smil.dcs.interceptors;

import com.alibaba.fastjson.JSONObject;
import com.smil.dcs.common.SystemUtils;
import com.smil.dcs.common.UserUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *用户信息拦截器
 * <h3>将用户信息写入到ThreadLocal中</h3>
 */
public class UserInfoInterceptor extends HandlerInterceptorAdapter {
    private static final Logger LOGGER = LogManager.getLogger(UserInfoInterceptor.class);

    @Autowired
    private SystemUtils systemUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserUtils.setUserInfo(getUser(request));

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {
        UserUtils.remove();
    }

    public UserUtils.User getUser(HttpServletRequest request) {
        UserUtils.User userInfo = null;

        if (systemUtils.isDevEnvironment()) {
            userInfo = UserUtils.createDefaultUser("dev-user");
        } else {
            userInfo = new UserUtils.User();

            userInfo.setUserCode(request.getHeader("ucode"));
            userInfo.setOrgCode(request.getHeader("orgcode"));
            userInfo.setAppCode(request.getHeader("appcode"));

            String roleCodeList = request.getHeader("roleCodeList");
            if (roleCodeList != null && !roleCodeList.trim().equals("")) {
                userInfo.setRoleCodeList(JSONObject.parseArray(roleCodeList, String.class));
            }
        }
        LOGGER.info("user information : {}", userInfo);
        return userInfo;
    }
}
