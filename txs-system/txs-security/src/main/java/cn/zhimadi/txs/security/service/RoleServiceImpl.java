package cn.zhimadi.txs.security.service;

import cn.zhimadi.txs.common.dao.BaseDao;
import cn.zhimadi.txs.common.service.BaseServiceImpl;
import cn.zhimadi.txs.security.dao.RoleDao;
import cn.zhimadi.txs.security.entity.Role;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 角色接口实现类
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

    /**
    * Role cn.zhimadi.txs.monitor.dao
    */
    @Autowired
    private RoleDao roleDao;


    @Override
    protected BaseDao<Role> getDao() {
        return roleDao;
    }
}
