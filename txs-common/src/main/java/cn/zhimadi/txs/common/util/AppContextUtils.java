package cn.zhimadi.txs.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Spring应用上下文
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
@Component
public class AppContextUtils implements ApplicationContextAware {

    public static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AppContextUtils.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过bean name获取容器中已创建的bean实例
     * @param name
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name){
        return (T) applicationContext.getBean(name);
    }
}
