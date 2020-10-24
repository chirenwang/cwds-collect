/**
 * FileUtils.java
 * com.wcc.wds.web.utils
 *
 * @author pengguang
 * @date 2020年10月24日 上午1:18:29
 * 版权所有
 */
package com.wcc.wds.web.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import com.wcc.wds.web.mapper.WithdrawContributionMapper;
import com.wcc.wds.web.response.ResponseEnum;
import com.wcc.wds.web.response.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 通用文件处理
 *
 * @author pengguang
 * @date 2020年10月24日 上午1:18:29
 */

public class FileUtils {

    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);


    private static final int numOfEncAndDec = 0x99; // 加密解密秘钥

    private static int dataOfFile = 0; // 文件字节内容

    /**
     * 文件加密
     *
     * @param srcFile
     * @param encFile
     * @throws Exception
     */
    public static void encFile(File srcFile, File encFile) {
        if (!encFile.exists()) {
            try {
                if (!encFile.getParentFile().exists()) {
                    encFile.getParentFile().mkdirs();
                }
                encFile.createNewFile();
            } catch (IOException e) {
                logger.warn(ResponseEnum.FILE_CREATE_ERROR.msg, e);
                throw new ServiceException(ResponseEnum.FILE_CREATE_ERROR);
            }
        }

        try (InputStream fis = new FileInputStream(srcFile); OutputStream fos = new FileOutputStream(encFile);) {
            while ((dataOfFile = fis.read()) > -1) {
                fos.write(dataOfFile ^ numOfEncAndDec);
            }
            fos.flush();
        } catch (Exception e) {
            logger.warn(ResponseEnum.FILE_ENC_ERROR.msg, e);
            throw new ServiceException(ResponseEnum.FILE_ENC_ERROR);
        }

    }

    /**
     * 文件解密
     *
     * @param encFile
     * @param encFile
     * @throws Exception
     */
    public static void decFile(File encFile, File decFile) {
        if (!decFile.exists()) {
            try {
                if (!decFile.getParentFile().exists()) {
                    decFile.getParentFile().mkdirs();
                }
                decFile.createNewFile();
            } catch (IOException e) {
                logger.warn(ResponseEnum.FILE_CREATE_ERROR.msg, e);
                throw new ServiceException(ResponseEnum.FILE_CREATE_ERROR);
            }
        }
        if (!encFile.exists()) {
            logger.warn(ResponseEnum.FILE_DOES_NOT_EXIST_ERROR.msg);
            throw new ServiceException(ResponseEnum.FILE_DOES_NOT_EXIST_ERROR);
        }
        try (InputStream fis = new FileInputStream(encFile); OutputStream fos = new FileOutputStream(decFile);) {
            while ((dataOfFile = fis.read()) > -1) {
                fos.write(dataOfFile ^ numOfEncAndDec);
            }
            fos.flush();
        } catch (Exception e) {
            logger.warn(ResponseEnum.FILE_DEC_ERROR.msg, e);
            throw new ServiceException(ResponseEnum.FILE_DEC_ERROR);
        }
    }

    /**
     * 文件复制
     *
     * @param source
     * @param target
     */
    public static void copy(File source, File target) {
        try {
            Files.copy(source.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            logger.warn(ResponseEnum.FILE_COPY_ERROR.msg, e);
            throw new ServiceException(ResponseEnum.FILE_COPY_ERROR);
        }
    }

    /**
     * 设置文件内容
     *
     * @param file
     * @param content
     */
    public static void setContent(File file, String content) {
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(content);
            fileWriter.flush();
        } catch (Exception e) {
            logger.warn(ResponseEnum.FILE_SET_CONTENT_ERROR.msg, e);
            throw new ServiceException(ResponseEnum.FILE_SET_CONTENT_ERROR);
        }
    }

    /**
     * 文件删除
     *
     * @param file
     */
    public static void delete(File file) {
        try {
            Files.delete(file.toPath());
        } catch (Exception e) {
            logger.warn(ResponseEnum.FILE_DELETE_ERROR.msg, e);
            throw new ServiceException(ResponseEnum.FILE_DELETE_ERROR);
        }
    }

}
