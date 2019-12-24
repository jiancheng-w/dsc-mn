package com.smil.dcs.service;


import java.io.File;

public interface IAzureBlobService {

    /**
     *  上传静态文件
     *      container(文件夹) 随机
     *      保存文件名 随机文件名
     * @param file
     * @return 文件访问域名路径
     */
    String uploadPublicFile(File file);

    /**
     *  上传静态文件
     *      container(文件夹) 随机
     *      保存文件名 源文件名
     * @param file
     * @return 文件访问域名路径
     */
    String uploadPublicFileSourceName(File file);

    /**
     *
     *  上传静态文件
     *      container(文件夹) 指定 需遵循container 命名规则
     *                  以小写字母或数字开头，只能包含字母、数字和 dash(-)。
     *                  不能有连续的 dash(-)，dash(-)不能是第一个字符，也不能是最后一个字符。
     *                  所有字符小写，总长度为 3-63 字符。
     *      保存文件名 随机文件名
     * @param file
     * @param container
     * @return 文件访问域名路径
     */
    String uploadPublicFile(File file, String container);

    /**
     *
     *  上传静态文件
     *      container(文件夹) 指定 需遵循container 命名规则
     *                  以小写字母或数字开头，只能包含字母、数字和 dash(-)。
     *                  不能有连续的 dash(-)，dash(-)不能是第一个字符，也不能是最后一个字符。
     *                  所有字符小写，总长度为 3-63 字符。
     *      保存文件名 源文件名
     * @param file
     * @param container
     * @return 文件访问域名路径
     */
    String uploadPublicFileSourceName(File file, String container);

    /**
     * 根据文件地址 删除文件
     * @param fileUrl
     * @return
     */
    void deletePublicFile(String fileUrl);

}
