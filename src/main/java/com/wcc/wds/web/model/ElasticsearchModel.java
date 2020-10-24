package com.wcc.wds.web.model;

import com.wcc.wds.web.entity.NodeNameEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ElasticsearchModel {

    private String title;

    private String id;

    private String text;

    private Date time;

    private String source;

    private String editor;

    private String url;

    private String serverCatalogue;

    private String status;

    private List<NodeNameEntity> nodeNames;

    private String publishTime;

    private String domainId;

    public ElasticsearchModel(){};

    public ElasticsearchModel(String title, String id, String text, Date time, String source, String editor, String url, String serverCatalogue, String status, List<NodeNameEntity> nodeNames, String publishTime, String domainId) {
        this.title = title;
        this.id = id;
        this.text = text;
        this.time = time;
        this.source = source;
        this.editor = editor;
        this.url = url;
        this.serverCatalogue = serverCatalogue;
        this.status = status;
        this.nodeNames = nodeNames;
        this.publishTime = publishTime;
        this.domainId = domainId;
    }

}
