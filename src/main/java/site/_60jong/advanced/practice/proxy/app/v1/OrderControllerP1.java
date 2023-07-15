package site._60jong.advanced.practice.proxy.app.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping
@ResponseBody
public interface OrderControllerP1 {

    @GetMapping("/p1/request")
    String request(@RequestParam("itemId") String itemId);

    @GetMapping("/p1/no-log")
    String noLog();
}
