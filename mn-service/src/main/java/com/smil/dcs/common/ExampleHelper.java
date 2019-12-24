package com.smil.dcs.common;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.weekend.Fn;
import tk.mybatis.mapper.weekend.reflection.Reflections;

import java.util.Map;
import java.util.stream.Collectors;

public class ExampleHelper {

    public static <T> Example andConcatEqualTo(Example example, Class<T> type, Object values,Fn<T, Object>... fns){
        return andConcatEqualTo(example,type,values,GlobalConsts.UNDERLINE,fns);
    }

    public static <T> Example andConcatEqualTo(Example example, Class<T> type, Object values,String separate, Fn<T, Object>... fns){
        if(fns.length > 0){
            andCondition(example,concatCondition(type,separate,fns)+" =",values);
        }
        return example;
    }

    public static <T> Example andConcatIn(Example example, Class<T> type, Iterable values, Fn<T, Object>... fns){
        return andConcatIn(example,type,values,GlobalConsts.UNDERLINE,fns);
    }

    public static <T> Example andConcatIn(Example example, Class<T> type,Iterable values,String separate,Fn<T, Object> ... fns){
        if(fns.length > 0){
            andCondition(example,concatCondition(type,separate,fns)+" in",values);
        }
        return example;
    }

    private static <T> String concatCondition(Class<T> type,String separate,Fn<T, Object> ... fns){
        Map<String, EntityColumn> entityColumnMap = EntityHelper.getEntityTable(type).getPropertyMap();
        if(fns.length > 0){
            return "concat("+ StringUtils.join(Lists.newArrayList(fns).stream().map(fn -> entityColumnMap.get(Reflections.fnToFieldName(fn)).getColumn()).collect(Collectors.toList()),",'"+separate+"',")+")";
        }else{
            return StringUtils.EMPTY;
        }
    }

    private static void andCondition(Example example,String condition, Object value){
        example.and(example.createCriteria().andCondition(condition,value));
    }
}
