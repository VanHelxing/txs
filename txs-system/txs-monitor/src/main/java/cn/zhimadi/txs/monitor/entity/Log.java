package cn.zhimadi.txs.monitor.entity;

import cn.zhimadi.txs.common.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 系统日志
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
@Data
@Entity
@Table(name = "txs_log")
@DynamicInsert
@DynamicUpdate
public class Log extends BaseEntity {

    private static final long serialVersionUID = -3400004761113672013L;
    /** 请求类型(system, api) */
    private String requestTpe;

    /** 请求路径 */
    private String requestUri;

    /** 请求方法 */
    private String requestMethod;

    /** 请求数据 */
    private String requestData;

    /** 客户端地址 */
    private String requestAddr;

    /** 响应状态 */
    private String responseStatus;

    /** 响应数据 */
    private String responseData;

    /** 处理时间 */
    private String handleTime;
}
