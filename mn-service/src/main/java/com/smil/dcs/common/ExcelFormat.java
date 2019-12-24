package com.smil.dcs.common;

import org.apache.poi.ss.usermodel.Workbook;

import java.util.Map;

public interface ExcelFormat {

    Map<Integer, Integer> getCellTypeMap(Workbook wb);
}
