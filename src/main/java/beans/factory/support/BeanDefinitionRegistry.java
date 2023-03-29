package beans.factory.support;

import beans.BeansException;
import beans.factory.config.BeanDefinition;

/**
 * BeanDefinition注册表接口，定义注册BeanDefinition的方法。
 */
public interface BeanDefinitionRegistry {
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;
    boolean containsBeanDefinition(String beanName);
    String[] getBeanDefinitionNames();
}
