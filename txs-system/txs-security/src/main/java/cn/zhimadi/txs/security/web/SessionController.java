package cn.zhimadi.txs.security.web;


import cn.zhimadi.txs.common.web.controller.BaseController;
import cn.zhimadi.txs.security.entity.Session;
import cn.zhimadi.txs.security.pojo.SecurityUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 会话Controller
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
@Controller
@RequestMapping("security/session")
public class SessionController extends BaseController {

    private static final Logger logger = LogManager.getLogger(SessionController.class);

    @Autowired
    private SessionRegistry sessionRegistry;

    @Autowired
    private RedisOperationsSessionRepository redisOperationsSessionRepository;


    /**
     * 请求数据列表页面
     * @param model
     * @return
     */
    @GetMapping
    public String list(Model model){

        List<Object> objects = sessionRegistry.getAllPrincipals();
        logger.info("sessionRegistry统计的在线人数: " + objects.size());




//        for (Object user : objects){
//            logger.info("用户信息: " + ((SecurityUser)user).getUsername());
//        }

       addEntityParam(Session.class, model);
       return getListPagePath(Session.class, "system");
    }
}
