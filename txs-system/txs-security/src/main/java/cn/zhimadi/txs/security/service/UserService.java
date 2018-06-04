package cn.zhimadi.txs.security.service;

import cn.zhimadi.txs.common.service.BaseService;
import cn.zhimadi.txs.security.entity.User;

/**
 * 用户接口
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
public interface UserService extends BaseService<User> {

    /**
     * 用过用户名称查询用户信息
     *
     * @return the user
     * @author : yangjunqing / 2018-06-04
     */
    public User findByUserName(String userName);
}
