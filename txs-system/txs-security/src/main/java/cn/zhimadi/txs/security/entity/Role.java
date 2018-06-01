package cn.zhimadi.txs.security.entity;

import cn.zhimadi.txs.common.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 角色
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
@Data
@Entity
@Table(name = "txs_role")
public class Role extends BaseEntity {

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 机构
     */
    private String orgId;

    /**
     * 备注
     */
    private String remark;
}
