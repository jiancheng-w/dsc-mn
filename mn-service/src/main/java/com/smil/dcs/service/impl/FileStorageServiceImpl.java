package com.smil.dcs.service.impl;

import com.smil.dcs.common.FileSftpServer;
import com.smil.dcs.enums.FileType;
import com.smil.dcs.exception.DcsRuntimeException;
import com.smil.dcs.exception.McRuntimeException;
import com.smil.dcs.service.IFileStorageService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class FileStorageServiceImpl implements IFileStorageService {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final int DEFAULTDIRLENGTH = 1;

    private static final String SEPARATOR = "/";

    @Value("${sftp.file.publicRootPath:}")
    private String publicRootPath;

    @Value("${sftp.file.privateRootPath:}")
    private String privateRootPath;

    @Autowired
    private FileSftpServer fileSftpServer;

    @Override
    public String writePublicFile(InputStream inputStream, String fileSavePath, String fileName, FileType fileType) {
        return null;
    }

    @Override
    public String writePublicFile(InputStream inputStream, String fileSavePath, FileType fileType) {
        return null;
    }

    @Override
    public String writePrivateFile(InputStream inputStream, String fileSavePath, String fileName, FileType fileType) {
        return this.writeFile(inputStream,privateRootPath,fileSavePath,fileName,fileType);
    }

    private String writeFile(InputStream inputStream,String rootDir, String fileSavePath, String fileName, FileType fileType) {
        this.validateWrite(inputStream,rootDir,fileType);
        byte[] data = null;
        try {
            data = StreamUtils.copyToByteArray(inputStream);
            inputStream.close();
            inputStream = new ByteArrayInputStream(data);
        } catch (IOException e) {
            throw new McRuntimeException("data is null.");
        }
        String md5Str = this.inputToMd5Str(data);
        fileSavePath = this.getDir(fileSavePath,md5Str);
        fileName = this.getFileName(fileName,md5Str);
        String remotePath = this.getRemotePath(fileSavePath,fileName,fileType);
        String remoteFilePath = this.getRemoteRootDirPath(rootDir,remotePath);
        if(!fileSftpServer.sftpUpload(remoteFilePath,inputStream)){
            throw new McRuntimeException("SFTP file upload failed.");
        }
        return remotePath;
    }

    @Override
    public String writePrivateFile(InputStream inputStream, String fileSavePath, FileType fileType) {
        return this.writePrivateFile(inputStream,fileSavePath,null,fileType);
    }

    @Override
    public String writePrivateFile(InputStream inputStream, FileType fileType) {
        return this.writePrivateFile(inputStream,null,null,fileType);
    }

    @Override
    public List<String> getRemotePrivateFilenameList(String remotePath) {
        return this.getRemotePrivateFilenameList(remotePath,null);
    }

    @Override
    public List<String> getRemotePrivateFilenameList(String remotePath, String pattern) {
        if(StringUtils.isEmpty(remotePath)){
            throw new DcsRuntimeException("The remote folder path is empty, please check.");
        }
        return fileSftpServer.getFileList(this.getRemoteRootDirPath(privateRootPath,remotePath),pattern);
    }

    @Override
    public boolean movePrivateFile(String srouce, String target) {
        if(StringUtils.isEmpty(srouce)){
            throw new DcsRuntimeException("The remote srouce folder path is empty, please check.");
        }
        if(StringUtils.isEmpty(target)){
            throw new DcsRuntimeException("The remote target folder path is empty, please check.");
        }
        return fileSftpServer.moveFile(this.getRemoteRootDirPath(privateRootPath,srouce),this.getRemoteRootDirPath(privateRootPath,target));
    }

    @Override
    public boolean deletePrivateFile(String filePath) {
        if(StringUtils.isEmpty(filePath)){
            throw new DcsRuntimeException("The remote filepath is empty, please check.");
        }
        return fileSftpServer.rmFile(this.getRemoteRootDirPath(privateRootPath,filePath));
    }

    @Override
    public boolean deletePrivateFileList(List<String> filePaths) {
        if(CollectionUtils.isEmpty(filePaths)){
            throw new DcsRuntimeException("The remote filepath list is empty, please check.");
        }
        return fileSftpServer.rmFile(filePaths);
    }

    @Override
    public boolean isExistsPrivateFile(String fileName) {
        if(StringUtils.isEmpty(fileName)){
            throw new DcsRuntimeException("The remote fileName is empty, please check.");
        }
        try {
            return CollectionUtils.isNotEmpty(this.getRemotePrivateFilenameList(this.getFileDir(fileName),this.getFileName(fileName)));
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    @Override
    public byte[] readPrivateFile(String filePath) {
        return this.readFile(privateRootPath,filePath);
    }

    private byte[] readFile(String rootDir,String filePath){
        this.validateRead(rootDir,filePath);
        return fileSftpServer.sftpDownLoad(this.getRemoteRootDirPath(rootDir,filePath));
    }

    private void validateRead(String rootDir,String dir){
        if(StringUtils.isEmpty(rootDir)){
            throw new DcsRuntimeException("The remote folder path is empty, please check.");
        }
        if(StringUtils.isEmpty(dir)){
            throw new DcsRuntimeException("The file name is empty, please check.");
        }
    }

    private void validateWrite(InputStream inputStream,String rootDir,FileType fileType){
        if(inputStream == null){
            throw new DcsRuntimeException("The file input stream is empty, please check.");
        }
        if(StringUtils.isEmpty(rootDir)){
            throw new DcsRuntimeException("The remote root directory does not exist, please check.");
        }
        if(fileType == null){
            throw new DcsRuntimeException("The file type is empty, please check.");
        }
        if(!FileType.isExists(fileType)){
            throw new DcsRuntimeException("File type not supported, please check.");
        }
    }


    private String inputToMd5Str(byte[] data){
        return DigestUtils.md5Hex(data);
    }

    private String writeDirPath(String md5Str){
        return md5Str.substring(0,DEFAULTDIRLENGTH);
    }

    private String getDir(String fileSavePath,String md5Name){
        return StringUtils.isEmpty(fileSavePath) ? this.writeDirPath(md5Name) : fileSavePath;
    }

    private String getFileName(String fileName,String md5Name){
        return StringUtils.isEmpty(fileName) ? md5Name : fileName;
    }

    private String getRemoteRootDirPath(String rooot,String getRemotePath){
        return rooot+SEPARATOR+getRemotePath;
    }

    private String getRemotePath(String dir,String fileName,FileType fileType){
        return dir+SEPARATOR+fileName+fileType.getFiletype();
    }

    private String getFileDir(String filePath){
        return filePath.substring(0,filePath.lastIndexOf(SEPARATOR));
    }

    private String getFileName(String filePath){
        return filePath.substring(filePath.lastIndexOf(SEPARATOR)+1);
    }

    //@PostConstruct
    private void setPrivateRootPath(){
        if(StringUtils.isEmpty(privateRootPath) || StringUtils.isEmpty(publicRootPath)){
            String userHome = fileSftpServer.userDir();
            this.privateRootPath = userHome;
            this.publicRootPath = userHome;
        }
        LOGGER.info("sftp root address is :" + privateRootPath);
    }

}
