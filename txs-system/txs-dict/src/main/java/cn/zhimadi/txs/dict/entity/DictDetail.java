package cn.zhimadi.txs.dict.entity;

import cn.zhimadi.txs.common.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 字典明细
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
@Data
@Entity
@Table(name = "sys_dict_detail")
public class DictDetail extends BaseEntity {

    private static final long serialVersionUID = 7318118142110514117L;

    /** 字典类型 */
    private String dictType;

    /** 节点名 */
    private String treeName;

    /** 节点值 */
    private String treeValue;

    /** 节点代码 */
    private String treeCode;

    /** 优先级 */
    private Integer treeLevel;

    /** 备注 */
    private String remark;
}
