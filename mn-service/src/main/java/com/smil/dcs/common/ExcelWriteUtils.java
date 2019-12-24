package com.smil.dcs.common;

import com.aimilin.bean.ExcelResult;
import com.aimilin.bean.ExcelRow;
import com.aimilin.bean.ExcelSheet;
import com.aimilin.bean.ExcelType;
import com.aimilin.exception.ExcelReadException;
import com.aimilin.exception.ExcelWriteException;
import com.aimilin.utils.ListUtils;
import com.smil.dcs.enums.ExcelUtilsEnums;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Excel写入工具类
 *
 * @author duankk
 */
public class ExcelWriteUtils {

	private ExcelWriteUtils() {
		super();
	}

	/**
	 * 根据Excel文件地址 获取Excel工作薄对象
	 *
	 * @param filePath excel文件地址
	 * @return Workbook Excel工作博
	 * @throws ExcelReadException - 从FilePath中读取失败，则抛出该异常，或者文件未找到等
	 */
	public static Workbook getWorkbook(String filePath) {
		Objects.requireNonNull(filePath, "Excel file path must not be NULL !");
		try {
			return WorkbookFactory.create(new File(filePath));
		} catch (Exception e) {
			throw new ExcelReadException(e.getMessage(), e);
		}
	}

	/**
	 * 根据Excel文件输入流 ，获取Excel工作薄对象
	 *
	 * @param is 输入流
	 * @return Workbook Excel工作博
	 * @throws ExcelReadException - 从Input中读取失败，则抛出该异常
	 */
	public static Workbook getWorkbook(InputStream is) {
		Objects.requireNonNull(is, "Excel InputStream must not be NULL !");
		try {
			return WorkbookFactory.create(is);
		} catch (Exception e) {
			throw new ExcelReadException(e.getMessage());
		}
	}


	/**
	 * 根据Excel 类型生成不同的WorkBook
	 *
	 * @author duankk
	 * @param excelType ExcelType 枚举类型
	 * @return Workbook Excel工作簿
	 */
	public static Workbook getWorkbook(ExcelType excelType) {
		Objects.requireNonNull(excelType, "excelType must not be NULL !");
		if (ExcelType.XLS == excelType) {
			return new HSSFWorkbook();
		}
		return new XSSFWorkbook();
	}

	/**
	 * 将Excel结果写入到指定的输出流中（重写）
	 *
	 * @author duankk
	 * @param excelResult Excel结果集
	 * @param os 输出流
	 * @param excelType Excel类型，如果格式为Excel2003格式，并且记录总行数超过了65535，则将结果集拆分多个Sheet，名称和原名称一样,多了索引
	 * @param type 根据列下标设置该列的单元格类型
	 */
	public static void write(ExcelResult excelResult, OutputStream os, ExcelType excelType, ExcelFormat type) {
		Objects.requireNonNull(excelResult);
		Objects.requireNonNull(os);
		List<ExcelSheet> sheets = excelResult.getSheetList();
		if (CollectionUtils.isEmpty(sheets)) {
			throw new ExcelWriteException("excelResult 结果对象中必须包含 ExcelSheet对象！");
		}

		Workbook wb = null;
		try {
			wb = getWorkbook(excelType);
			Map<Integer, Integer> typeMap = type.getCellTypeMap(wb);

			for (int i = 0; i < sheets.size(); i++) {
				ExcelSheet exlSheet = sheets.get(i);
				String sheetName = exlSheet.getName();
				if (sheetName == null || "".equals(sheetName)) {
					sheetName = "sheet" + i;
				}
				List<ExcelRow> exlRows = exlSheet.getRowList();
				// 将Excel2003格式数据拆分成多个Sheet
				if (ExcelType.XLS == excelType) {
					List<List<ExcelRow>> exlRowList = ListUtils.split(exlRows, 65535);
					for (int j = 0; j < exlRowList.size(); j++) {
						creatSheet(wb, sheetName + "_" + j, exlRowList.get(j), exlSheet.getHeadRow(),typeMap);
					}
				} else {
					creatSheet(wb, sheetName, exlRows, exlSheet.getHeadRow(),typeMap);
				}
			}
			wb.write(os);
		} catch (Exception e) {
			throw new ExcelWriteException(e);
		} finally {
			try {
				if (os != null) {
					os.close();
				}
			} catch (IOException e) {
			}
			try {
				if (wb != null) {
					wb.close();
				}
			} catch (IOException e) {
			}
		}
	}


	/**
	 * 将Excel结果写入到指定的输出流中（重写）
	 *
	 * @author duankk
	 * @param excelResult Excel结果集
	 * @param os 输出流
	 * @param excelType Excel类型，如果格式为Excel2003格式，并且记录总行数超过了65535，则将结果集拆分多个Sheet，名称和原名称一样,多了索引
	 * @param type 根据列下标设置该列的单元格类型
	 * @param wb 单元薄
	 * @param index 单元薄写数据的索引
	 */
	public static void write(ExcelResult excelResult, OutputStream os, ExcelType excelType, ExcelFormat type, InputStream io, int index, String sheetName) {
		Objects.requireNonNull(excelResult);
		Objects.requireNonNull(os);
		List<ExcelSheet> sheets = excelResult.getSheetList();
		if (CollectionUtils.isEmpty(sheets)) {
			throw new ExcelWriteException("excelResult 结果对象中必须包含 ExcelSheet对象！");
		}
		Workbook wb = null;
		try {
			wb = getWorkbook(io);
			Map<Integer, Integer> typeMap = type.getCellTypeMap(wb);
			for (int i = 0; i < sheets.size(); i++) {
				ExcelSheet exlSheet = sheets.get(i);
				if(StringUtils.isBlank(sheetName)){
					sheetName = exlSheet.getName();
				}
				if (sheetName == null || "".equals(sheetName)) {
					sheetName = "sheet" + i;
				}
				List<ExcelRow> exlRows = exlSheet.getRowList();
				// 将Excel2003格式数据拆分成多个Sheet
				if (ExcelType.XLS == excelType) {
					List<List<ExcelRow>> exlRowList = ListUtils.split(exlRows, 65535);
					for (int j = 0; j < exlRowList.size(); j++) {
						creatSheet(wb, sheetName + "_" + j, typeMap,exlRowList.get(j), index);
					}
				} else {
					creatSheet(wb, sheetName,typeMap, exlRows,index);
				}
			}
			wb.write(os);
		} catch (Exception e) {
			throw new ExcelWriteException(e);
		} finally {
			try {
				if (os != null) {
					os.close();
				}
			} catch (IOException e) {
			}
			try {
				if (wb != null) {
					wb.close();
				}
			} catch (IOException e) {
			}
		}
	}

	private static void creatSheet(Workbook wb, String sheetName, List<ExcelRow> exlRows, ExcelRow headRow, Map<Integer, Integer> typeMap) {
		Sheet sheet = wb.createSheet(sheetName);
		// 设置表头
		int startRow = 0;
		if (headRow != null && headRow.getCellList() != null) {
			setValue(wb, sheet.createRow(0), headRow,null,null);
			startRow = 1;
		}
		//获取每列样式
		Map<Integer,CellStyle> cellAtypeMap = getCellValueTypeMap(wb,typeMap);
		// 设置每行值
		for (int i = 0; i < exlRows.size(); i++) {
			Row row = sheet.createRow(i + startRow);
			setValue(wb, row, exlRows.get(i), cellAtypeMap,typeMap);
		}
	}

	private static void creatSheet(Workbook wb, String sheetName, Map<Integer, Integer> typeMap ,List<ExcelRow> exlRows,int index) {
		Sheet sheet = wb.getSheet(sheetName);
		int startRow = index;
		//获取每列样式
		Map<Integer,CellStyle> cellAtypeMap = getCellValueTypeMap(wb,typeMap);
		// 设置每行值
		for (int i = 0; i < exlRows.size(); i++) {
			Row row = sheet.createRow(i + startRow);
			setValue(wb, row, exlRows.get(i),cellAtypeMap,typeMap);
		}
	}

	//设置Excel行数据
	private static void setValue(Workbook wb, Row row, ExcelRow excelRow, Map<Integer, CellStyle> cellTypeMap,Map<Integer, Integer> typeMap) {
		if (excelRow == null) {
			return;
		}
		List<String> cells = excelRow.getCellList();
		if (cells == null || cells.isEmpty()) {
			return;
		}
		for (int i = 0; i < cells.size(); i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(cells.get(i));
			//判断是否有特殊样式待处理
			if(typeMap != null && typeMap.containsKey(i)){
				setCellValue(wb,cell,cells.get(i),cellTypeMap.get(i),typeMap.get(i));
			}
		}
	}
	//设置Excel行数据
	private static void setValue(Workbook wb, Row row, ExcelRow excelRow) {
		if (excelRow == null) {
			return;
		}
		List<String> cells = excelRow.getCellList();
		if (cells == null || cells.isEmpty()) {
			return;
		}
		for (int i = 0; i < cells.size(); i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(cells.get(i));
		}
	}

	//根据格式设置Excel行数据
	private static void setCellValue(Workbook wb, Cell cell, String cellValue, CellStyle style,Integer cellTypeIndex) {

		ExcelUtilsEnums.ExcelCellTypeEnum anEnum = ExcelUtilsEnums.ExcelCellTypeEnum.getEnum(cellTypeIndex);
		//时间格式
		if(cellTypeIndex == ExcelUtilsEnums.ExcelCellTypeEnum.TIME_GENERAL.getIndex()
				|| cellTypeIndex == ExcelUtilsEnums.ExcelCellTypeEnum.TIME_HMS.getIndex()
				|| cellTypeIndex == ExcelUtilsEnums.ExcelCellTypeEnum.DATE_DMY.getIndex()
				|| cellTypeIndex == ExcelUtilsEnums.ExcelCellTypeEnum.DATE_YMD.getIndex()){
			String formatStr = anEnum.getCode();
			SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
			try {
				if(StringUtils.isNotBlank(cellValue)){
					cell.setCellValue(sdf.parse(cellValue));
				}
			} catch (ParseException e) {
				throw new RuntimeException("EXCEL DOWNLOAD EXCEPTION :",e);
			}
		}else if(cellTypeIndex == ExcelUtilsEnums.ExcelCellTypeEnum.NUMBER_INT.getIndex() || cellTypeIndex == ExcelUtilsEnums.ExcelCellTypeEnum.NUMBER_DOUBLE.getIndex()
			|| cellTypeIndex == ExcelUtilsEnums.ExcelCellTypeEnum.NUMBER_QUADRUPLE.getIndex()){
			//数字格式
			if(StringUtils.isNotBlank(cellValue)){
				cell.setCellValue(Double.parseDouble((cellValue).trim()));
			}
		}
		cell.setCellStyle(style);
		cell.setCellType(anEnum.getType());
	}


	//根据格式设置Excel数据类型
	private static Map<Integer,CellStyle> getCellValueTypeMap(Workbook wb, Map<Integer, Integer> typeMap ) {

		Map<Integer,CellStyle> result = new HashMap<>();
		if(MapUtils.isEmpty(typeMap)){
			return result;
		}
		for(Map.Entry<Integer, Integer>  entry : typeMap.entrySet()){
			if(entry.getKey() == null || entry.getValue() == null){
				continue;
			}
			//dataFormat.getBuiltinFormat
			ExcelUtilsEnums.ExcelCellTypeEnum styleEnum = ExcelUtilsEnums.ExcelCellTypeEnum.getEnum(entry.getValue());
			CellStyle style = wb.createCellStyle();
			DataFormat dataFormat = wb.getCreationHelper().createDataFormat();
			String formatStr = styleEnum.getCode();
			if(StringUtils.isNotBlank(formatStr)){
				style.setDataFormat(dataFormat.getFormat(formatStr));
			}
			result.put(entry.getKey(),style);
		}
		return result;
	}



	private static String getDateFormat(String value){
		if(value.indexOf("-") == 4){
			return "yyyy-MM-dd";
		}
		return "dd-MM-yyyy";
	}

	/**
	 * 将Excel结果写入到字节输出流中（重写）
	 *
	 * @author duankk
	 * @param excelResult Excel结果集
	 * @param excelType Excel类型
	 * @param create 设置样式
	 * @return Excel数据字节流
	 */
	public static byte[] write(ExcelResult excelResult, ExcelType excelType, ExcelFormat create) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			write(excelResult, baos, excelType, create);
			return baos.toByteArray();
		} catch (Exception e) {
			throw new ExcelWriteException(e.getMessage());
		}
	}
}
