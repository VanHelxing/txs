package cn.zhimadi.txs.security.dto;

import lombok.Data;

/**
 * 会话交互对象
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
@Data
public class SessionDTO {

    /** 会话编号 */
    private String id;

    /** 用户编号 */
    private String userId;

    /** 用户名称 */
    private String userName;

    /** 用户IP */
    private String remoteIp;

    /** 用户主机名 */
    private String remoteName;

    /** 机构 */
    private String orgId;
}
