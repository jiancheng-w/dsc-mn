package com.smil.dcs.utils;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

/**
 * FTP 上传相关工具
 * 简单实现了FTP的上传、下载，删除和显示文件
 * <h3>由于FTP的不稳定性，工具中大部分方法使用重新连接再重试的策略提高成功率</h3>
 *
 * @Author liuchuliang
 */
@Component
public class FtpUtils {
    private static final Logger logger = LoggerFactory.getLogger(com.smil.commons.utils.FtpUtils.class);

    @Value("${ftp.host:}")
    private String ftpHost;
    @Value("${ftp.username:}")
    private String ftpUsername;
    @Value("${ftp.password}")
    private String ftpPassword;
    @Value("${ftp.timeout:}")
    private int timeout = 5000;
    private int maxTryTimes = 3;
    private boolean isPassive = true;

    public FtpUtils() {}
    /**
     * 构造函数
     * 默认为被动模式连接
     * @param ftpHost ftp host
     * @param ftpUsername ftp username
     * @param ftpPassword ftp password
     */
    public FtpUtils(String ftpHost, String ftpUsername, String ftpPassword) {
        this(ftpHost, ftpUsername, ftpPassword, 5000, 3, true);
    }

    public FtpUtils(String ftpHost, String ftpUsername, String ftpPassword, int timeout, int maxTryTimes, boolean isPassive) {
        this.ftpHost = ftpHost;
        this.ftpUsername = ftpUsername;
        this.ftpPassword = ftpPassword;
        this.timeout = timeout;
        this.maxTryTimes = maxTryTimes;
        this.isPassive = isPassive;
    }

    /**
     * 下载远程路径文件
     * @param remoteFilePath 远程文件路径
     * @param localFile 本地文件
     * @return 成功true，失败false
     * @throws IOException 远程连接或本地文件写入出错抛出异常
     */
    public boolean downloadFile(String remoteFilePath, File localFile) throws IOException {
        if (remoteFilePath == null || localFile == null) {
            throw new IllegalArgumentException("remote file path is null or local file is null");
        }
        int tryTimes = 0;
        boolean retrieveResult = false;

        do {
            FTPClient ftpClient = createClient();

            try (FileOutputStream outputStream = new FileOutputStream(localFile)) {
                setMode(ftpClient);
                retrieveResult = ftpClient.retrieveFile(remoteFilePath, outputStream);
            } catch (SocketTimeoutException ste) {
                logger.warn(ste.getMessage(), ste.getCause());
            } catch (Exception e) {
                logger.error(e.getMessage(), e.getCause());
            } finally {
                ftpClient.disconnect();
            }
        } while (++tryTimes < maxTryTimes && !retrieveResult);

        return retrieveResult;
    }

    /**
     * 下载远程目录下的所有文件到本地指定目录中
     * @param remoteDirPath 远程目录路径
     * @param localDir 本地目录路径
     * @return 返回成功下载的文件
     * @throws IOException 远程连接或本地目录读写出错抛出异常
     */
    public List<File> downloadFilesInDirectory(String remoteDirPath, File localDir) throws IOException {
        if (remoteDirPath == null || localDir == null) {
            throw new IllegalArgumentException("remote dir path is null or local dir is null");
        }
        if (!localDir.exists() || !localDir.isDirectory()) {
            throw new IllegalArgumentException("local dir not exist or dir is not a directory, details: "
                    + localDir.getAbsolutePath());
        }
        FTPFile[] files = getFiles(remoteDirPath);
        List<File> downloadFiles = new ArrayList<>(files.length);
        for (FTPFile ftpFile : files) {
            if (ftpFile.isDirectory()) continue;

            File newFile = new File(localDir.getAbsolutePath() + "/" +  ftpFile.getName());
            boolean result = downloadFile(remoteDirPath + "/" + ftpFile.getName(), newFile);

            if (result) downloadFiles.add(newFile);
        }

        return downloadFiles;
    }



    /**
     * 下载远程目录下的指定前缀文件到本地指定目录中
     * @param remoteDirPath 远程目录路径
     * @param localDir 本地目录路径
     * @return 返回成功下载的文件
     * @throws IOException 远程连接或本地目录读写出错抛出异常
     */
    public List<File> downloadSrFilesInDirectory(String remoteDirPath,String srefix, File localDir) throws IOException {
        if (remoteDirPath == null || localDir == null) {
            throw new IllegalArgumentException("remote dir path is null or local dir is null");
        }
        if (!localDir.exists() || !localDir.isDirectory()) {
            throw new IllegalArgumentException("local dir not exist or dir is not a directory, details: "
                    + localDir.getAbsolutePath());
        }
        FTPFile[] files = getFiles(remoteDirPath);

        List<File> downloadFiles = new ArrayList<>(files.length);
        for (FTPFile ftpFile : files) {
            if (ftpFile.isDirectory()) continue;
            if(ftpFile.getName().startsWith(srefix)) {
                File newFile = new File(localDir.getAbsolutePath()+"/"+ftpFile.getName());
                boolean result = downloadFile(remoteDirPath + ftpFile.getName(), newFile);
                if (result) downloadFiles.add(newFile);
            }

        }

        return downloadFiles;
    }




    /**
     * 上传文件
     * @param remoteFilePath 远程目标文件路径（含文件名）
     * @param localFile 本地文件
     * @return 成功true 失败false
     * @throws IOException 远程连接或本地文件读取出错抛出异常
     */
    public boolean uploadFile(String remoteFilePath, File localFile) throws IOException {
        if (remoteFilePath == null || localFile == null) {
            throw new IllegalArgumentException("remote file path is null or local file is null");
        }
        int tryTimes = 0;
        boolean uploadResult = false;

        do {
            FTPClient ftpClient = createClient();

            try (FileInputStream inputStream = new FileInputStream(localFile)) {
                uploadResult = uploadFile(remoteFilePath, inputStream);
            } catch (SocketTimeoutException ste) {
                logger.warn(ste.getMessage(), ste.getCause());
            } catch (Exception e) {
                logger.error(e.getMessage(), e.getCause());
            } finally {
                ftpClient.disconnect();
            }
        } while (++tryTimes < maxTryTimes && !uploadResult);

        return uploadResult;
    }

    /**
     * 通过inputStream上传文件
     * <h3>该方法不负责stream的关闭</h3>
     * <h3>由于stream的特殊性，该方法在重试效果可能不明显</h3>
     * @param remoteFilePath 远程目标文件路径（含文件名）
     * @param inputStream input stream
     * @return 成功true 失败false
     * @throws IOException 远程连接或本地文件读取出错抛出异常
     */
    public boolean uploadFile(String remoteFilePath, InputStream inputStream) throws IOException {
        if (remoteFilePath == null || inputStream == null) {
            throw new IllegalArgumentException("remote file path is null or input stream is null");
        }
        int tryTimes = 0;
        boolean uploadResult = false;
        do {
            FTPClient ftpClient = createClient();

            try {
                setMode(ftpClient);
                uploadResult = ftpClient.storeFile(remoteFilePath, inputStream);
            } catch (SocketTimeoutException ste) {
                logger.warn(ste.getMessage(), ste.getCause());
            } catch (Exception e) {
                logger.error(e.getMessage(), e.getCause());
            } finally {
                ftpClient.disconnect();
            }
        } while (++tryTimes < maxTryTimes && !uploadResult);

        return uploadResult;
    }

    /**
     * 删除远程文件
     * @param remoteFilePath 远程目标文件路径
     * @return 成功true  失败false
     * @throws IOException 远程连接错误时抛出异常
     */
    public boolean removeFile(String remoteFilePath) throws IOException {
        int tryTimes = 0;
        boolean deleteResult = false;

        do {
            FTPClient ftpClient = createClient();

            try {
                setMode(ftpClient);
                deleteResult = ftpClient.deleteFile(remoteFilePath);
            } catch (SocketTimeoutException ste) {
                logger.warn(ste.getMessage(), ste.getCause());
            } catch (Exception e) {
                logger.error(e.getMessage(), e.getCause());
            } finally {
                ftpClient.disconnect();
            }
        } while (++tryTimes < maxTryTimes && !deleteResult);

        return deleteResult;
    }

    /**
     * 移动远程文件
     * <h3>源文件会被删除</h3>
     * @param remoteSourceFile 远程源文件路径
     * @param remoteTargetFile 远程目标文件路径
     * @return 成功true 失败false
     * @throws IOException 远程连接错误时抛出异常
     */
    public boolean moveFile(String remoteSourceFile, String remoteTargetFile) throws IOException {
        if (remoteSourceFile == null || remoteTargetFile == null) {
            throw new IllegalArgumentException("remote source file is null or remote target file is null");
        }

        int tryTimes = 0;
        boolean removeResult = false;

        do {
            FTPClient ftpClient = createClient();

            try {
                setMode(ftpClient);
                removeResult = ftpClient.rename(remoteSourceFile, remoteTargetFile);
            } catch (SocketTimeoutException ste) {
                logger.warn(ste.getMessage(), ste.getCause());
            } catch (Exception e) {
                logger.error(e.getMessage(), e.getCause());
            } finally {
                ftpClient.disconnect();
            }
        } while (++tryTimes < maxTryTimes && !removeResult);

        return removeResult;
    }

    /**
     * 获取指定目录下的所有文件
     * @param path 远程目录
     * @return 远程目录下的所有文件
     * @throws IOException 远程连接错误时抛出异常
     */
    public FTPFile[] getFiles(String path) throws IOException {
        return getFiles(path, maxTryTimes);
    }

    /**
     * 获取指定目录下的所有文件
     * @param path 远程目录
     * @param maxTryTimes 最大尝试次数
     * @return 远程目录下的所有文件
     * @throws IOException 远程连接错误时抛出异常
     */
    public FTPFile[] getFiles(String path, int maxTryTimes) throws IOException {
        int tryTimes = 0;

        FTPFile[] ftpFiles = new FTPFile[0];
        do {
            FTPClient ftpClient = createClient();
            try {
                setMode(ftpClient);
                ftpFiles = ftpClient.listFiles(path);
            } catch (SocketTimeoutException ste) {
                logger.warn(ste.getMessage(), ste.getCause());
            } catch (Exception e) {
                logger.error(e.getMessage(), e.getCause());
            } finally {
                ftpClient.disconnect();
            }
        } while (++tryTimes < maxTryTimes && ftpFiles.length == 0);

        return ftpFiles;
    }

    protected void setMode(FTPClient ftpClient) {
        if (isPassive) ftpClient.enterLocalPassiveMode();
        else ftpClient.enterLocalActiveMode();
    }

    protected FTPClient createClient() throws IOException {
        FTPClient ftpClient = new FTPClient();

        ftpClient.setConnectTimeout(timeout);
        ftpClient.setDefaultTimeout(timeout);
        ftpClient.setDataTimeout(timeout);
        ftpClient.connect(ftpHost);
        ftpClient.login(ftpUsername, ftpPassword);

        return ftpClient;
    }


    public InputStream readCsvFile(String fileName) throws IOException{
        FTPClient client = createClient();
        InputStream inputStream = client.retrieveFileStream(fileName);
        return inputStream;
    }

    public String getFtpHost() {
        return ftpHost;
    }

    public void setFtpHost(String ftpHost) {
        this.ftpHost = ftpHost;
    }

    public String getFtpUsername() {
        return ftpUsername;
    }

    public void setFtpUsername(String ftpUsername) {
        this.ftpUsername = ftpUsername;
    }

    public String getFtpPassword() {
        return ftpPassword;
    }

    public void setFtpPassword(String ftpPassword) {
        this.ftpPassword = ftpPassword;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getMaxTryTimes() {
        return maxTryTimes;
    }

    public void setMaxTryTimes(int maxTryTimes) {
        this.maxTryTimes = maxTryTimes;
    }

    public boolean isPassive() {
        return isPassive;
    }

    public void setPassive(boolean passive) {
        isPassive = passive;
    }
}
