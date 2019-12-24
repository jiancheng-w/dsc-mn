package com.smil.dcs.common;


import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.smil.dcs.exception.SftpRuntimeException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "sftp.config")
public class FileSftpServer extends SftpUtils {

    private String privateKeyPath;

   private boolean privatekeyConnectionMode;

    private int timeOut;

    //重写连接方法,改用私钥链接
    @Override
    protected void connect() {
       if(privatekeyConnectionMode){
           try{
               tryConnection();
               
               JSch jsch = new JSch();
               jsch.addIdentity(this.getKeyPath());
               Session sshSession = jsch.getSession(username, host, port);
               sshSession.setTimeout(timeOut);
               sshSession.setConfig("StrictHostKeyChecking", "no"); //不检查
               sshSession.setConfig("userauth.gssapi-with-mic", "no"); //
               sshSession.setConfig("UseDNS", "no");
               sshSession.connect(); // 经由过程Session建树链接
               LOGGER.info("Open the SFTP channel.");
               ChannelSftp sftp = (ChannelSftp) sshSession.openChannel("sftp"); // 打开SFTP通道
               sftp.connect(); // 建立SFTP通道的连接
               
               // 存放
               this.setSshSession(sshSession);
               this.setSftp(sftp);
           }catch (JSchException e) {
               throw new SftpRuntimeException("sftp getConnect error",e);
           }
       }else{
           super.connect();
       }
    }

    private String getKeyPath(){
        return this.isLinux() ? privateKeyPath : this.getClass().getClassLoader().getResource("sftp/id_rsa").getPath();
    }

    private boolean isLinux(){
        return System.getProperty("os.name").toLowerCase().indexOf("linux") >= 0;
    }

    @Override
    protected void disconnect() {
       if(privatekeyConnectionMode){
           try {
               if (getSftp() != null) {
                   getSftp().disconnect();
                   getSftplocal().remove();
               }
               if (getSshSession() != null) {
                   getSshSession().disconnect();
                   getSessionlocal().remove();
               }
               LOGGER.info("Close the SFTP channel.");
           } catch (Exception e) {
               LOGGER.error("colosing sftp error", e);
           }
       }else{
           super.disconnect();
       }
    }

    public String getPrivateKeyPath() {
        return privateKeyPath;
    }

    public void setPrivateKeyPath(String privateKeyPath) {
        this.privateKeyPath = privateKeyPath;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public boolean isPrivatekeyConnectionMode() {
        return privatekeyConnectionMode;
    }

    public void setPrivatekeyConnectionMode(boolean privatekeyConnectionMode) {
        this.privatekeyConnectionMode = privatekeyConnectionMode;
    }
}
