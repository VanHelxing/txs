package cn.zhimadi.txs.security.web;


import cn.zhimadi.txs.common.pojo.ResponseData;
import cn.zhimadi.txs.common.search.DataTable;
import cn.zhimadi.txs.common.search.SearchResponse;
import cn.zhimadi.txs.common.util.StringUtils;
import cn.zhimadi.txs.common.web.controller.BaseController;
import cn.zhimadi.txs.security.dto.SessionDTO;
import cn.zhimadi.txs.security.entity.Session;
import cn.zhimadi.txs.security.pojo.SecurityUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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



    /**
     * 请求数据列表页面
     * @param model
     * @return
     */
    @GetMapping
    public String list(Model model){
       addEntityParam(Session.class, model);
       return getListPagePath(Session.class, "system");
    }

    /**
     * 查询数据列表
     * @param dataTable
     * @return
     */
    @PostMapping("list")
    @ResponseBody
    public Map<String, Object> query(DataTable dataTable){
        Map<String, Object> map = new HashMap<>();
        List<Session> sessions = new ArrayList<>();
        List<Object> objects = sessionRegistry.getAllPrincipals();

        for (Object object : objects){

            Session session = new Session();
            SecurityUser user = (SecurityUser)object;
            //由于只允许单点登录,informations的size只存在一个information
            List<SessionInformation> informations = sessionRegistry.getAllSessions(object, false);

            session.setUserId(user.getId());
            session.setUserName(user.getUserName());
            session.setOrgId(user.getOrgId());
            if (informations != null && informations.size() == 1){
                session.setId(informations.get(0).getSessionId());
                sessions.add(session);
            }
        }


        //根据dataTable查询数据记录
        SearchResponse<Session> searchResponse = new SearchResponse<>();
        map.put(PARAM_DRAW, dataTable.getDraw());
        map.put(PARAM_RECORDS_TOTAL, sessions.size());
        map.put(PARAM_RECORDS_FILTERED, sessions.size());
        map.put(PARAM_DATA, convert(sessions));
        return map;
    }


    /**
     * 强制下线
     * @param ids
     * @return
     */
    @PostMapping("forceout")
    @ResponseBody
    public ResponseData forceout(String ids){
        String sessionIds[] =  StringUtils.split(ids, ",");
        for (String sessionId : sessionIds){
            SessionInformation information = sessionRegistry.getSessionInformation(sessionId);
            information.expireNow();
        }
        return ResponseData.ok();
    }


    /**
     * Entity转DTO
     * @param sessions
     * @return
     */
    private List<SessionDTO> convert(List<Session> sessions){
        List<SessionDTO> dtos = new ArrayList<>();
        for (Session session : sessions){
            SessionDTO dto = new SessionDTO();
            BeanUtils.copyProperties(session, dto);
            dtos.add(dto);
        }
        return dtos;
    }
}
