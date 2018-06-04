package cn.zhimadi.txs.server.config;

import cn.zhimadi.txs.common.constant.Constants;
import cn.zhimadi.txs.server.interceptor.LogInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * spring boot自定义配置类继承了WebMvcConfigurationSupport自动配置的静态资源路径就会失效
 * 因为项目类路径缺少 WebMvcConfigurationSupport类型的Bean时才会生效
 *
 * spring5.0后实现WebMvcConfigurer就可以配置额外的mvc配置
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    /**
     * 配置拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor()).excludePathPatterns(Constants.FILTER_LOG_PATH);
    }


    /**
     * 配置视图Controller
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("logout").setViewName("logout");
        registry.addViewController("/").setViewName("home");
    }
}
