package site._60jong.advanced.practice.proxy.decorator.code;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import site._60jong.advanced.practice.proxy.decorator.code.Component;

@Slf4j
@RequiredArgsConstructor
public class DecoratorPatternClient {

    private final Component component;

    public void execute() {
        String result = component.operation();
        log.info("result = {}", result);
    }
}
