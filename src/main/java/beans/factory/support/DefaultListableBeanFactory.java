package beans.factory.support;

import beans.BeansException;
import beans.factory.BeanFactory;
import beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {
    private Map<String, BeanDefinition>beanDefinitionMap =new HashMap<>();



    @Override
    protected BeanDefinition getDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if(beanDefinition==null){
            String mess="no bean named ' "+ beanName +" ' is defined";
            throw new BeansException(mess);
        }
        return beanDefinition;
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName,beanDefinition);
    }


}
