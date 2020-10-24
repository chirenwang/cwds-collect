/**  
 * ResultStatusEnum.java
 * com.wcc.wds.web.response
 * 
 * @author pengguang
 * @date 2020年10月23日 下午10:14:51
 * 版权所有
 */
package com.wcc.wds.web.response;

/**
 * 返回值状态枚举
 *
 * @author pengguang
 * @date 2020年10月23日 下午10:14:51
 */
public enum ResponseEnum {

    /**
     * 请求成功
     */
    SUCCESS(200, "请求成功"),
    /**
     * 
     */
    FAIL(400, "请求失败"),

    FILE_COPY_ERROR(1000, "文件复制失败"),

    FILE_ENC_ERROR(1001, "文件加密失败失败"),

    FILE_DEC_ERROR(1002, "文件解密失败失败"),
    
    FILE_CREATE_ERROR(1003, "文件创建失败失败"),
    
    FILE_SET_CONTENT_ERROR(1004, "文件内容设置失败"),
    
    FILE_DELETE_ERROR(1005, "文件删除失败"),

    ES_SEARCH_FAILED(1006, "查询ES失败"),

    CREATE_TASK_FAILED(1008, "创建采集任务失败"),

    DELETE_TASK_FAILED(1009, "删除采集任务失败"),

    PAUSE_TASK_FAILED(1010, "暂停采集任务失败"),

    MODIFY_TASK_FAILED(1011, "更新采集任务失败"),

    SEARCH_TASK_FAILED(1012, "查询采集任务失败"),

    NO_SUCH_OPERATE(1013, "不支持的操作类型");

    public Integer code;

    public String msg;

    ResponseEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
