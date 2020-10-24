package com.wcc.wds.core.biz.elasticsearch.dao;

import com.alibaba.fastjson.JSON;
import com.wcc.wds.web.entity.NodeNameEntity;
import com.wcc.wds.web.model.ElasticsearchModel;
import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.Date;

@Component
public class ElasticsearchDao {

    private static final Logger logger = LoggerFactory.getLogger(ElasticsearchDao.class);

    private BulkProcessor bulkProcessor;
    @Autowired
    private RestHighLevelClient client;

    @PostConstruct
    public void initBulkProcessor() {
        BulkProcessor.Listener listener = new BulkProcessor.Listener() {
            @Override
            public void beforeBulk(long executionId, BulkRequest request) {
                logger.info("序号：{} 开始执行{} 条记录保存", executionId, request.numberOfActions());
            }

            @Override
            public void afterBulk(long executionId, BulkRequest request, Throwable failure) {
                logger.error(String.format("序号：%s 执行失败; 总记录数：%s", executionId, request.numberOfActions()), failure);
            }

            @Override
            public void afterBulk(long executionId, BulkRequest request, BulkResponse response) {
                logger.info("序号：{} 执行{}条记录保存成功,耗时：{}毫秒,", executionId, request.numberOfActions(), response.getIngestTookInMillis());
            }
        };
        BulkProcessor.Builder builder = BulkProcessor.builder(
                (request, bulkListener) ->
                        client.bulkAsync(request, RequestOptions.DEFAULT, bulkListener),
                listener);
        builder.setBulkActions(500);
        builder.setBulkSize(new ByteSizeValue(1L, ByteSizeUnit.MB));
        builder.setConcurrentRequests(0);
        builder.setFlushInterval(TimeValue.timeValueSeconds(10L));
        builder.setBackoffPolicy(BackoffPolicy
                .constantBackoff(TimeValue.timeValueSeconds(1L), 3));
        bulkProcessor = builder.build();
    }


    @PreDestroy
    public void closeBulk() {
        if (bulkProcessor != null) {
            try {
                bulkProcessor.close();
            } catch (Exception e) {
                logger.error("close bulkProcessor exception", e);
            }
        }
    }


    public void addDocumentToBulkProcessor(ElasticsearchModel elasticsearchModel) {
        bulkProcessor.add(new IndexRequest("posts").id(elasticsearchModel.getId()).source(XContentType.JSON, JSON.toJSONString(elasticsearchModel)));
    }


}
