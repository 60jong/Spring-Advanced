package site._60jong.advanced.kj.aop.proxy.common.v3;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MainControllerV3 {

    private final MainServiceV3 mainService;


    @GetMapping("/main/v3/request")
    public String request(String name) {
        long saveId = mainService.execute(name);

        return String.format("ID = %d", saveId);
    }

    @GetMapping("/main/v3/no-log")
    public String noLog() {
        return "no-log";
    }
}
