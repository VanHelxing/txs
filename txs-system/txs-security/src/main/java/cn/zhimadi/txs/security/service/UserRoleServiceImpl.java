package cn.zhimadi.txs.security.service;

import cn.zhimadi.txs.common.dao.BaseDao;
import cn.zhimadi.txs.common.service.BaseServiceImpl;
import cn.zhimadi.txs.security.dao.UserRoleDao;
import cn.zhimadi.txs.security.entity.UserRole;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 用户角色接口实现类
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole> implements UserRoleService {

    /**
    * UserRole cn.zhimadi.txs.monitor.dao
    */
    @Autowired
    private UserRoleDao userRoleDao;


    @Override
    protected BaseDao<UserRole> getDao() {
        return userRoleDao;
    }
}
