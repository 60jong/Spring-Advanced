package site._60jong.advanced.kj.aop.proxy.common.v1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@RequiredArgsConstructor
public class MainControllerV1Impl implements MainControllerV1 {

    private final MainServiceV1 mainService;

    @Override
    public String request(String name) {
        long saveId = mainService.execute(name);

        return String.format("ID = %d", saveId);
    }

    @Override
    public String noLog() {
        return "no-log";
    }
}
