package cn.zhimadi.txs.dict.service;

import cn.zhimadi.txs.common.dao.BaseDao;
import cn.zhimadi.txs.common.service.BaseServiceImpl;
import cn.zhimadi.txs.dict.dao.DictDetailDao;
import cn.zhimadi.txs.dict.entity.DictDetail;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 字典明细接口实现类
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
@Service
public class DictDetailServiceImpl extends BaseServiceImpl<DictDetail> implements DictDetailService {

    /**
    * DictDetail cn.zhimadi.txs.monitor.dao
    */
    @Autowired
    private DictDetailDao dictDetailDao;


    @Override
    protected BaseDao<DictDetail> getDao() {
        return dictDetailDao;
    }
}
