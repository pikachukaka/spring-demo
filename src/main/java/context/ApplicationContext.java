package context;

import beans.factory.HierachicalBeanFactory;
import beans.factory.ListableBeanFactory;
import core.io.ResourceLoader;

public interface ApplicationContext extends ListableBeanFactory , HierachicalBeanFactory, ResourceLoader, ApplicationEventPublisher{
}
