package com.wcc.wds.web.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wcc.wds.web.entity.DataModifyEntity;
import com.wcc.wds.web.utils.FileUtils;

/**
 * 数据撤稿与恢复服务
 */
@Service
public class DataModifyService {

    @Value("${file.recover.path}")
    private String fileRecoverPath;

    @Value("${file.content}")
    private String fileContent;

    /**
     * 数据撤销
     *
     * @param dataModifyEntity
     */
    public void revoke(DataModifyEntity dataModifyEntity) {

        // 通过id查询Es数据
        String sourcePath = "";

        String targetPath = fileRecoverPath + sourcePath;
        File sourceFile = new File(sourcePath);
        File targetFile = new File(targetPath);
        FileUtils.encFile(sourceFile, targetFile);
        FileUtils.setContent(targetFile, fileContent);

    }
    
    /**
     * 数据恢复
     *
     * @param dataModifyEntity
     */
    public void restore(DataModifyEntity dataModifyEntity) {

        // 通过id查询Es数据
        String sourcePath = "";
        String targetPath = fileRecoverPath + sourcePath;
        File sourceFile = new File(sourcePath);
        File targetFile = new File(targetPath);
        FileUtils.decFile(sourceFile, targetFile);
        FileUtils.delete(sourceFile);

    }
}
