package com.smil.dcs.utils;

import com.smil.dcs.common.GlobalConsts;
import org.apache.commons.lang3.StringUtils;

public class KeyUtils {

    private KeyUtils() {
    }

    public static String getKey(String ... arr){
        return StringUtils.join(arr, GlobalConsts.UNDERLINE);
    }

}
