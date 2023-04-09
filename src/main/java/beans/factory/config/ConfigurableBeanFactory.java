package beans.factory.config;

import beans.factory.HierachicalBeanFactory;
import utils.StringValueResolver;

public interface ConfigurableBeanFactory extends HierachicalBeanFactory,SingletonBeanRegistry {
    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
    void destroySingletons();
    void addEmbeddedValueResolver(StringValueResolver valueResolver);

    String resolveEmbeddedValue(String value);

    //void setConversionService(ConversionService conversionService);

   // ConversionService getConversionService();
}
