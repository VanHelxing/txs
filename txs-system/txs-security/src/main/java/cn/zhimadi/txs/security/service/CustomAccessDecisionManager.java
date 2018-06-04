package cn.zhimadi.txs.security.service;

import cn.zhimadi.txs.common.constant.Constants;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;

/**
 * 权限验证
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
@Service
public class CustomAccessDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {

        //没有匹配到资源所需要的角色则放行
        if (configAttributes == null){
            return;
        }

        //遍历访问的资源需要的角色
        for (Iterator<ConfigAttribute> ite = configAttributes.iterator(); ite.hasNext();){
            ConfigAttribute ca = ite.next();
            String needRole = ((SecurityConfig)ca).getAttribute();

            //遍历当前登录的用户所拥有的角色并匹配
            for (GrantedAuthority authority : authentication.getAuthorities()){

                //系统管理员可以访问任何资源
                if (Constants.ROLE_ADMIN.equals(authority.getAuthority())){
                    return;
                }

                if (needRole.trim().equals(authority.getAuthority())){
                    return; //放行
                }
            }
        }
        throw new AccessDeniedException("权限不足!");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
