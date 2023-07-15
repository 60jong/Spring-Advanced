package site._60jong.advanced.practice.proxy.app.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
public class OrderServiceP1Impl implements OrderServiceP1 {

    private final OrderRepositoryP1 orderRepository;

    @Override
    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }
}
