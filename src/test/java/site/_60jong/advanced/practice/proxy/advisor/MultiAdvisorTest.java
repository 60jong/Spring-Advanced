package site._60jong.advanced.practice.proxy.advisor;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import site._60jong.advanced.practice.proxy.common.service.ServiceImpl;
import site._60jong.advanced.practice.proxy.common.service.ServiceInterface;

@Slf4j
public class MultiAdvisorTest {

    @Test
    void two_advisor_two_proxy() {

        // 프록시 1 생성
        ServiceInterface target = new ServiceImpl();
        DefaultPointcutAdvisor advisor1 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice1());
        ProxyFactory proxyFactory1 = new ProxyFactory(target);
        proxyFactory1.addAdvisor(advisor1);
        ServiceInterface proxy1 = (ServiceInterface) proxyFactory1.getProxy();

        // 프록시 2 생성
        DefaultPointcutAdvisor advisor2 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice2());
        ProxyFactory proxyFactory2 = new ProxyFactory(proxy1);
        proxyFactory1.addAdvisor(advisor2);
        ServiceInterface proxy2 = (ServiceInterface) proxyFactory2.getProxy();

        proxy2.save();
    }

    @Test
    void two_advisor_one_proxy() {
        DefaultPointcutAdvisor advisor1 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice1());
        DefaultPointcutAdvisor advisor2 = new DefaultPointcutAdvisor(Pointcut.TRUE, new Advice2());
        // 프록시 생성
        ServiceInterface target = new ServiceImpl();

        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvisor(advisor2);
        proxyFactory.addAdvisor(advisor1);
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        proxy.save();
    }

    static class Advice1 implements MethodInterceptor {

        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            log.info("advice1 Start");

            Object result = invocation.proceed();

            log.info("advice1 End");
            return result;
        }
    }

    static class Advice2 implements MethodInterceptor {

        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            log.info("advice2 Start");

            Object result = invocation.proceed();

            log.info("advice2 End");
            return result;
        }
    }
}
