package cn.zhimadi.txs.security.entity;

import lombok.Data;

import java.util.Map;

/**
 * 会话信息
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
@Data
public class Session {

    private String userId;

    private String userName;

    private String remoteIp;

    private String remoteName;

    private Long expireTime;

    private String orgId;

    private Map<String, String> roleMap;
}
