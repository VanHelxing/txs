package cn.zhimadi.txs.common.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * 基类entity
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable {

    /** 唯一ID */
    @Id
    @GenericGenerator(name = "twitter-id", strategy = "cn.zhimadi.txs.common.pojo.TwitterIdGennerate")
    @GeneratedValue(generator = "twitter-id")
    private String id;

    /** 创建人 */
    @CreatedBy
    @Column(name = "create_by", updatable = false)
    private String createBy;

    /** 创建时间 */
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", updatable = false)
    private Date createTime;

    /** 最后一次修改人 */
    @LastModifiedBy
    private String lastmodifyBy;

    /** 最后一次修改时间 */
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastmodifyTime;

    /** 版本 */
    @Version
    private Long version;

    /** 状态 */
    @Column(name = "status", columnDefinition = "char(1) default '0'")
    private String status;
}
