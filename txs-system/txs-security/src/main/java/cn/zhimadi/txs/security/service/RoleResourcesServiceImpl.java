package cn.zhimadi.txs.security.service;

import cn.zhimadi.txs.common.dao.BaseDao;
import cn.zhimadi.txs.common.service.BaseServiceImpl;
import cn.zhimadi.txs.security.dao.RoleResourcesDao;
import cn.zhimadi.txs.security.entity.RoleResources;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 角色资源接口实现类
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
@Service
public class RoleResourcesServiceImpl extends BaseServiceImpl<RoleResources> implements RoleResourcesService {

    /**
    * RoleResources cn.zhimadi.txs.monitor.dao
    */
    @Autowired
    private RoleResourcesDao roleResourcesDao;


    @Override
    protected BaseDao<RoleResources> getDao() {
        return roleResourcesDao;
    }
}
