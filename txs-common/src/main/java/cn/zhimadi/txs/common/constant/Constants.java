package cn.zhimadi.txs.common.constant;

/**
 * 通用的常量
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
public class Constants {


    /**
     * 角色常量
     */
    public final static String ROLE_ADMIN = "ROLE_ADMIN"; //管理员角色


    /**
     * 不需要权限的路径资源
     */
    public final static String[] FILTER_AUTHENTICATED_PATH = {"/login", "/logout",
            "/swagger-ui.html", "/swagger-resources", "/v2/**", "/configuration/**",
            "/resource/**", "/smart-admin/**", "/webjars/**"
    };

    /**
     * 不需要日志监视的路径资源
     */
    public final static String[] FILTER_LOG_PATH = {"", "/", "/login", "/logout",
            "/swagger-ui.html", "/swagger-resources", "/v2/**", "/configuration/**",
            "/resource/**", "/smart-admin/**", "/webjars/**"
    };



    /**
     * 请求方式
     */
    public final static String REQUEST_API = "api";
    public final static String REQUEST_SYSTEM = "system";
}
