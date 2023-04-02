package bean;

import java.util.HashMap;
import java.util.Map;

public class UserDao {
    private static Map<String,String> hashMap=new HashMap<>();
    static {
        hashMap.put("1","echo");
        hashMap.put("2","lily");
    }
    public String queryUserName(String id){
        return hashMap.get(id);
    }
}
