package com.smil.dcs.converter;

@FunctionalInterface
public interface CsvFieldConverter {

    /**
     * 特殊类型自定义转换
     * @param index
     * @param fieldName
     * @param val
     * @return
     */
    Object convert(int index, String fieldName, Object val);
}
