package site._60jong.advanced.kj.aop.v2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import site._60jong.advanced.kj.aop.log.tracer.LogTracer;
import site._60jong.advanced.kj.aop.log.tracer.strategy.Context;
import site._60jong.advanced.kj.aop.log.tracer.templatemethod.AbstractTemplate;

@Slf4j
@RequiredArgsConstructor
@RestController
public class OrderControllerVV2 {

    private final OrderServiceVV2 orderService;
    private final Context context;

    @GetMapping("/vv2/request")
    public String request(String itemId) {
        return context.execute("OrderController.request()", () -> {
                    orderService.orderItem(itemId);
                    return "ok";
                }
        );
    }
}
