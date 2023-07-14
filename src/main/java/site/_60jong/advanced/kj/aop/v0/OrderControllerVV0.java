package site._60jong.advanced.kj.aop.v0;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import site._60jong.advanced.kj.aop.log.tracer.LogTracer;
import site._60jong.advanced.kj.aop.log.tracer.ThreadLocalLogTracer;
import site._60jong.advanced.kj.aop.log.TraceStatus;

@Slf4j
@RequiredArgsConstructor
@RestController
public class OrderControllerVV0 {

    private final OrderServiceVV0 orderService;
    private final LogTracer tracer;

    @GetMapping("/vv0/request")
    public String request(String itemId) {
        TraceStatus status = tracer.begin("OrderController.request()");

        orderService.orderItem(itemId);

        tracer.end(status);
        return "ok";
    }
}
