package cn.zhimadi.txs.security.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 加密工具类
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
public class EncryptUtils {


    /**
     * BCrypt加密(Spring security读取密码后使用BCrypt加密验证)
     */
    public static String BCrypt(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4);
        return encoder.encode(password);
    }
}
