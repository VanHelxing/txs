package cn.zhimadi.txs.security.dao;

import cn.zhimadi.txs.common.dao.BaseDao;
import cn.zhimadi.txs.security.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 用户持久类
 *
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
@Repository
public interface UserDao extends BaseDao<User> {


    /**
     * 通过用户名称查询用户信息
     *
     * @return the user
     * @author : yangjunqing / 2018-06-04
     */
    public User findByUserName(String userName);

    /**
     * 通过用户ID查询用户拥有的角色id
     * @param id
     * @return
     */
    @Query("select ur.role_id from txs_user_role ur where ur.user_id = :id")
    public List<String> getRoleIds(String id);
}
