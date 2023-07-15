package site._60jong.advanced.practice.proxy.app.v2;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site._60jong.advanced.practice.proxy.app.v1.OrderRepositoryP1;
import site._60jong.advanced.practice.proxy.app.v1.OrderServiceP1;

@RequiredArgsConstructor
public class OrderServiceP2 {

    private final OrderRepositoryP2 orderRepository;

    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }
}
