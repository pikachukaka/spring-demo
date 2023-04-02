package context;

import beans.BeansException;

public interface ConfigurableApplicationContext extends ApplicationContext {
    void refresh() throws BeansException;
}
