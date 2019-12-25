package com.smil.dcs.common;

import com.smil.mn.infrastructure.constant.MailConstant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ChenKuoJun on 2018-12-3
 */
public class UserUtils {
    private static ThreadLocal<User> userInfoThreadLocal = new ThreadLocal<>();
    private static InheritableThreadLocal<User> inheritableThreadLocal = new InheritableThreadLocal<>();

    public static void setUserInfo(User user) {
        userInfoThreadLocal.set(user);
        inheritableThreadLocal.set(user);
    }

    public static User getUserInfo() {
        User user = userInfoThreadLocal.get();
        return user == null ? inheritableThreadLocal.get() : user;
    }

    public static void remove() {
        userInfoThreadLocal.remove();
        inheritableThreadLocal.remove();
    }

    public static String getUserCode() {
//        return getUserInfo().getUserCode();
        return MailConstant.EVENT_USER_CODE;
    }

    public static String getAppCode() {
        return getUserInfo().getAppCode();
    }

    public static String getOrgCode () {
        return getUserInfo().getOrgCode();
    }

    public static ThreadLocal<User> getThreadLocal() {
        return userInfoThreadLocal;
    }

    public static User createDefaultUser(String createName) {
        UserUtils.User userInfo = new UserUtils.User();

        userInfo.setUserCode(createName);
        userInfo.setOrgCode(GlobalConsts.SME_ORG_CODE);
        userInfo.setAppCode(GlobalConsts.SME_APP_CODE);
        userInfo.setRoleCodeList(Collections.singletonList(GlobalConsts.DCS_SME_APPROVER_ROLE_NAME));

        return userInfo;
    }

    public static class User {
        private String userCode;
        private String orgCode;
        private String appCode;
        private List<String> roleCodeList;

        public User() {
            roleCodeList = new ArrayList<>();
        }

        public String getUserCode() {
            return userCode;
        }

        public void setUserCode(String userCode) {
            this.userCode = userCode;
        }

        public String getOrgCode() {
            return orgCode;
        }

        public void setOrgCode(String orgCode) {
            this.orgCode = orgCode;
        }

        public String getAppCode() {
            return appCode;
        }

        public void setAppCode(String appCode) {
            this.appCode = appCode;
        }

        public List<String> getRoleCodeList() {
            return roleCodeList;
        }

        public void setRoleCodeList(List<String> roleCodeList) {
            this.roleCodeList = roleCodeList;
        }

        public boolean isSme() {
            return isSmeApprover() || isSmeFinance() || isSmeOperator();
        }

        public boolean isSmeApprover() {
            return roleCodeList.stream().anyMatch(GlobalConsts.DCS_SME_APPROVER_ROLE_NAME::equalsIgnoreCase);
        }

        public boolean isSmeFinance() {
            return roleCodeList.stream().anyMatch(GlobalConsts.DCS_SME_FINANCE_ROLE_NAME::equalsIgnoreCase);
        }

        public boolean isSmeOperator() {
            return roleCodeList.stream().anyMatch(GlobalConsts.DCS_SME_OPERATOR_ROLE_NAME::equalsIgnoreCase);
        }

        public boolean isDt() {
            return roleCodeList.stream().anyMatch(GlobalConsts.DCS_DT_ROLE_NAME::equalsIgnoreCase);
        }

        public boolean isDhl() {
            return roleCodeList.stream().anyMatch(GlobalConsts.DCS_DHL_ROLE_NAME::equalsIgnoreCase);
        }

        @Override
        public String toString() {
            return "User{" +
                    "userCode='" + userCode + '\'' +
                    ", orgCode='" + orgCode + '\'' +
                    ", appCode='" + appCode + '\'' +
                    ", roleCodeList=" + roleCodeList +
                    '}';
        }
    }
}
