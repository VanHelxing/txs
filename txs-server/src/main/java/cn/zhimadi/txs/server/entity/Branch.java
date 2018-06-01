package cn.zhimadi.txs.server.entity;

import cn.zhimadi.txs.common.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 机构
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
@Data
@Entity
@Table(name = "txs_branch")
public class Branch extends BaseEntity {

    /** 机构编号 */
    @Column(name = "org_id", unique = true)
    private String orgId;

    /** 机构名称 */
    private String orgName;

    /** 机构类型 */
    private String orgType;

    /** 备注 */
    private String remark;

    /** 父级 */
    private String pId;
}
