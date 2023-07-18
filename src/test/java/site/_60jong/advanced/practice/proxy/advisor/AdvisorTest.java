package site._60jong.advanced.practice.proxy.advisor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import site._60jong.advanced.practice.proxy.common.advice.BasicAdvice;
import site._60jong.advanced.practice.proxy.common.advice.TimeAdvice;
import site._60jong.advanced.practice.proxy.common.service.ServiceImpl;
import site._60jong.advanced.practice.proxy.common.service.ServiceInterface;

import java.lang.reflect.Method;

@Slf4j
public class AdvisorTest {
    @Test
    void proxyFactory_with_advisor() {
        ServiceInterface service = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(service);
        proxyFactory.addAdvisor(new DefaultPointcutAdvisor(new MyPointCut(), new BasicAdvice()));

        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
        proxy.save();
        proxy.find();
    }

    @Test
    void spring_pointcut() {
        ServiceInterface service = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(service);

        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("save");

        proxyFactory.addAdvisor(new DefaultPointcutAdvisor(pointcut, new BasicAdvice()));

        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
        proxy.save();
        proxy.find();
    }

    static class MyPointCut implements Pointcut {

        @Override
        public ClassFilter getClassFilter() {
            return ClassFilter.TRUE;
        }

        @Override
        public MethodMatcher getMethodMatcher() {
            return new MyMethodMatcher();
        }
    }

    static class MyMethodMatcher implements MethodMatcher {

        private static final String matchName = "save";

        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            boolean result = method.getName().equals(matchName);

            log.info("포인트컷 호출 method = {}, target class  = {}", method.getName(), targetClass.getName());
            log.info("포인트컷 결과 result = {}", result);
            return result;
        }

        @Override
        public boolean isRuntime() {
            return false;
        }

        @Override
        public boolean matches(Method method, Class<?> targetClass, Object... args) {
            return false;
        }
    }
}
