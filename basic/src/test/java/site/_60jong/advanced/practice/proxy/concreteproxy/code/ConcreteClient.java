package site._60jong.advanced.practice.proxy.concreteproxy.code;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ConcreteClient {

    private final ConcreteLogic logic;

    public void execute() {
        String result = logic.operation();

        log.info("result = {}", result);
    }
}
