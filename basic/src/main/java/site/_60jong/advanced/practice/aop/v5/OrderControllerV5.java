package site._60jong.advanced.practice.aop.v5;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import site._60jong.advanced.practice.trace.callback.TraceTemplate;
import site._60jong.advanced.practice.trace.logtrace.LogTrace;
import site._60jong.advanced.practice.trace.template.AbstractTemplate;

@RequiredArgsConstructor
@RestController
public class OrderControllerV5 {

    private final OrderServiceV5 orderService;
    private final TraceTemplate template;

    @GetMapping("/v5/request")
    public String request(String itemId) {
        return template.execute("OrderController.request()", () -> {
                    orderService.orderItem(itemId);
                    return "ok";
                }
        );
    }
}
