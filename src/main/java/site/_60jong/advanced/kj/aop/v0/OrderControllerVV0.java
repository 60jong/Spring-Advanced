package site._60jong.advanced.kj.aop.v0;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import site._60jong.advanced.kj.aop.log.LogTracer;
import site._60jong.advanced.kj.aop.log.Trace;

@Slf4j
@RequiredArgsConstructor
@RestController
public class OrderControllerVV0 {

    private final OrderServiceVV0 orderService;
    private final LogTracer tracer;

    @GetMapping("/vv0/request")
    public String request(String itemId) {
        Trace begin = tracer.begin("OrderController.request()");

        orderService.orderItem(itemId);

        tracer.end("OrderController.request()", begin);
        return "ok";
    }
}
