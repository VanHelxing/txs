package cn.zhimadi.txs.security.service;

import cn.zhimadi.txs.common.dao.BaseDao;
import cn.zhimadi.txs.common.service.BaseServiceImpl;
import cn.zhimadi.txs.security.dao.UserDao;
import cn.zhimadi.txs.security.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户接口实现类
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    /**
     * User dao
     */
    @Resource
    private UserDao userDao;


    @Override
    protected BaseDao<User> getDao() {
        return userDao;
    }
}
