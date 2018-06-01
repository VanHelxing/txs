package cn.zhimadi.txs.security.dao;

import cn.zhimadi.txs.common.dao.BaseDao;
import cn.zhimadi.txs.security.entity.User;
import org.springframework.stereotype.Repository;


/**
 * 用户持久类
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
@Repository
public interface UserDao extends BaseDao<User> {
}
