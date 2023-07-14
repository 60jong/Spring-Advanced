package site._60jong.advanced.kj.aop.v1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import site._60jong.advanced.kj.aop.log.TraceStatus;
import site._60jong.advanced.kj.aop.log.tracer.LogTracer;
import site._60jong.advanced.kj.aop.log.tracer.templatemethod.AbstractTemplate;

@Slf4j
@RequiredArgsConstructor
@RestController
public class OrderControllerVV1 {

    private final OrderServiceVV1 orderService;
    private final LogTracer tracer;

    @GetMapping("/vv1/request")
    public String request(String itemId) {
        AbstractTemplate<String> template = new AbstractTemplate(tracer) {
            @Override
            public String call() {
                orderService.orderItem(itemId);
                return "ok";
            }
        };

        return template.execute("OrderController.request()");
    }
}
