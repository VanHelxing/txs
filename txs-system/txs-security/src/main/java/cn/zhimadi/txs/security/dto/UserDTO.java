package cn.zhimadi.txs.security.dto;

import cn.zhimadi.txs.common.dto.BaseDTO;
import cn.zhimadi.txs.security.entity.Role;
import lombok.Data;

import java.util.List;


/**
 * 用户交互对象
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
@Data
public class UserDTO extends BaseDTO {

    /** 用户名 */
    private String userName;

    /** 用户口令 */
    private String password;

    /** 用户角色 */
    private List<Role> roles;

    /** 角色IDs */
    private List<String> roleIds;

}
