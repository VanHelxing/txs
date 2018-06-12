package cn.zhimadi.txs.security.web;

import cn.zhimadi.txs.common.exception.CustomException;
import cn.zhimadi.txs.common.pojo.ResponseData;
import cn.zhimadi.txs.common.search.DataTable;
import cn.zhimadi.txs.common.search.SearchResponse;
import cn.zhimadi.txs.common.util.StringUtils;
import cn.zhimadi.txs.common.web.controller.BaseController;
import cn.zhimadi.txs.security.dto.UserDTO;
import cn.zhimadi.txs.security.entity.Role;
import cn.zhimadi.txs.security.entity.User;
import cn.zhimadi.txs.security.service.RoleService;
import cn.zhimadi.txs.security.service.UserRoleService;
import cn.zhimadi.txs.security.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("security/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;


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
    @ResponseBody
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

    /**
     * 跳转数据添加页面
     * @param model
     * @return
     */
    @GetMapping("create")
    public String create(Model model){
        UserDTO dto = new UserDTO();
        //新增
        dto.setIsNew(true);

        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);

        //设置dto对象到页面
        model.addAttribute(PARAM_DTO, dto);
        //设置页面实体参数(路径，消息，权限前缀)
        addEntityParam(User.class, model);
        return getEditPagePath(User.class, "system");
    }

    /**
     * 跳转数据更新页面
     * @param model
     * @param id
     * @return
     */
    @GetMapping("update")
    public String update(Model model, @RequestParam("id") String id){
        if (StringUtils.isEmpty(id)){
            throw new CustomException("必须要传入id参数！");
        }

        UserDTO dto = new UserDTO();
        //更新
        dto.setIsNew(false);
        User user = userService.findById(id);
        BeanUtils.copyProperties(user, dto);
        dto.setRoleIds(userService.getRoleIds(id));

        //查询数据列表，返回给添加界面
        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);

        //设置dto对象到页面
        model.addAttribute(PARAM_DTO, dto);
        //设置页面实体参数(路径，消息，权限前缀)
        addEntityParam(User.class, model);
        return getEditPagePath(User.class, "system");
    }


    /**
     * 删除
     * @param ids
     * @return
     */
    @PostMapping("delete")
    @ResponseBody
    public ResponseData delete(String ids){
        //根据id数组查询对象列表
        Iterable<User> list = userService.findAll(StringUtils.splitToString(ids, ","));
        //删除对象列表
        userService.deleteInBatch(list);
        return ResponseData.ok();
    }


    /**
     * 保存
     * @param dto
     * @return
     */
    @PostMapping("save")
    @ResponseBody
    public ResponseData save(UserDTO dto) {

        User user = new User();
        BeanUtils.copyProperties(dto, user);

        if (dto.getEnable()){
            user.setState(0);
        }else {
            user.setState(1);
        }

        //新增
        if (StringUtils.isEmpty(user.getId())) {
            User temp = userService.findByUserName(user.getUserName());
            if (temp != null) {
                throw new CustomException("该用户名已存在!");
            }
            userService.save(user);
        }
        //修改
        else {
            userService.update(user);
        }

        return ResponseData.ok();
    }
}
