/**  
 * FileUtils.java
 * com.wcc.wds.web.utils
 * 
 * @author pengguang
 * @date 2020年10月24日 上午1:18:29
 * 版权所有
 */
package com.wcc.wds.web.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import com.wcc.wds.web.response.ResponseEnum;
import com.wcc.wds.web.response.ServiceException;

/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author pengguang
 * @date 2020年10月24日 上午1:18:29
 */

public class FileUtils {

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
                encFile.createNewFile();
            } catch (IOException e) {
                throw new ServiceException(ResponseEnum.FILE_CREATE_ERROR);
            }
        }

        try (InputStream fis = new FileInputStream(srcFile); OutputStream fos = new FileOutputStream(encFile);) {
            while ((dataOfFile = fis.read()) > -1) {
                fos.write(dataOfFile ^ numOfEncAndDec);
            }
            fos.flush();
        } catch (Exception e) {
            throw new ServiceException(ResponseEnum.FILE_ENC_ERROR);
        }

    }

    /**
     * 文件解密
     *
     * @param srcFile
     * @param encFile
     * @throws Exception
     */
    public static void decFile(File encFile, File decFile) {
        if (!decFile.exists()) {
            try {
                decFile.createNewFile();
            } catch (IOException e) {
                throw new ServiceException(ResponseEnum.FILE_CREATE_ERROR);
            }
        }
        try (InputStream fis = new FileInputStream(encFile); OutputStream fos = new FileOutputStream(decFile);) {
            while ((dataOfFile = fis.read()) > -1) {
                fos.write(dataOfFile ^ numOfEncAndDec);
            }
            fos.flush();
        } catch (Exception e) {
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
            throw new ServiceException(ResponseEnum.FILE_CREATE_ERROR);
        }
    }

    /**
     * 文件删除
     *
     * @param file
     */
    public static void delete(File file) {
        try  {
            Files.delete(file.toPath());
        } catch (Exception e) {
            throw new ServiceException(ResponseEnum.FILE_DELETE_ERROR);
        }
    }

}
