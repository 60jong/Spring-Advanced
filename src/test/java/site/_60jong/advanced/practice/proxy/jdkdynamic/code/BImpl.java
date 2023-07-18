package site._60jong.advanced.practice.proxy.jdkdynamic.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BImpl implements B {
    @Override
    public String call() {
        log.info("B 호출");

        return "B";
    }
}
