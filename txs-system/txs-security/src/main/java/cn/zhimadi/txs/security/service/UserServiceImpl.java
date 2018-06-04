package cn.zhimadi.txs.security.service;

import cn.zhimadi.txs.common.dao.BaseDao;
import cn.zhimadi.txs.common.service.BaseServiceImpl;
import cn.zhimadi.txs.security.dao.UserDao;
import cn.zhimadi.txs.security.entity.User;
import cn.zhimadi.txs.security.util.EncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * 用户接口实现类
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 *
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    protected BaseDao<User> getDao() {
        return userDao;
    }

    /**
     * 用过用户名称查询用户信息
     *
     * @param userName
     * @return the user
     * @author : yangjunqing / 2018-06-04
     */
    @Override
    public User findByUserName(String userName) {
        return userDao.findByUserName(userName);
    }


    /**
     * 保存用户信息
     *
     * @param entity the cn.zhimadi.txs.monitor.entity
     * @return the s
     * @author : yangjunqing / 2018-06-01
     */
    @Override
    public <S extends User> S save(S entity) {
        entity.setPassword(EncryptUtils.BCrypt(entity.getPassword()));  //对用户密码加密
        return userDao.save(entity);
    }

    /**
     * Save and flush t.
     *
     * @param entity the cn.zhimadi.txs.monitor.entity
     * @return the t
     * @author : yangjunqing / 2018-06-01
     */
    @Override
    public User saveAndFlush(User entity) {
        entity.setPassword(EncryptUtils.BCrypt(entity.getPassword()));  //对用户密码加密
        return userDao.saveAndFlush(entity);
    }

    /**
     * Save list.
     *
     * @param entities the entities
     * @return the list
     * @author : yangjunqing / 2018-06-01
     */
    @Override
    public <S extends User> List<S> save(Iterable<S> entities) {
        List<S> users = new ArrayList<>();
        for (Iterator<S> ite = entities.iterator(); ite.hasNext();){
            S s = ite.next();
            s.setPassword(EncryptUtils.BCrypt(s.getPassword())); //对用户密码加密
            users.add(s);
        }
        return userDao.saveAll(users);
    }
}
