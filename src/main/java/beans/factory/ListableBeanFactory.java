package beans.factory;

import beans.BeansException;

import java.util.Map;

public interface ListableBeanFactory extends BeanFactory{
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * Return the names of all beans defined in this registry.
     *
     * 返回注册表中所有的Bean名称
     */
    String[] getBeanDefinitionNames();

}
