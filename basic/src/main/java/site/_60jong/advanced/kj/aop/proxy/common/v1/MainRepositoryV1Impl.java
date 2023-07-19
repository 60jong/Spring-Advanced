package site._60jong.advanced.kj.aop.proxy.common.v1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MainRepositoryV1Impl implements MainRepositoryV1 {

    private static long sequence = 0l;
    private final Map<Long, String> mainStore = new ConcurrentHashMap<>();

    @Override
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
