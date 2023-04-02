package beans.factory.support;

import beans.BeansException;
import beans.PropertyValue;
import beans.PropertyValues;
import beans.factory.config.AutowireCapableBeanFactory;
import beans.factory.config.BeanDefinition;
import beans.factory.config.BeanPostProcessor;
import beans.factory.config.BeanReference;
import cn.hutool.core.bean.BeanUtil;

import java.lang.reflect.Constructor;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition,Object[] args) throws BeansException {
        Object bean =null;
        try {
            bean=createBeanInstance(beanDefinition,beanName,args);
            applyPropertyValues(beanName,bean,beanDefinition);
            // 执行 Bean 的初始化方法和 BeanPostProcessor 的前置和后置处理方法
            bean = initializeBean(beanName, bean, beanDefinition);
        }catch (Exception e){
            throw new BeansException("Instantiation of bean failed",e);
        }
        addSingleton(beanName,bean);
        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition,String beanName,
                                        Object[] args){
        Constructor constructor = null;
        Class<?> beanClass =  beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for(Constructor constructor1: declaredConstructors){
            if(null!=args&&constructor1.getParameterTypes().length== args.length){
                constructor=constructor1;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition,beanName,constructor,args);
    }
    protected void applyPropertyValues(String beanName,Object bean,BeanDefinition beanDefinition){
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for(PropertyValue propertyValue:propertyValues.getPropertyValues()){
                String name = propertyValue.getName();
                Object value=propertyValue.getValue();
                if(value instanceof BeanReference){
                    BeanReference beanReference=(BeanReference) value;
                    value=getBean(beanReference.getBeanName());
                }
                BeanUtil.setFieldValue(bean,name,value);
            }
        }catch (Exception e){
            throw new BeansException("error setting property values: "+beanName);
        }
    }


    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        // 1. 执行 BeanPostProcessor Before 处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);

        // 待完成内容：invokeInitMethods(beanName, wrappedBean, beanDefinition);
        invokeInitMethods(beanName, wrappedBean, beanDefinition);

        // 2. 执行 BeanPostProcessor After 处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
        return wrappedBean;
    }

    private void invokeInitMethods(String beanName, Object wrappedBean, BeanDefinition beanDefinition) {

    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessBeforeInitialization(result, beanName);
            if (null == current) return result;
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessAfterInitialization(result, beanName);
            if (null == current) return result;
            result = current;
        }
        return result;
    }
}
