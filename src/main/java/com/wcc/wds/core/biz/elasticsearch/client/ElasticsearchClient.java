package com.wcc.wds.core.biz.elasticsearch.client;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Configuration
public class ElasticsearchClient {

        private final static Logger logger = LoggerFactory.getLogger(ElasticsearchClient.class);

        @Value("${elasticsearch.host}")
        private String esHost;

        @Value("${elasticsearch.port}")
        private int esPort;

        private RestHighLevelClient client;

        @PostConstruct
        public void initialize(){
            client = new RestHighLevelClient(RestClient.builder(new HttpHost(esHost, esPort, "http")));
        }

        @Bean
        public RestHighLevelClient client() {
            return client;
        }


        @PreDestroy
        public void destroy() {

            if (client != null) {
                try {
                    client.close();
                }catch (Exception e){
                    logger.error("close bulkProcessor exception" ,e);
                }
            }
        }

}

