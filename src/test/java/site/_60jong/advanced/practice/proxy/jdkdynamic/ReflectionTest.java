package site._60jong.advanced.practice.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Supplier;

@Slf4j
public class ReflectionTest {

    @Test
    void reflection0() {

        Hello hello = new Hello();

        execute(() -> hello.callA());

        execute(() -> hello.callB());
    }

    @Test
    void reflection1() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method callA = Hello.class.getMethod("callA");
        executeMethod(callA);
    }
    private <T> T execute(Supplier<T> supplier) {
        log.info("start");
        T result = supplier.get(); // 호출 메서드만 다름
        log.info("result = {}", result);
        return result;
    }

    private void executeMethod(Method method) throws InvocationTargetException, IllegalAccessException {
        log.info("start");
        Object result = method.invoke(new Hello()); // 호출 메서드만 다름
        log.info("result = {}", result);
    }

    @Slf4j
    static class Hello {
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
