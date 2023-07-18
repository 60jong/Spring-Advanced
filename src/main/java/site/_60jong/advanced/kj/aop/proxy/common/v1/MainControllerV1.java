package site._60jong.advanced.kj.aop.proxy.common.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@RequestMapping
public interface MainControllerV1 {

    @GetMapping("/main/v1/request")
    String request(@RequestParam("name") String name);

    @GetMapping("/main/v1/no-log")
    String noLog();
}
