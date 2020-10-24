package com.wcc.wds.web.service;

import java.io.File;

import com.wcc.wds.core.biz.elasticsearch.dao.ElasticsearchDao;
import com.wcc.wds.web.data.PublicData;
import com.wcc.wds.web.entity.RestoreContributionEntity;
import com.wcc.wds.web.mapper.WithdrawContributionMapper;
import com.wcc.wds.web.model.ElasticsearchModel;
import com.wcc.wds.web.model.WithdrawContributionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wcc.wds.web.entity.WithdrawContributionEntity;
import com.wcc.wds.web.utils.FileUtils;

/**
 * 数据撤稿与恢复服务
 */
@Service
public class ContributionService {

    @Autowired
    private WithdrawContributionMapper withdrawContributionMapper;

    @Autowired
    private ElasticsearchDao elasticsearchDao;

    @Value("${file.recover.path}")
    private String fileRecoverPath;

    @Value("${file.content}")
    private String fileContent;

    /**
     * 数据撤销
     *
     * @param dataModifyEntity
     */
    public void revoke(WithdrawContributionEntity dataModifyEntity) {
        String targetPath = fileRecoverPath + dataModifyEntity.getFilePath();
        File sourceFile = new File(dataModifyEntity.getFilePath());
        File targetFile = new File(targetPath);
        //将文件移到回收站并加密
        FileUtils.encFile(sourceFile, targetFile);
        //将源数据内容替换成自定义内容
        FileUtils.setContent(sourceFile, fileContent);

        //将撤稿信息入库到
        WithdrawContributionModel withdrawContributionModel = new WithdrawContributionModel();
        withdrawContributionModel.setId(dataModifyEntity.getId());
        withdrawContributionModel.setFilePath(targetPath);
        withdrawContributionModel.setWithdrawFilePath(dataModifyEntity.getFilePath());
        withdrawContributionModel.setWithdrawType(dataModifyEntity.getWithdrawType());
        withdrawContributionMapper.insert(withdrawContributionModel);

        //修改es的装填为撤稿
        ElasticsearchModel elasticsearchModel = new ElasticsearchModel();
        elasticsearchModel.setId(dataModifyEntity.getId());
        elasticsearchModel.setStatus(PublicData.WITHDREW);
        elasticsearchDao.addDocumentToBulkProcessor(elasticsearchModel);

    }

    /**
     * 数据恢复
     *
     * @param restoreContributionEntity
     */
    public void restore(RestoreContributionEntity restoreContributionEntity) {

        WithdrawContributionModel withdrawContributionModel = withdrawContributionMapper.selectById(restoreContributionEntity.getId());

        File sourceFile = new File(withdrawContributionModel.getFilePath());
        File targetFile = new File(withdrawContributionModel.getWithdrawFilePath());
        //将加密文件解密并恢复到原始位置
        FileUtils.decFile(sourceFile, targetFile);
        //删除加密文件
        FileUtils.delete(sourceFile);
        //删除数据库记录
        withdrawContributionMapper.deleteById(restoreContributionEntity.getId());

        //修改es的装填为撤稿
        ElasticsearchModel elasticsearchModel = new ElasticsearchModel();
        elasticsearchModel.setId(restoreContributionEntity.getId());
        elasticsearchModel.setStatus(PublicData.PUBLISHED);
        elasticsearchDao.addDocumentToBulkProcessor(elasticsearchModel);
    }
}
