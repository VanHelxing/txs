package cn.zhimadi.txs.server.interceptor;

import cn.zhimadi.txs.common.constant.Constants;
import cn.zhimadi.txs.common.util.AppContextUtils;
import cn.zhimadi.txs.common.util.JsonUtils;
import cn.zhimadi.txs.common.util.RequestUtils;
import cn.zhimadi.txs.monitor.entity.Log;
import cn.zhimadi.txs.monitor.service.LogService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 日志拦截器
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
public class LogInterceptor implements HandlerInterceptor {

    private static Logger logger = LogManager.getLogger(LogInterceptor.class);

    /** 请求开始时间 */
    private static final String BEGIN_TIME = "begin_time";
    /** 系统日志 */
    private static final String SYSTEM_LOG = "system_log";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("日志拦截器有请求"+ Thread.currentThread().getName() +"进入，路径为: " + request.getRequestURI());

        long beginTime = System.currentTimeMillis();
        String requestType;
        Log log = new Log();

        if (RequestUtils.isRestRequest(request)){
            requestType = Constants.REQUEST_API;
        }else {
            requestType = Constants.REQUEST_SYSTEM;
        }

        log.setRequestTpe(requestType);
        log.setRequestUri(request.getRequestURI());
        log.setRequestAddr(request.getRemoteAddr());

        request.setAttribute(SYSTEM_LOG, log);
        request.setAttribute(BEGIN_TIME, beginTime);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info(Thread.currentThread().getName() + "请求完成!");

        long endTime = System.currentTimeMillis();

        //响应成功记录日志
        if (response.getStatus() == HttpServletResponse.SC_OK){

            LogService logService = AppContextUtils.getBean("logService");
            long beginTime = (long) request.getAttribute(BEGIN_TIME);
            Log log = (Log) request.getAttribute(SYSTEM_LOG);
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Map<String, String[]> requestParam = request.getParameterMap();
            String requestData = JsonUtils.objectToJson(requestParam);

            log.setRequestData(requestData);
            log.setRequestMethod(handlerMethod.getMethod().getName());
            log.setResponseStatus(String.valueOf(response.getStatus()));
            log.setHandleTime(String.valueOf((endTime - beginTime)/ 1000));

            logService.save(log);
        }
    }
}
