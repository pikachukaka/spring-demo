import aop.AdvisedSupport;
import aop.TargetSource;
import aop.aspectj.AspectJExpressionPointcut;
import aop.framework.Cglib2AopProxy;
import aop.framework.JdkDynamicAopProxy;
import cn.echo.springframework.test.bean.IUserService;
import cn.echo.springframework.test.bean.UserDao;
import cn.echo.springframework.test.bean.UserService;
import cn.echo.springframework.test.bean.UserServiceInterceptor;
import beans.PropertyValue;
import beans.PropertyValues;
import beans.factory.config.BeanDefinition;
import beans.factory.config.BeanReference;
import beans.factory.support.DefaultListableBeanFactory;
import beans.factory.support.XmlBeanDefinitionReader;
import common.MyBeanFactoryPostProcessor;
import common.MyBeanPostProcessor;
import context.support.ClassPathXmlApplicationContext;
import event.CustomEvent;
import org.junit.jupiter.api.Test;
import org.openjdk.jol.info.ClassLayout;

public class ApiTest {
    @Test
    public void testBeanFactory(){
        //初始化beanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //注入bean
        BeanDefinition beanDefinition= new BeanDefinition(UserDao.class);
        beanFactory.registerBeanDefinition("userDao",beanDefinition);

        PropertyValues propertyValues= new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("id","1"));
        propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));

        BeanDefinition beanDefinition1= new BeanDefinition(UserService.class,propertyValues);
        beanFactory.registerBeanDefinition("userService",beanDefinition1);

        //获取bean
        UserService userService=(UserService) beanFactory.getBean("userService");
        userService.queryInfo();
    }

    @Test
    public void test_xml() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 读取配置文件&注册Bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");
        //UserDao userDao = (UserDao)beanFactory.getBean("userDao");
        //System.out.println(userDao.queryUserName("1"));
        // 3. 获取Bean对象调用方法
        UserService userService = (UserService)beanFactory.getBean("userService");//, UserService.class
        String result = userService.queryInfo();

        System.out.println("测试结果：" + result);
    }

    @Test
    public void test_BeanFactoryPostProcessorAndBeanPostProcessor(){
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 读取配置文件&注册Bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        // 3. BeanDefinition 加载完成 & Bean实例化之前，修改 BeanDefinition 的属性值
        MyBeanFactoryPostProcessor beanFactoryPostProcessor = new MyBeanFactoryPostProcessor();
        beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        // 4. Bean实例化之后，修改 Bean 属性信息
        MyBeanPostProcessor beanPostProcessor = new MyBeanPostProcessor();
        beanFactory.addBeanPostProcessor(beanPostProcessor);

        // 5. 获取Bean对象调用方法
        UserService userService = beanFactory.getBean("userService", UserService.class);
        String result = userService.queryInfo();
        System.out.println("测试结果：" + result);
    }

    @Test
    public void test_xml_with_context() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");

        // 2. 获取Bean对象调用方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String result = userService.queryInfo();
        System.out.println("测试结果：" + result);
    }

    @Test
    public void test_xml_with_init_destroy() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        // 2. 获取Bean对象调用方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String result = userService.queryInfo();
        System.out.println("测试结果：" + result);
    }

    @Test
    public void test_xml_aware() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        // 2. 获取Bean对象调用方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String result = userService.queryInfo();
        System.out.println("测试结果：" + result);
        //System.out.println("ApplicationContextAware："+userService.getApplicationContext());
        //System.out.println("BeanFactoryAware："+userService.getBeanFactory());
    }
    @Test
    public void test_prototype() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        // 2. 获取Bean对象调用方法
        UserService userService01 = applicationContext.getBean("userService", UserService.class);
        UserService userService02 = applicationContext.getBean("userService", UserService.class);

        // 3. 配置 scope="prototype/singleton"
        System.out.println(userService01);
        System.out.println(userService02);

        // 4. 打印十六进制哈希
        System.out.println(userService01 + " 十六进制哈希：" + Integer.toHexString(userService01.hashCode()));
        System.out.println(ClassLayout.parseInstance(userService01).toPrintable());

    }

    @Test
    public void test_factory_bean() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        // 2. 调用代理方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        System.out.println("测试结果：" + userService.queryInfo());
    }

    @Test
    public void test_factory_bean2() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        // 2. 调用代理方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        System.out.println("测试结果：" + userService.queryInfo());
    }

    @Test
    public void test_event() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext, 1019129009086763L, "成功了！"));

        applicationContext.registerShutdownHook();
    }

    @Test
    public void test_dynamic(){
        IUserService target = new UserService();
        AdvisedSupport support =  new AdvisedSupport();
        support.setTargetSource(new TargetSource(target));
        support.setMethodInterceptor(new UserServiceInterceptor());
        support.setMethodMatcher(new AspectJExpressionPointcut("execution(* IUserService.*(..))"));
        IUserService JdkProxy = (IUserService) new JdkDynamicAopProxy(support).getProxy();
        JdkProxy.queryInfo();
        IUserService CglibProxy = (IUserService) new Cglib2AopProxy(support).getProxy();
        CglibProxy.queryInfo();
    }

    @Test
    public void test_autowired() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        //UserDao userDao = applicationContext.getBean("userDao", UserDao.class);
        //System.out.println("测试结果：" + userDao.queryUserName("111"));
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println("测试结果：" + userService.queryInfo());
    }

    @Test
    public void test_scan() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");


        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println("测试结果：" + userService.queryInfo());
    }
}
