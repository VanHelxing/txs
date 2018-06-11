package cn.zhimadi.txs.security;

import cn.zhimadi.txs.security.entity.User;
import cn.zhimadi.txs.security.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    private UserService userService;

    @Test
    public void addUser(){
        User user = new User();
        user.setUserName("范海辛");
        user.setPassword("123456");
        user.setState(0);
        user.setOrgId("00000");
        userService.save(user);

        User user1 = new User();
        user1.setUserName("陈宗");
        user1.setPassword("123456");
        user1.setState(0);
        user1.setOrgId("00000");
        userService.save(user1);
    }

    @Test
    public void update(){
        User user = new User();
        user.setId("1004190591841755136");
        user.setUserName("update");
        user.setVersion(0l);
        userService.update(user);
    }
}
