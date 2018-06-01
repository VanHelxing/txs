package cn.zhimadi.txs.security.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 角色资源
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
@Data
@Entity
@Table(name = "txs_role_resource")
public class RoleResource {

    /**
     * 资源
     */
    private String resourceId;

    /**
     * 角色
     */
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Role.class)
    @JoinColumn(name = "role_id")
    private Role role;

    /**
     * 机构编号
     */
    private String orgId;
}
