package site._60jong.advanced.aop.v2;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import site._60jong.advanced.trace.TraceStatus;
import site._60jong.advanced.trace.hellotrace.HelloTraceV1;
import site._60jong.advanced.trace.hellotrace.HelloTraceV2;

@RequiredArgsConstructor
@RestController
public class OrderControllerV2 {

    private final OrderServiceV2 orderService;
    private final HelloTraceV2 trace;

    @GetMapping("/v2/request")
    public String request(String itemId) {

        TraceStatus status = null;
        try {
            status = trace.begin("OrderController.request()");
            orderService.orderItem(status.getTraceId(), itemId);
            trace.end(status);
            return "ok";
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
