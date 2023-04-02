package bean;

import java.util.HashMap;
import java.util.Map;



public class UserDao {

    private static Map<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("10001", "test1");
        hashMap.put("10002", "test12");
        hashMap.put("10003", "test123");
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }

}
