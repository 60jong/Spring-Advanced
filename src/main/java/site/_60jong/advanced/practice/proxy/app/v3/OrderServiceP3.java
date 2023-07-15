package site._60jong.advanced.practice.proxy.app.v3;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderServiceP3 {

    private final OrderRepositoryP3 orderRepository;

    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }
}
