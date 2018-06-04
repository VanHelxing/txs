package cn.zhimadi.txs.common.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 请求工具类
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
public class RequestUtils {


    /**
     * 是否是不返回视图的请求
     *
     * @param request the request
     * @return the boolean
     * @author : yangjunqing / 2018-05-21
     */
    public static boolean isRestRequest(HttpServletRequest request){
        String accept = request.getHeader("accept");

        if (accept != null && accept.indexOf("application/json") != -1){
            return true;
        }

        String xRequestedWith = request.getHeader("X-Requested-With");
        if (xRequestedWith != null && xRequestedWith.indexOf("XMLHttpRequest") != -1){
            return true;
        }

        String uri = request.getRequestURI();
        if (StringUtils.inStringIgnoreCase(uri, ".json", ".xml")){
            return true;
        }

        String ajax = request.getParameter("__ajax");
        if (StringUtils.inStringIgnoreCase(ajax, "json", "xml")){
            return true;
        }

        return false;
    }
}
