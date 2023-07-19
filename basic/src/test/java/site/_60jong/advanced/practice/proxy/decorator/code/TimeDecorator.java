package site._60jong.advanced.practice.proxy.decorator.code;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class TimeDecorator implements Component {

    private final Component target;

    @Override
    public String operation() {
        log.info("TimeDecorator 실행");
        long startTimeMs = System.currentTimeMillis();
        String result = target.operation();
        long endTimeMs = System.currentTimeMillis();
        log.info("소요 시간 : {}ms", endTimeMs - startTimeMs);

        return result;
    }
}
