package bean;

import beans.factory.DisposableBean;
import beans.factory.InitializingBean;

public class UserService implements InitializingBean, DisposableBean {
    private String id;
    private UserDao userDao;
    private String company;
    private String location;

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
        return userDao.queryUserName(id)+", "+company+", "+location;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("执行：UserService.destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行：UserService.afterPropertiesSet");
    }
}
