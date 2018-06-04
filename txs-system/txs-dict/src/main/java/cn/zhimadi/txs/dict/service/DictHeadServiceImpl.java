package cn.zhimadi.txs.dict.service;

import cn.zhimadi.txs.common.dao.BaseDao;
import cn.zhimadi.txs.common.service.BaseServiceImpl;
import cn.zhimadi.txs.dict.dao.DictHeadDao;
import cn.zhimadi.txs.dict.entity.DictHead;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 字典表头接口实现类
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
@Service
public class DictHeadServiceImpl extends BaseServiceImpl<DictHead> implements DictHeadService {

    /**
    * DictHead cn.zhimadi.txs.monitor.dao
    */
    @Autowired
    private DictHeadDao dictHeadDao;


    @Override
    protected BaseDao<DictHead> getDao() {
        return dictHeadDao;
    }
}
