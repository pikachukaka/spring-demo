package bean;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class UserServiceInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        long start=System.currentTimeMillis();
        try {
            return invocation.proceed();
        }finally {
            System.out.println("monitor-begin");
            System.out.println("method:"+invocation.getMethod());
            System.out.println("time:"+(System.currentTimeMillis()-start)+"ms");
            System.out.println("monitor-end");
        }
    }
}
