package ${service_impl_package};

import cn.zhimadi.txs.common.dao.BaseDao;
import cn.zhimadi.txs.common.service.BaseServiceImpl;
import ${dao_package}.${entity_name}Dao;
import ${entity_package}.${entity_name};
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * ${entity_description}接口实现类
 * @author : ${author_name} / ${author_name}@zhimadi.cn
 * @version : 1.0
 */
@Service
public class ${entity_name}ServiceImpl extends BaseServiceImpl<${entity_name}> implements ${entity_name}Service {

    /**
    * ${entity_name} dao
    */
    @Resource
    private ${entity_name}Dao ${lower_entity_name}Dao;


    @Override
    protected BaseDao<${entity_name}> getDao() {
        return ${lower_entity_name}Dao;
    }
}
