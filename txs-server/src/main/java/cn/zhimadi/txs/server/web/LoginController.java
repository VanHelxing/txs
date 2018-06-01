package cn.zhimadi.txs.server.web;

import cn.zhimadi.txs.server.dao.BranchDao;
import cn.zhimadi.txs.server.entity.Branch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class LoginController {

    @Resource
    private BranchDao branchDao;


    @GetMapping("/login")
    public String login(){
        return "测试成功！";
    }

    @PostMapping("/create")
    public Branch create(Branch branch){
        return branchDao.save(branch);
    }

    @GetMapping("/findAll")
    public List<Branch> findAll(){
        return branchDao.findAll();
    }
}
