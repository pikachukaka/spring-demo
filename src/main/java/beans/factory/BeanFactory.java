package beans.factory;

import beans.BeansException;

public interface BeanFactory {
    Object getBean(String name) throws BeansException;
}
