package cn.zhimadi.txs.security.config;

import cn.zhimadi.txs.common.constant.Constants;
import cn.zhimadi.txs.security.pojo.LoginSuccessHandler;
import cn.zhimadi.txs.security.service.CustomFilterSecurityInterceptor;
import cn.zhimadi.txs.security.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableRedisHttpSession
//@EnableGlobalMethodSecurity  //开启方法级别安全@Secured @PreAuthorize / @PostAuthorize
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Autowired
    private CustomFilterSecurityInterceptor customFilterSecurityInterceptor;

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;


    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(4);
    }

    @Bean
    public LoginSuccessHandler loginSuccessHandler(){
        return new LoginSuccessHandler();
    }

    @Bean
    public RememberMeServices rememberMeServices(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        PersistentTokenBasedRememberMeServices rememberMeServices =
                new PersistentTokenBasedRememberMeServices("INTERNAL_SECRET_KEY", customUserDetailService, tokenRepository);
        rememberMeServices.setParameter("remember-me");
        return rememberMeServices;
    }

    @Bean
    public SessionRegistry sessionRegistry(){
        return new SessionRegistryImpl();
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher(){
        return new HttpSessionEventPublisher();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
        auth.eraseCredentials(false);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .addFilterBefore(customFilterSecurityInterceptor, FilterSecurityInterceptor.class) //注入自定义的过滤器实现权限验证
            .authorizeRequests()
            .antMatchers("/**").permitAll() //所有的请求都不需要验证,正式发布时请注释
            .antMatchers(Constants.FILTER_AUTHENTICATED_PATH).permitAll() //不需要验证的路径
            .anyRequest().authenticated()
            .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error").permitAll()
                .successHandler(loginSuccessHandler())
            .and()
                .logout()
                .logoutSuccessUrl("/login").permitAll()
            .and()
                .rememberMe()
                .rememberMeServices(rememberMeServices())
                .key("INTERNAL_SECRET_KEY")
            .and()
                .sessionManagement().maximumSessions(1).expiredUrl("/login?expire")
                .sessionRegistry(sessionRegistry());

        //解决不允许显示在iframe的问题
        http.headers().frameOptions().disable();

        //暂时解决POST请求403
        http.csrf().disable();
    }

}
