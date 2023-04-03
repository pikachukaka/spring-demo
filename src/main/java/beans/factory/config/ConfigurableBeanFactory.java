package beans.factory.config;

import beans.factory.HierachicalBeanFactory;

public interface ConfigurableBeanFactory extends HierachicalBeanFactory,SingletonBeanRegistry {
    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
    void destroySingletons();
}
