package site._60jong.advanced.practice.proxy.app.v2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import site._60jong.advanced.practice.proxy.app.v1.OrderControllerP1;
import site._60jong.advanced.practice.proxy.app.v1.OrderServiceP1;

@Slf4j
@RequiredArgsConstructor
@ResponseBody
@RequestMapping
public class OrderControllerP2 {

    private final OrderServiceP2 orderService;

    @GetMapping("/p2/request")
    public String request(String itemId) {
        orderService.orderItem(itemId);
        return "ok";
    }

    @GetMapping("/p2/no-log")
    public String noLog() {
        return "ok";
    }
}
