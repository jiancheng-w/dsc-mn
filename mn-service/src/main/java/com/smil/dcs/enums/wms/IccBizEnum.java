package com.smil.dcs.enums.wms;

public class IccBizEnum {

    /**
     * 库存控制等级类型
     */
    public enum StatsEnum {

        MOVEMENT("M"),
        VALUE("V");

        private String key;

        StatsEnum(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }

    }
}
