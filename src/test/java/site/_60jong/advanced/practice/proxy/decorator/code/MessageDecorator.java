package site._60jong.advanced.practice.proxy.decorator.code;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class MessageDecorator implements Component {

    private final Component target;

    @Override
    public String operation() {
        log.info("MessageDecorator 실행");
        String result = target.operation();
        String decoResult = "*****" + result + "*****";
        return decoResult;
    }
}
