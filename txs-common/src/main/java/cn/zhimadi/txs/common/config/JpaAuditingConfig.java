package cn.zhimadi.txs.common.config;

import cn.zhimadi.txs.common.pojo.CustomAuditorAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * 开启JPA审计功能
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
@Configuration
@EnableJpaAuditing
public class JpaAuditingConfig {


    @Bean
    public AuditorAware<String> auditorProvider(){
        return new CustomAuditorAware();
    }
}
