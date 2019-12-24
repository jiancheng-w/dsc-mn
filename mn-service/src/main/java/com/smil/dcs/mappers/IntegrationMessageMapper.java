package com.smil.dcs.mappers;

import com.smil.dcs.model.IntegrationMessage;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

@Component
public interface IntegrationMessageMapper extends Mapper<IntegrationMessage> {
}