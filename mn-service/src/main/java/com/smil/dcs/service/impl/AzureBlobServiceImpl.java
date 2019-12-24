package com.smil.dcs.service.impl;

import com.microsoft.azure.storage.blob.models.PublicAccessType;
import com.smil.dcs.common.AzureBlobService;
import com.smil.dcs.exception.DcsRuntimeException;
import com.smil.dcs.service.IAzureBlobService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class AzureBlobServiceImpl implements IAzureBlobService {

    private static final int DEFAULTDIRLENGTH = 3;

    private static final String CONTAINERSTR = "cn";

    @Autowired
    private AzureBlobService azureBlobService;

    @Override
    public String uploadPublicFile(File file) {
        return this.uploadPublicFile(file,null,Boolean.FALSE);
    }

    @Override
    public String uploadPublicFileSourceName(File file) {
        return this.uploadPublicFile(file,null,Boolean.TRUE);
    }

    @Override
    public String uploadPublicFile(File file, String container) {
        return this.uploadPublicFile(file,container,Boolean.FALSE);
    }

    @Override
    public String uploadPublicFileSourceName(File file, String container) {
        return this.uploadPublicFile(file,container,Boolean.TRUE);
    }

    @Override
    public void deletePublicFile(String fileUrl) {
         azureBlobService.deleteBlob(fileUrl);
    }

    private String uploadPublicFile(File file, String container, Boolean isSourceName) {
        return this.upload(file,container,isSourceName,PublicAccessType.BLOB);
    }

    public String upload(File file, String container,Boolean isSourceName,PublicAccessType type) {
        String md5 = this.getMd5(file);
        String fileName = isSourceName ? file.getName() : getMd5FileName(file.getName(),md5);
        container = StringUtils.isEmpty(container) ? getContainer(md5) : container;
        return azureBlobService.uploadBlob(file,container,fileName,type);
    }

    private String getMd5FileName(String fileName,String md5){
        return md5+fileName.substring(fileName.lastIndexOf("."));
    }

    private String getMd5(File file){
        try {
            return DigestUtils.md5Hex(FileUtils.readFileToByteArray(file));
        } catch (IOException e) {
            throw new DcsRuntimeException("getMd5 IOEXCEPTION ",e);
        }
    }

    private String getContainer(String md5){
        return CONTAINERSTR+md5.substring(0,DEFAULTDIRLENGTH);
    }


}
