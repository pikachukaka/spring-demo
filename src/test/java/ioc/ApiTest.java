package ioc;

import beans.factory.config.BeanDefinition;
import beans.factory.support.DefaultListableBeanFactory;
import org.junit.jupiter.api.Test;


public class ApiTest {
    @Test
    public void testBeanFactory(){
        //初始化beanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //注入bean
        BeanDefinition beanDefinition= new BeanDefinition(HelloService.class);
        beanFactory.registerBeanDefinition("helloService",beanDefinition);
        //获取bean
        HelloService helloService= (HelloService) beanFactory.getBean("helloService","hi");
        helloService.sayHello();
    }
}
