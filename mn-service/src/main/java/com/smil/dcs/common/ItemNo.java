package com.smil.dcs.common;

import org.apache.commons.lang3.StringUtils;

public class ItemNo {

    private int index;

    public ItemNo(int index) {
        this.index = index;
    }

    public String get() {
        String itemNo = StringUtils.leftPad(index + "", GlobalConsts.ITEM_LENGTH, "0");
        index++;
        return itemNo;
    }
}
