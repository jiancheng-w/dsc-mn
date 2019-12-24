package com.smil.dcs.enums.wms;

/**
 * 枚举工具类
 */
public class WmsEnumUtil {

    private WmsEnumUtil() {
    }

    /**
     * 返回指定编码的'枚举'
     * @param code
     * @return SharedObjTypeEnum
     * @throws
     */
    public static <T extends WmsCommonEnum> T getEnumBycode(Class<T> clazz, Byte code) {
        for(T _enum : clazz.getEnumConstants())
            if(_enum.getCode().equals(code))
                return _enum;
        return null;
    }

    /**
     * 返回指定名称的'枚举'
     * @param name
     * @return SharedObjTypeEnum
     * @throws
     */
    public static <T extends WmsCommonEnum> T getEnumByName(Class<T> clazz, String name) {
        for(T _enum : clazz.getEnumConstants())
            if(_enum.getName().equals(name))
                return _enum;
        return null;
    }

    /**
     * 返回指定描述的'枚举'
     * @param desc
     * @return SharedObjTypeEnum
     * @throws
     */
    public static <T extends WmsCommonEnum> T getEnumByDesc(Class<T> clazz, String desc) {
        for(T _enum : clazz.getEnumConstants())
            if(_enum.getDesc().equals(desc))
                return _enum;
        return null;
    }
}
