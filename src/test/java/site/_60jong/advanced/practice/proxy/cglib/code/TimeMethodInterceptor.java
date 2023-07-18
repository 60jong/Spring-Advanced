package site._60jong.advanced.practice.proxy.cglib.code;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

@Slf4j
@RequiredArgsConstructor
public class TimeMethodInterceptor implements MethodInterceptor {

    private final Object target;

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        log.info("TimeProxy 실행");
        long startTime = System.currentTimeMillis();

        Object result = methodProxy.invoke(target, objects);

        log.info("TimeProxy 종료, resultTime = {}ms", System.currentTimeMillis() - startTime);
        return result;
    }
}
