package cn.zhimadi.txs.security.dao;

import cn.zhimadi.txs.common.dao.BaseDao;
import cn.zhimadi.txs.security.entity.User;
import org.springframework.stereotype.Repository;


/**
 * 用户持久类
 *
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
@Repository
public interface UserDao extends BaseDao<User> {


    /**
     * 用过用户名称查询用户信息
     *
     * @return the user
     * @author : yangjunqing / 2018-06-04
     */
    public User findByUserName(String userName);
}
