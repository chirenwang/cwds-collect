/**  
 * ServiceException.java
 * com.wcc.wds.web.response
 * 
 * @author pengguang
 * @date 2020年10月24日 上午12:50:23
 * 版权所有
 */
package com.wcc.wds.web.response;

/**
 * 自定义service异常
 *
 * @author pengguang
 * @date 2020年10月24日 上午12:50:23
 */

public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    private ResponseEnum responseEnum;

    /** 
     * 枚举异常
     *
     * @param message
     * @param cause
     */
    public ServiceException(ResponseEnum responseEnum) {
        super(responseEnum.msg);
        this.responseEnum = responseEnum;
    }

    /** 
     * 枚举异常
     *
     * @param message
     * @param cause
     */
    public ServiceException(ResponseEnum responseEnum, Throwable cause) {
        super(responseEnum.msg, cause);
        this.responseEnum = responseEnum;
    }

    public ResponseEnum getResponseEnum() {
        return responseEnum;
    }

    public void setResponseEnum(ResponseEnum responseEnum) {
        this.responseEnum = responseEnum;
    }
    
   

}
