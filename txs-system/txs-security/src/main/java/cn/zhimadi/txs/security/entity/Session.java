package cn.zhimadi.txs.security.entity;

import lombok.Data;


/**
 * 会话信息
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
@Data
public class Session {

    /** 会话ID */
    private String id;

    /** 用户编号 */
    private String userId;

    /** 用户姓名 */
    private String userName;

    /** 用户主机IP */
    private String remoteIp;

    /** 用户主机名称 */
    private String remoteName;

    /** 剩余过期时间(单位/秒) */
    private Long expireTime;

    /** 机构 */
    private String orgId;

}
