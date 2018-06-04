package cn.zhimadi.txs.security.entity;

import cn.zhimadi.txs.common.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 资源
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
@Data
@Entity
@Table(name = "txs_resource")
public class Resources extends BaseEntity {

    private static final long serialVersionUID = 364919753740503465L;
    /**
     * 资源名称
     */
    private String resourceName;

    /**
     * 功能名称
     */
    private String methodName;

    /**
     * 功能路径
     */
    private String methodPath;

    /**
     * 备注
     */
    private String remark;

    /**
     * 角色资源
     */
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "resourceId")
    private Set<RoleResources> roleResources = new HashSet<>();
}
