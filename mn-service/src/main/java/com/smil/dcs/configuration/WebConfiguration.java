package com.smil.dcs.configuration;

import com.smil.dcs.interceptors.UserInfoInterceptor;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    /** 
     * {@inheritDoc}
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 调整StringHttpMessageConverter和Jackson2HttpMessageConverter的顺序
        List<HttpMessageConverter<?>> stringHttpMessageConverterList = new ArrayList<>();
        List<HttpMessageConverter<?>> jackson2HttpMessageConverterList = new ArrayList<>();
        Iterator<?> iterator = converters.iterator(); 
        while (iterator.hasNext()) {  
            HttpMessageConverter<?> httpMessageConverter = (HttpMessageConverter<?>) iterator.next(); 
            if (httpMessageConverter instanceof StringHttpMessageConverter) {
                stringHttpMessageConverterList.add(httpMessageConverter);
                iterator.remove();
            }
            if (httpMessageConverter instanceof MappingJackson2HttpMessageConverter) {
                jackson2HttpMessageConverterList.add(httpMessageConverter);
                iterator.remove();
            }
        } 
        converters.addAll(NumberUtils.INTEGER_ZERO, jackson2HttpMessageConverterList);
        converters.addAll(stringHttpMessageConverterList);
    }

    @Bean
    UserInfoInterceptor userInfoInterceptor() {
        return new UserInfoInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInfoInterceptor());
    }
}
