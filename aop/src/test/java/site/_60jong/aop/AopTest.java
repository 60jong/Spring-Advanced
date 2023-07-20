package site._60jong.aop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import site._60jong.aop.order.OrderRepository;
import site._60jong.aop.order.OrderService;
import site._60jong.aop.order.aop.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Import({AspectV5.LogAspect.class, AspectV5.TxAspect.class})
@SpringBootTest
public class AopTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderService orderService;

    @Test
    void aopInfo() {
        assertThat(AopUtils.isAopProxy(orderService)).isTrue();
        assertThat(AopUtils.isAopProxy(orderRepository)).isFalse();
    }

    @Test
    void success() {
        orderService.orderItem("item");
    }

    @Test
    void exception() {
        assertThrows(IllegalArgumentException.class, () -> orderService.orderItem("ex"));
    }
}
