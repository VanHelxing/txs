package cn.zhimadi.txs.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SessionService 实现类
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获取所有会话信息
     * @return
     */
    @Override
    public List<Object> getAllPrincipals() {

        return null;
    }

    /**
     * 获取所有session信息
     * @param o
     * @param b
     * @return
     */
    @Override
    public List<SessionInformation> getAllSessions(Object o, boolean b) {
        return null;
    }

    /**
     * 获得一个session信息
     * @param s
     * @return
     */
    @Override
    public SessionInformation getSessionInformation(String s) {
        return null;
    }

    /**
     * 刷新一个会话
     * @param s
     */
    @Override
    public void refreshLastRequest(String s) {

    }

    /**
     * 新增一个会话
     * @param s
     * @param o
     */
    @Override
    public void registerNewSession(String s, Object o) {

    }

    /**
     * 移除一个会话
     * @param s
     */
    @Override
    public void removeSessionInformation(String s) {

    }
}
