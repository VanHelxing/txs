package cn.zhimadi.txs.security.service;

import cn.zhimadi.txs.common.dao.BaseDao;
import cn.zhimadi.txs.common.service.BaseServiceImpl;
import cn.zhimadi.txs.security.dao.ResourcesDao;
import cn.zhimadi.txs.security.entity.Resources;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 资源接口实现类
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
@Service
public class ResourcesServiceImpl extends BaseServiceImpl<Resources> implements ResourcesService {

    /**
    * Resources cn.zhimadi.txs.monitor.dao
    */
    @Autowired
    private ResourcesDao resourcesDao;


    @Override
    protected BaseDao<Resources> getDao() {
        return resourcesDao;
    }
}
