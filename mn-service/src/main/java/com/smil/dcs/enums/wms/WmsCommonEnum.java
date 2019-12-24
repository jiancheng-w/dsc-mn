package com.smil.dcs.enums.wms;

public interface WmsCommonEnum {

    //此处对应枚举的字段,如状态枚举StatusEnum定义了code,name,desc
    //那么这里定义这个三个字段的get方法,可以获取到所有的字段
    Byte getCode();

    String getName();

    String getDesc();

}
