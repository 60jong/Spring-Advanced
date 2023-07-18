package site._60jong.advanced.kj.aop.proxy.common.v2;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ResponseBody
@RequestMapping
public class MainControllerV2 {

    private MainServiceV2 mainService;

    public MainControllerV2(MainServiceV2 mainService) {
        this.mainService = mainService;
    }

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
