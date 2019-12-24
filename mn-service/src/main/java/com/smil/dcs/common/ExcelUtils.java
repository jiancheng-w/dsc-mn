package com.smil.dcs.common;

import com.aimilin.bean.ExcelResult;
import com.aimilin.bean.ExcelType;
import com.aimilin.converter.DictionaryConverter;
import com.aimilin.utils.BeanUtils;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Excel操作工具类
 *
 * @author duankk重写
 */
public class ExcelUtils {

	private ExcelUtils() {
		super();
	}

	/**
	 * 将ExcelResult 数据集合写入到输出流中
	 *
	 * @author duankk
	 * @param excelResult Excel数据结果集
	 * @param os 输出流
	 * @param excelType Excel 枚举类型
     * @param type 类型集合
	 */
	public static void write(ExcelResult excelResult, OutputStream os, ExcelType excelType, ExcelFormat type) {
		ExcelWriteUtils.write(excelResult, os, excelType, type);
	}

	/**
	 * 将ExcelResult 数据集合写入到输出流中
	 *
	 * @author duankk
	 * @param excelResult Excel数据结果集
	 * @param os 输出流
	 * @param excelType Excel 枚举类型
	 * @param io 模板流
	 * @param index 写入数据索引
	 *             ExcelResult excelResult, OutputStream os, ExcelType excelType, ExcelFormat type,
	 */
	public static void write(ExcelResult excelResult, OutputStream os, ExcelType excelType, ExcelFormat type, InputStream io, int index, String sheetName) {
		ExcelWriteUtils.write(excelResult, os, excelType,type,io,index,sheetName);
	}

	/**
	 * 将对象列表写入到输出流中,使用指定Excel格式，并可指定是否忽略转换异常
	 *
	 * @author duankk
	 * @param list 对象列表
	 * @param os 输出流
	 * @param excelType Excel文件类型
	 * @param type 类型集合
	 * @param ignoreException true 忽略转换异常，false 抛出转换异常
	 * @param <T> 任意对象类型
	 * @param converters 可选，参数类型转换器
	 */
	public static <T> void write(List<T> list, OutputStream os, ExcelType excelType, ExcelFormat type, boolean ignoreException,
                                 DictionaryConverter... converters) {
		ExcelResult excelResult = BeanUtils.toResult(list, ignoreException, converters);
		write(excelResult, os, excelType, type);
	}

	/**
	 * 将对象列表写入到输出流中,使用指定Excel格式，并可指定是否忽略转换异常
	 *
	 * @author duankk
	 * @param list 对象列表
	 * @param os 输出流
	 * @param excelType Excel文件类型
	 * @param ignoreException true 忽略转换异常，false 抛出转换异常
	 * @param <T> 任意对象类型
	 * @param io 模板流
	 * @index 数据写入角标
	 * @param converters 可选，参数类型转换器
	 */
	public static <T> void write(List<T> list, OutputStream os, ExcelType excelType, ExcelFormat type, InputStream io, int index, String sheetName, boolean ignoreException,
                                 DictionaryConverter... converters) {
		ExcelResult excelResult = BeanUtils.toResult(list, ignoreException, converters);
		write(excelResult, os, excelType,type,io,index,sheetName);
	}

	/**
	 * 将对象列表写入到输出流中,默认使用Excel2007格式，直接忽略异常信息
	 *
	 * @author duankk
	 * @param list 对象列表
	 * @param os 输出流
	 * @param type 类型集合
	 * @param <T> 任意对象类型
	 * @param converters 可选，参数类型转换器
	 */
	public static <T> void write(List<T> list, OutputStream os, ExcelFormat type, DictionaryConverter... converters) {
		write(list, os, ExcelType.XLSX, type,true, converters);
	}

	/**
	 * 将对象列表写入到输出流中,默认使用Excel2007格式，直接忽略异常信息
	 *
	 * @author duankk
	 * @param list 对象列表
	 * @param os 输出流
	 * @param type 类型集合
	 * @param <T> 任意对象类型
	 * @param converters 可选，参数类型转换器
	 */
	public static <T> void write(List<T> list, OutputStream os, ExcelFormat type, InputStream io, int index, String sheetName, DictionaryConverter... converters) {
		write(list, os, ExcelType.XLSX,type,io,index,sheetName,true, converters);
	}


}
