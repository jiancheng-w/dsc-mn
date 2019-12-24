package com.smil.dcs.enums;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

public enum FileType {

    XLS(".xls"),
    XLSX(".xlsx"),
    CSV(".csv"),
    PDF(".pdf"),
    DOC(".doc"),
    PNG(".png"),
    JPG(".jpg");

    private String filetype;

    FileType(String filetype) {
        this.filetype = filetype;
    }

    public String getFiletype() {
        return filetype;
    }

    public static FileType getFileType(String filetypeStr){
        if(StringUtils.isEmpty(filetypeStr)){
            return null;
        }
       for(FileType type : FileType.values()){
           if(type.getFiletype().equalsIgnoreCase(filetypeStr))
               return type;
       }
        return null;
    }

    public static Boolean isExists(FileType fileType){
        return Lists.newArrayList(FileType.values()).contains(fileType);
    }
}
