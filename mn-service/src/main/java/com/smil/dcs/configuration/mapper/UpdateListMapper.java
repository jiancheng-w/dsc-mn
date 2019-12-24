package com.smil.dcs.configuration.mapper;

import org.apache.ibatis.annotations.UpdateProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

import java.util.List;

@RegisterMapper
public interface UpdateListMapper<T> {

    /**
     *  伪批量更新方法
     * @param recordList
     * @return
     */
    @UpdateProvider(type = MapperProvider.class, method = "dynamicSQL")
    int updateList(List<? extends T> recordList);

    /**
     *  伪批量更新方法(空值不更新)
     * @param recordList
     * @return
     */
    @UpdateProvider(type = MapperProvider.class, method = "dynamicSQL")
    int updateListSelective(List<? extends T> recordList);
}
