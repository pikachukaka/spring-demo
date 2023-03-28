package beans.factory.support;

import beans.BeansException;
import beans.factory.BeanFactory;
import beans.factory.config.BeanDefinition;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    @Override
    public Object getBean(String name) throws BeansException{
        Object bean = getSingleton(name);
        if(bean!=null) return bean;
        BeanDefinition beanDefinition = getDefinition(name);
        return creatBean(name,beanDefinition);
    }
    @Override
    public Object getBean(String name,Object... args) throws BeansException{
        Object bean = getSingleton(name);
        if(bean!=null) return bean;
        BeanDefinition beanDefinition = getDefinition(name);
        return creatBean(name,beanDefinition,args);
    }
    //protected abstract Object creatBean(String beanName,BeanDefinition beanDefinition)throws BeansException;
    protected abstract Object creatBean(String beanName,BeanDefinition beanDefinition,Object...args)throws BeansException;
    protected abstract BeanDefinition getDefinition(String beanName)throws BeansException;
}
