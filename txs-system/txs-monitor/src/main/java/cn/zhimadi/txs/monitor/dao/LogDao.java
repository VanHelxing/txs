package cn.zhimadi.txs.monitor.dao;

import cn.zhimadi.txs.common.dao.BaseDao;
import cn.zhimadi.txs.monitor.entity.Log;
import org.springframework.stereotype.Repository;


/**
 * 日志持久类
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
@Repository
public interface LogDao extends BaseDao<Log> {
}
