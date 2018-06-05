package cn.zhimadi.txs.security.dto;

import cn.zhimadi.txs.common.dto.BaseDTO;
import lombok.Data;


/**
 * 用户交互对象
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
@Data
public class UserDTO extends BaseDTO {

    /** 用户名 */
    private String username;

    /** 用户口令 */
    private String password;

}
