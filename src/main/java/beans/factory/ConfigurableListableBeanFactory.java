package beans.factory;

import beans.BeansException;
import beans.factory.config.AutowireCapableBeanFactory;
import beans.factory.config.BeanDefinition;
import beans.factory.config.ConfigurableBeanFactory;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons() throws BeansException;
}
