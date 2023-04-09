package cn.echo.springframework.test.bean;

import stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component("userDao")
public class UserDao {
    private static Map<String, String> hashMap = new HashMap<>();

    public UserDao() {
    }

    static {
        hashMap.put("111", "echo");

    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }
}
