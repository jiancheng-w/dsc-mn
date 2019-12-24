package com.smil.dcs.enums;


import org.apache.poi.hssf.usermodel.HSSFCell;

/**
 *
 * excel 枚举
 * @author wjh
 *
 * */
public class ExcelUtilsEnums {

    /** excelType enum */
     public enum ExcelCellTypeEnum{

         //文本
        TEXT(1,"@",HSSFCell.CELL_TYPE_STRING),
        //数字-整数
        NUMBER_INT(2,"0",HSSFCell.CELL_TYPE_NUMERIC),
        //数字-小数(2位)
        NUMBER_DOUBLE(3,"0.00",HSSFCell.CELL_TYPE_NUMERIC),
        //时间
        TIME_GENERAL(4,"yyyy-MM-dd hh:mm:ss",HSSFCell.CELL_TYPE_NUMERIC),
        //时间-hms
        TIME_HMS(5,"hh:mm:ss",HSSFCell.CELL_TYPE_NUMERIC),
        //日期-日月年
        DATE_DMY(6,"dd-MM-yyyy",HSSFCell.CELL_TYPE_NUMERIC),
        //日期-年月日
        DATE_YMD(7,"yyyy-MM-dd",HSSFCell.CELL_TYPE_NUMERIC),
        //货币-两位
        CURRENCY_TWO(8,"#,##0.00",HSSFCell.CELL_TYPE_NUMERIC),
        //货币-整数
        CURRENCY(9,"#,#0",HSSFCell.CELL_TYPE_NUMERIC),
        //数字-小数(4位)
        NUMBER_QUADRUPLE(10,"0.0000",HSSFCell.CELL_TYPE_NUMERIC);

        private int index;
        private String code;
        private int type;

        ExcelCellTypeEnum(int index, String code, int type) {
            this.index = index;
            this.code = code;
            this.type = type;
        }

        public int getIndex() {
            return index;
        }
        public String getCode() {
            return code;
        }
        public int getType() {
            return type;
        }

        /** 根据id返回枚举 */
        public static ExcelCellTypeEnum getEnum(int index) {
            ExcelCellTypeEnum [] values = ExcelCellTypeEnum.values();
            for (ExcelCellTypeEnum excelEnum : values) {
                if (excelEnum.getIndex() == index) {
                    return excelEnum;
                }
            }
            return null;
        }

    }

}

