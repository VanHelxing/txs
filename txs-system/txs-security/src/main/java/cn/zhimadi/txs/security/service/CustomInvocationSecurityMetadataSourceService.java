package cn.zhimadi.txs.security.service;

import cn.zhimadi.txs.security.dao.ResourcesDao;
import cn.zhimadi.txs.security.entity.Resources;
import cn.zhimadi.txs.security.entity.RoleResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 加载资源信息 (这里还有待改进,需要将HashMap缓存在数据字典中,实时修改实时更新)
 *
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
@Service
public class CustomInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private ResourcesDao resourcesDao;

    /**
     * 权限信息 (key - url, value - roleNames)
     */
    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

    /**
     * 加载数据库中所有的资源信息
     *
     * @author : yangjunqing / 2018-05-30
     */
    private void loadResources(){
        List<Resources> resources = resourcesDao.findAll();
        if (resources != null && resources.size() > 0){
            resourceMap = new HashMap<>();
            //遍历数据库中所有的资源
            for (Resources resource : resources){
                Collection<RoleResources> roleResources = resource.getRoleResources();
                if (roleResources != null && roleResources.size() > 0){
                    Collection<ConfigAttribute> roles = new ArrayList<ConfigAttribute>();
                    //添加资源所需要的角色，目前只添加了角色名称
                    for (RoleResources roleResource : roleResources){
                        SecurityConfig config = new SecurityConfig(roleResource.getRole().getRoleName());
                        roles.add(config);
                    }
                    resourceMap.put(resource.getMethodPath(), roles);
                }
            }
        }
    }

    /**
     * 匹配访问资源需要的角色
     * @param object
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

        //第一次验证时加载所有资源权限
        if (resourceMap == null){
            synchronized (CustomInvocationSecurityMetadataSourceService.class){
                if (resourceMap == null){
                    loadResources();
                }
            }
        }

        //数据库没有分配任何资源权限
        if (resourceMap == null){
            return null;
        }

        //当前的请求匹配所需要的角色
        HttpServletRequest request = ((FilterInvocation)object).getHttpRequest();

        for (Iterator<String> ite = resourceMap.keySet().iterator(); ite.hasNext();){
            String resUrl = ite.next();
            AntPathRequestMatcher matcher = new AntPathRequestMatcher(resUrl);
            if (matcher.matches(request)){
                return resourceMap.get(resUrl);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return new ArrayList<ConfigAttribute>();
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
