package site._60jong.advanced.practice.proxy.proxyfactory;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import site._60jong.advanced.practice.proxy.common.advice.TimeAdvice;
import site._60jong.advanced.practice.proxy.common.service.ConcreteService;
import site._60jong.advanced.practice.proxy.common.service.ServiceImpl;
import site._60jong.advanced.practice.proxy.common.service.ServiceInterface;

import static org.assertj.core.api.Assertions.*;

@Slf4j
public class ProxyFactoryTest {
    @Test
    @DisplayName("인터페이스가 있다면 JDK 동적 프록시 사용")
    void interfaceProxy() {
        ServiceImpl target = new ServiceImpl();

        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvice(new TimeAdvice());

        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        log.info("target class : {}", target.getClass());
        log.info("proxy class : {}", proxy.getClass());
        proxy.save();

        assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue();
        assertThat(AopUtils.isCglibProxy(proxy)).isFalse();
    }

    @Test
    @DisplayName("인터페이스가 없다면 CGLIB 사용")
    void proxyfactory_concrete() {
        ConcreteService target = new ConcreteService();

        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvice(new TimeAdvice());
        ConcreteService proxy = (ConcreteService) proxyFactory.getProxy();


        log.info("target class : {}", target.getClass());
        log.info("proxy class : {}", proxy.getClass());
        proxy.save();

        assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();
        assertThat(AopUtils.isCglibProxy(proxy)).isTrue();
    }

    @Test
    @DisplayName("인터페이스가 있어도 CGLIB 사용해 클래스 기반 proxy 생성")
    void proxyTargetClass() {
        ServiceImpl target = new ServiceImpl();

        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvice(new TimeAdvice());
        proxyFactory.setProxyTargetClass(true);
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        log.info("target class : {}", target.getClass());
        log.info("proxy class : {}", proxy.getClass());
        proxy.save();

        assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();
        assertThat(AopUtils.isCglibProxy(proxy)).isTrue();
    }

    @Test
    void proxyAdvisor() {
        ServiceImpl target = new ServiceImpl();

        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvisor(new DefaultPointcutAdvisor(Pointcut.TRUE, new TimeAdvice()));
        proxyFactory.setProxyTargetClass(true);
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        log.info("target class : {}", target.getClass());
        log.info("proxy class : {}", proxy.getClass());
        proxy.save();

        assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();
        assertThat(AopUtils.isCglibProxy(proxy)).isTrue();
    }
}
