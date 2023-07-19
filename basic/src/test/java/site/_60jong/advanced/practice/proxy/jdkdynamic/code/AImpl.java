package site._60jong.advanced.practice.proxy.jdkdynamic.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AImpl implements A {
    @Override
    public String call() {
        log.info("A 호출");
        return "A";
    }
}
