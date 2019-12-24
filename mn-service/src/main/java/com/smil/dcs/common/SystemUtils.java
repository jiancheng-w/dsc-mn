package com.smil.dcs.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class SystemUtils {
    @Autowired
    Environment env;

    public boolean isDevEnvironment() {
        for (String s : env.getActiveProfiles()) {
            if (s.equals(GlobalConsts.DEV_ENVIRONMENT_PROFILE_NAME)) return true;
        }
        return false;
    }
}
