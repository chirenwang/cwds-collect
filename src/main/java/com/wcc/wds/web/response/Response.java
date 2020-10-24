/**  
 * Response.java
 * com.wcc.wds.web.response
 * 
 * @author pengguang
 * @date 2020年10月23日 下午10:06:37
 * 版权所有
 */
package com.wcc.wds.web.response;

import java.io.Serializable;

import com.wcc.wds.web.entity.BaseEntity;

/**
 * 统一返回格式
 *
 * @author pengguang
 * @date 2020年10月23日 下午10:06:37
 */

public class Response<T> extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -4505655308965878999L;

    // 返回码
    private Integer code;

    // 返回描述
    private String msg;

    // 返回数据
    private T data;

    /**
     * 全参构造
     *
     * @param code
     * @param msg
     * @param data
     */
    public Response(ResponseEnum responseEnum, T data) {
        super();
        this.code = responseEnum.code;
        this.msg = responseEnum.msg;
        this.data = data;
    }

    /**
     * 全参构造
     *
     * @param code
     * @param msg
     * @param data
     */
    public Response(ResponseEnum responseEnum) {
        super();
        this.code = responseEnum.code;
        this.msg = responseEnum.msg;
    }

    /**
     * 请求成功
     *
     * @return
     */
    public static <T> Response<T> success() {
        return new Response<T>(ResponseEnum.SUCCESS);
    }

    /**
     * 请求成功
     *
     * @return 返回数据
     */
    public static <T> Response<T> success(T data) {
        return new Response<T>(ResponseEnum.SUCCESS, data);
    }

    /**
     * 请求失败
     *
     * @param responseEnum
     * @return
     */
    public static <T> Response<T> fail(ResponseEnum responseEnum) {
        return new Response<T>(responseEnum);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
