package cn.zhimadi.txs.common.pojo;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * 自定义审计对象
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
public class CustomAuditorAware implements AuditorAware<String> {


    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("范海辛");
    }
}
