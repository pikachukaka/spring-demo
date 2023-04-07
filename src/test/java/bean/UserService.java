package bean;

import beans.BeansException;
import beans.factory.*;
import context.ApplicationContext;
import context.ApplicationContextAware;

import java.util.Random;

public class UserService implements IUserService {

    @Override
    public String queryInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "echo";
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
