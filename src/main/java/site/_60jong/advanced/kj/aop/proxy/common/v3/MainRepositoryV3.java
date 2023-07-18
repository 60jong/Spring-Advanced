package site._60jong.advanced.kj.aop.proxy.common.v3;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MainRepositoryV3 {

    private static long sequence = 0l;
    private final Map<Long, String> mainStore = new ConcurrentHashMap<>();

    public long save(String name) {
        mainStore.put(++sequence, name);
        sleep(1000);
        return sequence;
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new IllegalArgumentException("예외 발생!");
        }
    }
}
