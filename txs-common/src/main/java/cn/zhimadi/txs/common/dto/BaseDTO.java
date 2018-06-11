package cn.zhimadi.txs.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础DTO
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
@Data
public class BaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 编号 */
    private String id;

    /** 机构 */
    private String orgId;

    /** 创建时间 */
    private Date createDate;

    /** 修改时间 */
    private Date updateDate;

    /**
     * 用户状态
     */
    private String status;

    /**
     * 版本号
     */
    private Long version;

    /**
     * 是否启用
     */
    protected Integer state;

    /**
     * 是否启用
     */
    private Boolean enable;

    private Boolean isNew;

    protected Boolean enable() {
        return getState() == 0;
    }

    public String getStatus() {
        return getState() == 0 ? "是" : "否";
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
