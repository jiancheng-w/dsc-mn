package com.smil.dcs.service;

import com.smil.dcs.enums.FileType;

import java.io.InputStream;
import java.util.List;

public interface IFileStorageService {

    /**
     * 暂未实现
     * @param inputStream
     * @param fileSavePath
     * @param fileName
     * @param fileType
     * @return
     */
    String writePublicFile(InputStream inputStream, String fileSavePath, String fileName, FileType fileType);

    /**
     * 暂未实现
     * @param inputStream
     * @param fileSavePath
     * @param fileType
     * @return
     */
    String writePublicFile(InputStream inputStream, String fileSavePath, FileType fileType);

    /**
     *根据传入流,文件保存路径,文件名称,文件类型保存私有文件
     * @param inputStream  文件流
     * @param fileSavePath  文件保存地址
     * @param fileName  文件保存名
     * @param fileType  文件类型
     * @return 保存文件路径
     */
    String writePrivateFile(InputStream inputStream, String fileSavePath, String fileName, FileType fileType);

    /**
     *根据传入流,文件保存路径,文件类型保存私有文件
     * @param inputStream  文件流
     * @param fileSavePath  文件保存地址
     * @param fileType  文件类型
     * @return 保存文件路径
     */
    String writePrivateFile(InputStream inputStream, String fileSavePath, FileType fileType);


    /**
     * 根据传入流,文件类型保存私有文件
     * @param inputStream 文件流
     * @param fileType 文件类型
     * @return 保存文件路径
     */
    String writePrivateFile(InputStream inputStream, FileType fileType);

    /**
     *  获取远程路径下的文件名列表 文件夹过滤
     * @param remotePath
     * @return
     */
    List<String> getRemotePrivateFilenameList(String remotePath);

    /**
     *  根据正则获取远程路径下的文件名列表 文件夹过滤
     * @param remotePath
     * @param pattern
     * @return
     */
    List<String> getRemotePrivateFilenameList(String remotePath, String pattern);


    /**
     *  移动私有文件
     * @param srouce 源路径
     * @param target 目标路径
     * @return
     */
    boolean movePrivateFile(String srouce, String target);

    /**
     * 删除私有文件
     * @param filePath 目标文件路径
     * @return
     */
    boolean deletePrivateFile(String filePath);

    /**
     * 批量删除私有文件
     * @param filePaths 目标文件路径数组
     * @return
     */
    boolean deletePrivateFileList(List<String> filePaths);

    /**
     * 判断私有文件是否存在
     * @param fileName
     * @return
     */
    boolean isExistsPrivateFile(String fileName);


    /**
     * 根据文件地址读取私有文件
     * @param filePath 文件地址
     * @return 文件数据
     */
    byte[] readPrivateFile(String filePath);

}
