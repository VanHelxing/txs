package cn.zhimadi.txs.security.pojo;

import cn.zhimadi.txs.security.entity.User;
import cn.zhimadi.txs.security.entity.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;


/**
 * 自定义用户信息
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
public class SecurityUser extends User implements UserDetails {


    private static final long serialVersionUID = 6200117103056216966L;

    public SecurityUser(User user) {
        if (user != null){
            this.setUserName(user.getUserName());
            this.setPassword(user.getPassword());
            this.setOrgId(user.getOrgId());
            this.setEmail(user.getEmail());
            this.setTel(user.getTel());
            this.setUserRoles(user.getUserRoles());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        Set<UserRole> userRoles = this.getUserRoles();

        if (userRoles != null && userRoles.size() != 0){
            for (UserRole userRole : userRoles){
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.getRole().getRoleName());
                authorities.add(authority);
            }
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return super.getUserName();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
