package cn.zhimadi.txs.security.entity;

import cn.zhimadi.txs.common.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "txs_user_role")
public class UserRole extends BaseEntity {

    private static final long serialVersionUID = 3011726861277895595L;
    /**
     * 用户编号
     */
    private String userId;

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
