package cn.echo.springframework.test.bean;

import beans.factory.annotion.Autowired;
import beans.factory.annotion.Value;
import stereotype.Component;

import java.util.Random;
@Component("userService")
public class UserService implements IUserService {

    @Value("${token}")
    private String token;

    @Autowired
    private UserDao userDao;

    public UserService() {
    }

    public UserService(String token, UserDao userDao) {
        this.token = token;
        this.userDao = userDao;
    }

    @Override
    public String queryInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //return "test";
        return  userDao.queryUserName("111") + "，" +token;//
    }



    @Override
    public String register(String userName) {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "register...";
    }
}
