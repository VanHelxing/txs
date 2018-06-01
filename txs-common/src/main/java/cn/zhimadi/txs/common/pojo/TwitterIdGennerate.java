package cn.zhimadi.txs.common.pojo;

import cn.zhimadi.txs.common.entity.BaseEntity;
import cn.zhimadi.txs.common.util.StringUtils;
import cn.zhimadi.txs.common.util.idgen.IdUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

/**
 * 主键生成策略
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
public class TwitterIdGennerate implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        if (o == null){
            throw new IllegalArgumentException("实体对象不能为空！");
        }
        if (!(o instanceof BaseEntity)){
            throw new IllegalArgumentException("实体对象必须要继承BaseEntity对象！");
        }

        BaseEntity baseEntity = (BaseEntity) o;
        //新增
        if (StringUtils.isEmpty(baseEntity.getId())){
            return IdUtils.twitterId();
        }
        //更新
        else{
            return baseEntity.getId();
        }
    }
}
