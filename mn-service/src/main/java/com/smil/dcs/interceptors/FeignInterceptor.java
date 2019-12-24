package com.smil.dcs.interceptors;

import com.smil.dcs.common.GlobalConsts;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

@Component
public class FeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        //todo need to refactor validation
        requestTemplate.header("orgcode", GlobalConsts.SME_ORG_CODE);
        requestTemplate.header("ucode", "Feign");
        requestTemplate.header("appcode", GlobalConsts.SME_APP_CODE);
    }
}