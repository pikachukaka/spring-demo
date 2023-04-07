package aop.aspectj;

import aop.MethodBeforeAdvice;
import aop.Pointcut;
import aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

import java.lang.reflect.Method;

public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {
    // 切面
    private AspectJExpressionPointcut pointcut;
    // 具体的拦截方法
    private Advice advice;
    // 表达式
    private String expression;

    public AspectJExpressionPointcutAdvisor(AspectJExpressionPointcut pointcut, Advice advice, String expression) {
        this.pointcut = pointcut;
        this.advice = advice;
        this.expression = expression;
    }

    public AspectJExpressionPointcutAdvisor() {
    }

    public void setPointcut(AspectJExpressionPointcut pointcut) {
        this.pointcut = pointcut;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    @Override
    public Pointcut getPointcut() {
        if (null == pointcut) {
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }
}
