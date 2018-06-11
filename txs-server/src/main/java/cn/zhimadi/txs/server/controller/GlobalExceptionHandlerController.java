package cn.zhimadi.txs.server.controller;

import cn.zhimadi.txs.common.exception.CustomException;
import cn.zhimadi.txs.common.pojo.ResponseData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Controller全局异常统一处理
 *
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandlerController {

    private static final Logger logger = LogManager.getLogger(GlobalExceptionHandlerController.class);


    /**
     * 处理未知错误
     * @param e
     * @return
     */
    @ExceptionHandler
    @ResponseBody
    public ResponseData handlerException(Exception e){
        logger.error(e.getMessage(), e);
        return ResponseData.fail("9999", "操作失败，请重新尝试！");
    }


    /**
     * 处理已知的错误
     * @param e
     * @return
     */
    @ExceptionHandler
    @ResponseBody
    public ResponseData handlerCustomException(CustomException e){
        logger.error("发生了错误，错误代码为:【"+ e.getCode() +"】,错误信息为:【"+ e.getMessage() +"】");
        return ResponseData.fail(e.getCode(), e.getMessage());
    }
}
