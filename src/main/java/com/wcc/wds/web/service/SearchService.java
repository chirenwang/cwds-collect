package com.wcc.wds.web.service;

import com.wcc.wds.core.biz.elasticsearch.dao.ElasticsearchDao;
import com.wcc.wds.web.response.ResponseEnum;
import com.wcc.wds.web.response.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

/**
 * 查询服务
 */
@Service
public class SearchService {

    @Autowired
    private ElasticsearchDao elasticsearchDao;

    public ArrayList<String> search(String queryString){
        try {
            return elasticsearchDao.search(queryString);
        }catch (Exception e){
            throw new ServiceException(ResponseEnum.ES_SEARCH_FAILED, e);
        }

    }
}
