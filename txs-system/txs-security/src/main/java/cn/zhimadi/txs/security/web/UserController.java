package cn.zhimadi.txs.security.web;

import cn.zhimadi.txs.common.search.DataTable;
import cn.zhimadi.txs.common.search.SearchResponse;
import cn.zhimadi.txs.common.web.controller.BaseController;
import cn.zhimadi.txs.security.dto.UserDTO;
import cn.zhimadi.txs.security.entity.User;
import cn.zhimadi.txs.security.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户Controller
 * @author : yangjunqing / yangjunqing@zhimadi.cn
 * @version : 1.0
 */
@Controller
@RequestMapping("system/security/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;


    /**
     * 请求数据列表界面
     *
     * @param model the model
     * @return the string
     * @author : yangjunqing / 2018-06-05
     */
    @GetMapping
    public String list(Model model){
        //设置页面实体参数(路径，消息，权限前缀)
        addEntityParam(User.class, model);
        return getListPagePath(User.class, "system");
    }

    /**
     * 查询数据列表
     * @param dataTable
     * @return
     */
    @PostMapping("list")
    public Map<String, Object> query(DataTable dataTable){
        Map<String, Object> map = new HashMap<>();
        //根据dataTable查询数据记录
        SearchResponse<User> searchResponse = userService.findAll(dataTable, User.class);
        map.put(PARAM_DRAW, dataTable.getDraw());
        map.put(PARAM_RECORDS_TOTAL, searchResponse.getRecordsTotal());
        map.put(PARAM_RECORDS_FILTERED, searchResponse.getRecordsFiltered());
        map.put(PARAM_DATA, convert(searchResponse.getData()));
        return map;
    }

    /**
     * Entity转DTO
     * @param users
     * @return
     */
    private List<UserDTO> convert(List<User> users){
        List<UserDTO> dtos = new ArrayList<>();
        for (User user : users){
            UserDTO dto = new UserDTO();
            BeanUtils.copyProperties(user, dto);
            dtos.add(dto);
        }
        return dtos;
    }





}
