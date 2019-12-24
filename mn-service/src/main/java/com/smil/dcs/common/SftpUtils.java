package com.smil.dcs.common;

import com.google.common.collect.Lists;
import com.jcraft.jsch.*;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.smil.dcs.exception.DcsRuntimeException;
import com.smil.dcs.exception.SftpRuntimeException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

abstract class SftpUtils {
    protected static final Logger LOGGER = LoggerFactory.getLogger(SftpUtils.class);

    private static final String SLASH = "/";

    protected String host;// 服务器连接ip
    protected String username;// 用户名
    protected String password;// 密码
    protected int port = 22;// 端口号
    
    protected ThreadLocal<Session> sessionLocal = new ThreadLocal<>();
    protected ThreadLocal<ChannelSftp> sftpLocal = new ThreadLocal<>();

    // 默认用户密码连接,可重写扩展
    protected void connect() {
        try {
            tryConnection();

            // 创建新连接
            JSch jsch = new JSch();
            Session sshSession = jsch.getSession(username, host, port);
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Session created.");
            }
            sshSession.setPassword(password);
            Properties sshConfig = new Properties();
            sshSession.setConfig("StrictHostKeyChecking", "no"); //不检查
            sshSession.setConfig("userauth.gssapi-with-mic", "no"); //
            sshSession.setConfig("UseDNS", "no");
            sshSession.setConfig(sshConfig);
            sshSession.connect();
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Session connected.");
            }
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Opening Channel.");
            }
            ChannelSftp sftp = (ChannelSftp) channel;
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Connected to {}.",host);
            }
            // 存放
            this.setSshSession(sshSession);
            this.setSftp(sftp);
        } catch (Exception e) {
            throw new SftpRuntimeException(e);
        }
    }

    protected void tryConnection() throws JSchException {
        // 如果Session、ChannelSftp任意一个存在则表示之前已进行过连接,仅检查是否还处于连接状态.
        if (null != getSshSession() && null != getSftp()) {
            if (!getSshSession().isConnected()) {
                getSshSession().connect();
            }
            if (!getSftp().isConnected()) {
                getSftp().connect();
            }
            return;
        }
    }

    protected void disconnect() {
        if (getSftp() != null) {
            getSftp().disconnect();
            sftpLocal.remove();
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("sftp is closed already");
            }
        }

        if (getSshSession() != null) {
            getSshSession().disconnect();
            sessionLocal.remove();
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("sshSession is closed already");
            }
        }
        LOGGER.info("sshSession and sftp are closed already");
    }

    public List<OutputStream> batchSftpDownload(String remotePath) {
        return this.batchSftpDownload(remotePath, null, null, Boolean.FALSE);
    }

    public List<OutputStream> batchSftpDownload(String remotePath, boolean del) {
        return this.batchSftpDownload(remotePath, null, null, del);
    }

    public List<String> getFileList(String remotePath) {
        return this.getFileList(remotePath, null);
    }

    public List<String> getFileList(String remotePath, String pattern) {
        List<String> fileNames = Lists.newArrayList();
        try {
            this.connect();
            getSftp().ls(remotePath,entry -> {
                String fileName = entry.getFilename();
                if (!entry.getAttrs().isDir() && (StringUtils.isEmpty(pattern) || (StringUtils.isNotEmpty(pattern) && Pattern.matches(pattern, fileName)))) {
                    fileNames.add(fileName);
                }
                return ChannelSftp.LsEntrySelector.CONTINUE;
            });
            return fileNames;
        } catch (SftpException e) {
            LOGGER.info("load files error", e);
            return fileNames;
        } finally {
            this.disconnect();
        }
    }

    public String userDir() {
        try {
            this.connect();
            return getSftp().pwd();
        } catch (SftpException e) {
            throw new DcsRuntimeException("Failed to get user SFTP directory.", e);
        } finally {
            this.disconnect();
        }
    }

    public Boolean moveFile(String from, String to) {
        try {
            this.connect();
            this.createDir(to);
            getSftp().rename(from, to);
            LOGGER.info("move file success");
            return Boolean.TRUE;
        } catch (SftpException e) {
            LOGGER.info("move files error", e);
            throw new DcsRuntimeException(e);
        } finally {
            this.disconnect();
        }
    }

    public Boolean rmFile(String filePath) {
        return this.rmFile(Lists.newArrayList(filePath));
    }

    public Boolean rmFile(List<String> filePaths) {
        try {
            this.connect();
            for (String filePath : filePaths) {
                getSftp().rm(filePath);
            }
            LOGGER.info("remove file success");
            return Boolean.TRUE;
        } catch (SftpException e) {
            LOGGER.info("remove files error", e);
            throw new DcsRuntimeException(e);
        } finally {
            this.disconnect();
        }
    }

    public List<OutputStream> batchSftpDownload(String remotePath, String fileFormat, String fileEndFormat, boolean del) {
        this.connect();
        List<OutputStream> outputStreams = new ArrayList<>();
        OutputStream outputStream = null;
        try {
            List<String> v = listFiles(remotePath);
            if (CollectionUtils.isNotEmpty(v)) {
                Iterator it = v.iterator();
                while (it.hasNext()) {
                    LsEntry entry = (LsEntry) it.next();
                    String filename = entry.getFilename();
                    SftpATTRS attrs = entry.getAttrs();
                    if (!attrs.isDir()) {
                        fileFormat = fileFormat == null ? "" : fileFormat.trim();
                        fileEndFormat = fileEndFormat == null ? "" : fileEndFormat.trim();
                        // 三种情况
                        if (fileFormat.length() > 0 && fileEndFormat.length() > 0) {
                            if (filename.startsWith(fileFormat) && filename.endsWith(fileEndFormat)) {
                                outputStream = sftpDownLoad(remotePath, filename, Boolean.FALSE);
                                if (outputStream != null) {
                                    outputStreams.add(outputStream);
                                    if (outputStream != null && del) {
                                        deleteSFTP(remotePath, filename);
                                    }
                                }
                            }
                        } else if (fileFormat.length() > 0 && "".equals(fileEndFormat)) {
                            if (filename.startsWith(fileFormat)) {
                                outputStream = sftpDownLoad(remotePath, filename, Boolean.FALSE);
                                if (outputStream != null) {
                                    outputStreams.add(outputStream);
                                    if (outputStream != null && del) {
                                        deleteSFTP(remotePath, filename);
                                    }
                                }
                            }
                        } else if (fileEndFormat.length() > 0 && "".equals(fileFormat)) {
                            if (filename.endsWith(fileEndFormat)) {
                                outputStream = sftpDownLoad(remotePath, filename, Boolean.FALSE);
                                if (outputStream != null) {
                                    outputStreams.add(outputStream);
                                    if (outputStream != null && del) {
                                        deleteSFTP(remotePath, filename);
                                    }
                                }
                            }
                        } else {
                            outputStream = sftpDownLoad(remotePath, filename, Boolean.FALSE);
                            if (outputStream != null) {
                                outputStreams.add(outputStream);
                                if (outputStream != null && del) {
                                    deleteSFTP(remotePath, filename);
                                }
                            }
                        }
                    }
                }
            }
        } finally {
            disconnect();
        }
        return outputStreams;
    }

    public OutputStream sftpDownLoad(String remotePath, String remoteFileName) {
        return this.sftpDownLoad(transRemotePath(remotePath, remoteFileName), Boolean.TRUE);
    }

    public OutputStream sftpDownLoad(String remotePath, String remoteFileName, Boolean isAutoConnect) {
        return this.sftpDownLoad(transRemotePath(remotePath, remoteFileName), isAutoConnect);
    }

    public byte[] sftpDownLoad(String remoteFilePath) {
        ByteArrayOutputStream outputStream = (ByteArrayOutputStream) this.sftpDownLoad(remoteFilePath, Boolean.TRUE);
        return outputStream.toByteArray();
    }

    public OutputStream sftpDownLoad(String remoteFilePath, Boolean isAutoConnect) {
        try {
            if (isAutoConnect) {
                this.connect();
            }
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(4096)) {
                getSftp().get(remoteFilePath, outputStream);
                LOGGER.info("===DownloadFile:{}  success from sftp.",remoteFilePath);
                return outputStream;
            }
        } catch (SftpException | IOException e) {
            LOGGER.info("===DownloadFile:{}  error from sftp.",remoteFilePath);
            throw new DcsRuntimeException(e);
        } finally {
            if (isAutoConnect) {
                this.disconnect();
            }
        }
    }

    private String transRemotePath(String remotePath, String remoteFileName) {
        return (remotePath.endsWith(SLASH) ? remotePath : remotePath + SLASH) + remoteFileName;
    }

    public boolean sftpUpload(String remotePath, String remoteFileName, String localPath, String localFileName) {
        return this.sftpUploads(this.transRemotePath(remotePath, remoteFileName), this.transRemotePath(localPath, localFileName), Boolean.TRUE);
    }

    public boolean sftpUpload(String remotePath, String remoteFileName, String localPath, String localFileName, Boolean isAutoConnect) {
        return this.sftpUploads(this.transRemotePath(remotePath, remoteFileName), this.transRemotePath(localPath, localFileName), isAutoConnect);
    }

    public boolean sftpUpload(String remoteFilePath, String localPath, String localFileName) {
        return this.sftpUploads(remoteFilePath, this.transRemotePath(localPath, localFileName), Boolean.TRUE);
    }

    public boolean sftpUploads(String remoteFilePath, String localFilePath, Boolean isAutoConnect){
        try {
            return this.sftpUpload(remoteFilePath,localFilePath,isAutoConnect);
        } catch (IOException e) {
            return Boolean.FALSE;
        }
    }

    public boolean sftpUpload(String remoteFilePath, String localFilePath, Boolean isAutoConnect) throws IOException{
        try ( FileInputStream in = new FileInputStream(new File(localFilePath))){
            return this.sftpUpload(remoteFilePath, in, isAutoConnect);
        } catch (FileNotFoundException e) {
            LOGGER.error("Exception:", e);
            throw new DcsRuntimeException(e);
        }
    }

    public boolean sftpUpload(String remoteFilePath, InputStream inputStream) {
        return this.sftpUpload(remoteFilePath, inputStream, Boolean.TRUE);
    }

    public boolean sftpUpload(String remoteFilePath, InputStream inputStream, Boolean isAutoConnect) {
        try {
            if (isAutoConnect) {
                this.connect();
            }
            createDir(remoteFilePath);
            getSftp().put(inputStream, remoteFilePath);
            LOGGER.info("===uploadFile:{} success from sftp.",remoteFilePath);
            return Boolean.TRUE;
        } catch (SftpException e) {
            LOGGER.info("===uploadFile:" + remoteFilePath + " error from sftp.", e);
            throw new DcsRuntimeException(e);
        } finally {
            if (isAutoConnect) {
                this.disconnect();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public boolean bacthSftpUplod(String remotePath, String localPath, boolean del) {
        try {
            connect();
            File file = new File(localPath);
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                if ((files[i].isFile() && files[i].getName().indexOf("bak") == -1) && (this.sftpUpload(remotePath, files[i].getName(), localPath, files[i].getName(), Boolean.FALSE) && del)) {
                    deleteFile(localPath + files[i].getName());
                }
            }
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("upload file is success:remotePath={} and localPath={} ,file size is {}",remotePath,localPath,files.length);
            }
            return true;
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
            throw new DcsRuntimeException(e);
        } finally {
            this.disconnect();
        }
    }

    private boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return false;
        }

        if (!file.isFile()) {
            return false;
        }
        boolean rs = file.delete();
        if (rs && LOGGER.isInfoEnabled()) {
            LOGGER.info("deleteLeadTime file success from local.");
        }
        return rs;
    }

    private boolean createDir(String createpath) {
        try {
            createpath = this.getDirForPath(createpath);
            if (isDirExist(createpath)) {
                getSftp().cd(createpath);
                return true;
            }
            String[] pathArry = createpath.split(SLASH);
            StringBuilder filePath = new StringBuilder(SLASH);
            for (String path : pathArry) {
                if (path.equals("")) {
                    continue;
                }
                filePath.append(path + SLASH);
                if (isDirExist(filePath.toString())) {
                    getSftp().cd(filePath.toString());
                } else {
                    // 建立目录
                    getSftp().mkdir(filePath.toString());
                    // 进入并设置为当前目录
                    getSftp().cd(filePath.toString());
                }

            }
            getSftp().cd(createpath);
            return true;
        } catch (SftpException e) {
            LOGGER.error("Exception:", e);
        }
        return false;
    }

    private String getDirForPath(String createpath) {
        return createpath.substring(0, createpath.lastIndexOf(SLASH) + 1);
    }

    private boolean isDirExist(String directory) {
        boolean isDirExistFlag = false;
        try {
            SftpATTRS sftpATTRS = getSftp().lstat(directory);
            isDirExistFlag = true;
            return sftpATTRS.isDir();
        } catch (Exception e) {
            if (e.getMessage().equalsIgnoreCase("no such file")) {
                isDirExistFlag = false;
            }
        }
        return isDirExistFlag;
    }

    public void sftpDelete(String delFilePath) {
        this.connect();
        this.deleteSFTP(delFilePath);
        this.disconnect();
    }

    private void deleteSFTP(String directory, String deleteFile) {
        this.deleteSFTP(transRemotePath(directory, deleteFile));
    }

    private void deleteSFTP(String delFilePath) {
        try {
            getSftp().rm(delFilePath);
            LOGGER.info("===deleteFile:{} success from sftp.",delFilePath);
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
            throw new DcsRuntimeException(e);
        }
    }

    private List<String> listFiles(String directory) {
        try {
            return Lists.newArrayList(getSftp().ls(directory));
        } catch (SftpException e) {
            return Lists.newArrayList();
        }
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public ChannelSftp getSftp() {
        return sftpLocal.get();
    }

    public void setSftp(ChannelSftp sftp) {
        sftpLocal.set(sftp);
    }

    @Override
    public String toString() {
        return "SftpUtils{" + "host='" + host + '\'' + ", username='" + username + '\'' + ", password='" + password + '\'' + ", port=" + port + ", sftp=" + getSftp() + ", sshSession="
                + getSshSession() + '}';
    }

    /**
     * @return the sshSession
     */
    public Session getSshSession() {
        return sessionLocal.get();
    }

    /**
     * @param sshSession the sshSession to set
     */
    public void setSshSession(Session sshSession) {
        sessionLocal.set(sshSession);
    }

    /**
     * @return the sessionlocal
     */
    public ThreadLocal<Session> getSessionlocal() {
        return sessionLocal;
    }

    /**
     * @return the sftplocal
     */
    public ThreadLocal<ChannelSftp> getSftplocal() {
        return sftpLocal;
    }
}
