package cn.zhimadi.txs.security.entity;

import cn.zhimadi.txs.common.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
@Data
@Entity
@Table(name = "txs_user")
public class User extends BaseEntity {

    private static final long serialVersionUID = -8811866642682161237L;
    /**
     * 用户名称
     */
    private String userName;

    /**
     * 密码
     */
    @Column(name = "password", updatable = false)
    private String password;

    /**
     * 机构
     */
    private String orgId;

    /**
     * Email
     */
    private String email;

    /**
     * 联系电话
     */
    private String tel;

    /** 用户角色 */
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private Set<UserRole> userRoles = new HashSet<>();
}
