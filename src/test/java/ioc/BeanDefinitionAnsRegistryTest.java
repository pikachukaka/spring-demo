package ioc;

import beans.factory.config.BeanDefinition;
import beans.factory.support.DefaultListableBeanFactory;
import org.junit.jupiter.api.Test;

public class BeanDefinitionAnsRegistryTest {
    @Test
    public void testBeanFactory()throws Exception{
        DefaultListableBeanFactory beanFactory=new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(HelloService.class);
        beanFactory.registerBeanDefinition("helloService",beanDefinition);
        HelloService helloService =(HelloService) beanFactory.getBean("helloService");
        helloService.sayHello();
    }
}
