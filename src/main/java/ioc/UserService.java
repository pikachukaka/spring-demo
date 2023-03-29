package ioc;

public class UserService {
    private String id;
    private UserDao userDao;

    public UserService() {
    }

    public UserService(String id, UserDao userDao) {
        this.id = id;
        this.userDao = userDao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String queryInfo(){
        return userDao.queryUserName(id);
    }
}
