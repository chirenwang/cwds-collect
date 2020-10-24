package com.wcc.wds.core.biz.elasticsearch;

import com.wcc.wds.core.biz.elasticsearch.dao.ElasticsearchDao;
import com.wcc.wds.web.model.ElasticsearchModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ElasticsearchWriter {

    @Autowired
    private ElasticsearchDao elasticsearchDao;


    public void write(ElasticsearchModel elasticsearchModel){
            elasticsearchDao.addDocumentToBulkProcessor(elasticsearchModel);
    }
}
