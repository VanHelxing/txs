package cn.zhimadi.txs.common.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;


/**
 * 基础Repository
 * @param <T> the type parameter
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
@NoRepositoryBean
public interface BaseDao<T> extends JpaRepository<T, String>, JpaSpecificationExecutor<T> {
}
