package site._60jong.advanced.kj.aop.proxy.common.v2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import site._60jong.advanced.kj.aop.proxy.common.v1.MainControllerV1;
import site._60jong.advanced.kj.aop.proxy.common.v1.MainServiceV1;

@RequiredArgsConstructor
@ResponseBody
@RequestMapping
public class MainControllerV2 {

    private final MainServiceV2 mainService;

    @GetMapping("/main/v2/request")
    public String request(String name) {
        long saveId = mainService.execute(name);

        return String.format("ID = %d", saveId);
    }

    @GetMapping("/main/v2/no-log")
    public String noLog() {
        return "no-log";
    }
}
