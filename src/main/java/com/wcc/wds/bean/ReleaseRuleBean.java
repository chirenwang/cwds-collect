package com.wcc.wds.bean;


import java.util.List;

/**
 * 发布规则参数
 */
public class ReleaseRuleBean {
    /**
     * 域名
     */
    private String domain;
    /**
     * 目录
     */
    private String menu;
    /**
     * 子主题
     */
    private String subTopic;
    /**
     * 文件名规则
     */
    private String fileNameRule;
    /**
     * 目录列表
     */
    private List<String> menuList;
}
