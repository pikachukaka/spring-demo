package beans.factory.config;

/**
 * 用于定义bean信息的类，包含bean的class类型、构造参数、属性值等信息，
 * 每个bean对应一个BeanDefinition的实例。
 * 简化BeanDefinition仅包含bean的class类型。
 */
public class BeanDefinition {
    private Class beanClass;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}