package beans.factory;

import beans.BeansException;

public interface BeanFactory {
    Object getBean(String name) throws BeansException;
    //含入参信息，方便传递入参给构造函数实例化
    Object getBean(String name,Object... args) throws BeansException;
    <T> T getBean(String name, Class<T> requiredType) throws BeansException;
}
