package cn.zhimadi.txs.monitor.service;

import cn.zhimadi.txs.common.dao.BaseDao;
import cn.zhimadi.txs.common.service.BaseServiceImpl;
import cn.zhimadi.txs.monitor.dao.LogDao;
import cn.zhimadi.txs.monitor.entity.Log;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 日志接口实现类
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
@Service("logService")
public class LogServiceImpl extends BaseServiceImpl<Log> implements LogService {

    /**
    * Log cn.zhimadi.txs.monitor.dao
    */
    @Autowired
    private LogDao logDao;


    @Override
    protected BaseDao<Log> getDao() {
        return logDao;
    }
}
