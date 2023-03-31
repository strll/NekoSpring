package bean;

public class UserService {
    private String name;

    public UserService(String name) {
        this.name = name;
    }

    public void queryUserInfo() {
        System.out.println("UserService的queryUserInfo方法执行");
    }
}
