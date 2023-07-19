package site._60jong.advanced.practice.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Supplier;

@Slf4j
public class ReflectionTest2 {

    @Test
    void reflection0() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method methodCallA = DynamicHello.class.getMethod("callA");
        Method methodCallB = DynamicHello.class.getMethod("callB");
        dynamicCall(methodCallA, new DynamicHello());

        dynamicCall(methodCallB, new DynamicHello());
    }

    private void dynamicCall(Method method, Object object) throws InvocationTargetException, IllegalAccessException {
        log.info("start");

        log.info("result = {}", method.invoke(object));

        log.info("end");
    }

    @Slf4j
    static class DynamicHello {
        public String callA() {
            log.info("callA");
            return "A";
        }

        public String callB() {
            log.info("callB");
            return "B";
        }
    }
}
