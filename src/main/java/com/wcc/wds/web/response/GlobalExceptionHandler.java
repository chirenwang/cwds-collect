/**  
 * GlobalExceptionHandler.java
 * com.wcc.wds.web.response
 * 
 * @author pengguang
 * @date 2020年10月24日 上午12:53:14
 * 版权所有
 */
package com.wcc.wds.web.response;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 自定义全局异常
 *
 * @author pengguang
 * @date 2020年10月24日 上午12:53:14
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 处理自定义的业务异常
     * 
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = ServiceException.class)
    public Response<String> ServiceExceptionHandler(ServiceException e) {
        logger.error("发生业务异常！原因是：{}", e.getMessage());
        if (null == e.getMessage()) {
            return Response.fail(ResponseEnum.FAIL);
        }
        return Response.fail(e.getResponseEnum());

    }

    /**
     * 处理其他异常
     * 
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Response<String> exceptionHandler(HttpServletRequest req, Exception e) {
        logger.error("未知异常！原因是:", e);
        return Response.fail(ResponseEnum.FAIL);
    }

}
