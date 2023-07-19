package site._60jong.advanced.practice.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import site._60jong.advanced.practice.proxy.jdkdynamic.code.A;
import site._60jong.advanced.practice.proxy.jdkdynamic.code.AImpl;
import site._60jong.advanced.practice.proxy.jdkdynamic.code.TimeInvocationHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

@Slf4j
public class JdkDynamicProxyTest {
    @Test
    void dynamicA() {
        A target = new AImpl();
        InvocationHandler handler = new TimeInvocationHandler(target);

        A proxy = (A) Proxy.newProxyInstance(A.class.getClassLoader(), new Class[]{A.class}, handler);

        proxy.call();
        log.info("target class : {}", target.getClass());
        log.info("proxy class : {}", proxy.getClass());

    }
}
