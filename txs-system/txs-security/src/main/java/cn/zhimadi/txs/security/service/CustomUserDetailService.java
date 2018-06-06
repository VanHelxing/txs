package cn.zhimadi.txs.security.service;

import cn.zhimadi.txs.security.entity.User;
import cn.zhimadi.txs.security.pojo.SecurityUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户信息接口
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
@Service
public class CustomUserDetailService implements UserDetailsService {

    @Resource
    private UserService userService;


    /**
     * 用户登录提交的表单会调用此方法，验证用户信息和权限
     * @param userName
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.findByUserName(userName);
        if (user == null){
            throw new UsernameNotFoundException("用户名【"+ userName +"】没有找到!");
        }

        SecurityUser securityUser = new SecurityUser(user);
        return  securityUser;
    }
}
