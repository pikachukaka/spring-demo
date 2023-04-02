package beans.factory.config;

import beans.BeansException;
import beans.factory.ConfigurableListableBeanFactory;

public interface BeanFactoryPostProcessor {
    /**
     * 在 Bean 对象注册后但未实例化之前，对 Bean 的定义信息 BeanDefinition 执行修改操作。
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
