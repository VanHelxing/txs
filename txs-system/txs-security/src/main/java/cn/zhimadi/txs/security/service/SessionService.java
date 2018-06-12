package cn.zhimadi.txs.security.service;


import org.springframework.security.core.session.SessionRegistry;

/**
 * 由于SessionRegistry只能监听Web服务器启动后的Session创建和销毁, 如下情况无法记录准确的在线人数:
 *
 *  1. web服务器重启, redis保存的session依然有效的会话没有记录
 *  2. 手工在redis端踢出会话时
 *  3. 分布式
 *
 *  解决措施,从redis中获取所有的session记录
 *
 * Session service 接口
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
public interface SessionService extends SessionRegistry{

}
