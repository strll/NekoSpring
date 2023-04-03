package bean;

public class UserService {
    private String uId;
    private String company;
    private String location;
    private UserDao userDao;

    public String queryUserInfo() {
        System.out.println("这是方法里面的System.out.println 查询用户信息：" + userDao.queryUserName(uId));
        return "查询用户信息：" + userDao.queryUserName(uId);
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

}
