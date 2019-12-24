package com.smil.dcs.utils;

import com.csvreader.CsvWriter;
import com.esotericsoftware.minlog.Log;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.smil.dcs.annotation.CsvField;
import com.smil.dcs.converter.CsvFieldConverter;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CsvUtils {

    private static final Logger LOG = LoggerFactory.getLogger(CsvUtils.class);


    private static final String SPLITCHARACTER = ",";

    private static final char SPLITCHARACTERCHAR = ',';

    private CsvUtils() {
    }

    /**
     * 读取csv格式文件，返回对象列表
     *
     * @param inputStream
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> List<T> readCsv(InputStream inputStream,Class<T> tClass){
        return readCsv(inputStream,tClass,SPLITCHARACTER);
    }


    public static <T> List<T> readCsv(InputStream inputStream, Class<T> tClass, String splitCharacter, CsvFieldConverter ... csvFieldConverters){
        Scanner scanner = new Scanner(inputStream);
        List<T> list = new ArrayList<>();
        Map<Integer,String> fieldMap = fieldMap(tClass);
        while (scanner.hasNextLine()) {
            String content = scanner.nextLine();
            if(StringUtils.isNotEmpty(content)){
                list.add(toBean(content.trim(),splitCharacter,tClass,fieldMap,csvFieldConverters));
            }
        }
        scanner.close();
        return list;
    }

    private static <T> T toBean(String lineStr,String splitCharacter,Class<T> tClass,Map<Integer,String> fieldMap,CsvFieldConverter ... csvFieldConverters){
        if(StringUtils.isEmpty(lineStr)){
            return null;
        }
        Map<String,Object> beanMap = Maps.newHashMap();
        String[] fields = lineStr.split(splitCharacter);

        for(int i = 0 ; i < fields.length ; i ++){
            String fieldName = fieldMap.get(i);
            if(StringUtils.isNotEmpty(fieldName)){
                String val = fields[i];
                if(StringUtils.isNotEmpty(val)){
                    beanMap.put(fieldName,valConvert(i,fieldName,val,csvFieldConverters));
                }
            }
        }
        return mapToBean(beanMap,tClass);
    }

    private static Object valConvert(int index,String fieldName,Object val,CsvFieldConverter ... csvFieldConverters){
         if(csvFieldConverters != null && csvFieldConverters.length > 0){
             for(CsvFieldConverter csvFieldConverter : csvFieldConverters){
                 Object res = null;
                 if((res = csvFieldConverter.convert(index,fieldName,val)) != null){
                     return res;
                 }
             }
         }
        return val;
    }

    private static Map<Integer,String> fieldMap(Class<?> tClass){
        Map<Integer,String> fieldMap = Maps.newHashMap();
        Field[] fields = tClass.getDeclaredFields();
        if(fields != null && fields.length > 0){
            for(Field field : fields){
                CsvField csvField = field.getAnnotation(CsvField.class);
                if(csvField != null && !csvField.ignored()){
                    fieldMap.put(csvField.index(),field.getName());
                }
            }
        }
        return  fieldMap;
    }

    public static  <T> T mapToBean(Map<String,Object> beanMap,Class<T> tClass){
        try {
            T t = tClass.newInstance();
            BeanUtils.populate(t, beanMap);
            return t;
        } catch (IllegalAccessException |InvocationTargetException | InstantiationException e) {
            return null;
        }
    }

    /**
     *  根据传入的List对象，指定分隔符，生成csv格式文件，返回输入流 文件头待添加（暂未实现）
     * @param list
     * @param splitCharacter
     * @param csvFieldConverters
     * @param <T>
     * @return
     */
    public static <T> InputStream writeCsvToInputStream(List<T> list,char splitCharacter,CsvFieldConverter ... csvFieldConverters){
        return writeCsvToInputStream(listContent(list,Boolean.FALSE,csvFieldConverters),splitCharacter);
    }

    public static <T> InputStream writeCsvToInputStreamWithTitle(List<T> list,char splitCharacter,CsvFieldConverter ... csvFieldConverters){
        return writeCsvToInputStream(listContent(list,Boolean.TRUE,csvFieldConverters),splitCharacter);
    }

    public static <T> InputStream writeCsvToInputStream(List<T> list,CsvFieldConverter ... csvFieldConverters){
        return writeCsvToInputStream(list,SPLITCHARACTERCHAR,csvFieldConverters);
    }

    public static InputStream writeCsvToInputStream(List<String[]> list,char splitCharacter){
        return new ByteArrayInputStream(writeCsvToBytes(list,splitCharacter));
    }

    /**
     * 根据传入的List对象，指定分隔符，生成csv格式文件，返回字节数组 文件头待添加（暂未实现）
     * @param list
     * @param splitCharacter
     * @param csvFieldConverters
     * @param <T>
     * @return
     */
    public static <T> byte[] writeCsvToBytes(List<T> list,char splitCharacter,CsvFieldConverter ... csvFieldConverters){
        return writeCsvToBytes(listContent(list,Boolean.FALSE,csvFieldConverters),splitCharacter);
    }

    public static <T> byte[] writeCsvToBytesWithTitle(List<T> list,char splitCharacter,CsvFieldConverter ... csvFieldConverters){
        return writeCsvToBytes(listContent(list,Boolean.TRUE,csvFieldConverters),splitCharacter);
    }

    public static byte[] writeCsvToBytes(List<String[]> list,char splitCharacter){
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            CsvWriter csvWriter = new CsvWriter(bao,splitCharacter, StandardCharsets.UTF_8);
        try {
            list.forEach( e -> {
                try {
                    csvWriter.writeRecord(e);
                } catch (IOException e1) {
                    Log.info("IOException",e1);

                }
            });
            csvWriter.flush();
            return bao.toByteArray();
        } catch (Exception e) {
            Log.info("Exception",e);
        }finally {
            csvWriter.close();
        }
        return new byte[0];
    }

    private static <T> List<String[]> listContent(List<T> list,Boolean isContainTitle,CsvFieldConverter ... csvFieldConverters){
        List<String[]> contents = Lists.newArrayList();
        if(isContainTitle && CollectionUtils.isNotEmpty(list)){
            contents.add(titileContent(list.get(0)));
        }
        if(CollectionUtils.isEmpty(list)){
            return contents;
        }
        list.forEach(t -> contents.add(content(t,csvFieldConverters)));
        return contents;
    }

    public static <T> String[] titileContent(T t) {
        Map<Integer,CsvField> annocationMap = Maps.newLinkedHashMap();
        Field[] fields = t.getClass().getDeclaredFields();
        if(fields != null && fields.length > 0){
            for(Field field : fields){
                CsvField csvField = field.getAnnotation(CsvField.class);
                if(csvField != null && !csvField.ignored()){
                    annocationMap.put(csvField.index(),csvField);
                }
            }
        }

        List<Integer> indexes = annocationMap.keySet().stream().sorted().collect(Collectors.toList());
        String [] strArr = new String[indexes.size()];
        for(int i = 0 ; i < indexes.size() ; i ++ ){
            CsvField csvField = annocationMap.get(i);
            if(csvField != null && !csvField.ignored()){
                strArr[i] = csvField.fieldname();
            }
        }
        return strArr;
    }

    /**
     *  对象转换为String 数组，更具CsvFieId id 排序
     * @param t
     * @param csvFieldConverters
     * @param <T>
     * @return
     */
    public static <T> String[] content(T t,CsvFieldConverter ... csvFieldConverters){
        Map<Integer,Field> fieldMap = Maps.newLinkedHashMap();
        Field[] fields = t.getClass().getDeclaredFields();
        if(fields != null && fields.length > 0){
            for(Field field : fields){
                CsvField csvField = field.getAnnotation(CsvField.class);
                if(csvField != null && !csvField.ignored()){
                    fieldMap.put(csvField.index(),field);
                }
            }
        }

        List<Integer> indexes = fieldMap.keySet().stream().sorted().collect(Collectors.toList());
        String [] strArr = new String[indexes.size()];
        for(int i = 0 ; i < indexes.size() ; i ++ ){
            Field field = fieldMap.get(indexes.get(i));
            field.setAccessible(true);
            Object val = ReflectionUtils.getField(field,t);
            if(val == null || "null".equals(val)){
                val = "";
            }else{
                val = valConvert(i,field.getName(),val,csvFieldConverters);
            }
            strArr[i] = val + "";
        }
        return strArr;
    }

    /**
     * 生成CSV文件
     * @param file
     * @param list
     * @param splitCharacter
     * @param getTitle
     * @param csvFieldConverters
     * @param <T>
     */
    public static <T> void writeAndAppendToCsv(File file, List<T> list, Boolean getTitle, CsvFieldConverter... csvFieldConverters) {
        if(getTitle){
            writeAndAppendToCsv(file,listContent(list,Boolean.TRUE,csvFieldConverters),',',StandardCharsets.UTF_8);
        } else {
            writeAndAppendToCsv(file,listContent(list,Boolean.FALSE,csvFieldConverters),',',StandardCharsets.UTF_8);
        }

    }

    public static void writeAndAppendToCsv(File file, List<String[]> list, char delimiter, Charset charset) {
        CsvWriter cw = null;
        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), charset))){
            cw = new CsvWriter(out, delimiter);
            // 追加文件内容
            for (String[] e : list) {
                cw.writeRecord(e);
            }
            cw.flush();
        } catch (IOException e) {
            LOG.error("Exception:", e);
        }
    }

    public static <T> void writeCsvToBytesWithTitle(OutputStream outputStream,List<T> list,char splitCharacter,CsvFieldConverter ... csvFieldConverters){
        InputStream inputStream = writeCsvToInputStreamWithTitle(list,splitCharacter,csvFieldConverters);
        byte[] b = new byte[10240];
        try {
            int i = inputStream.read(b);
            while(i!=-1){
                outputStream.write(b,0,i);
                i = inputStream.read(b);
            }
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            LOG.info("",e);
        }
    }


}
