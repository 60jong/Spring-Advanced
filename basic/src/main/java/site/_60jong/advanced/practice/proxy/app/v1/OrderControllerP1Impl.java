package site._60jong.advanced.practice.proxy.app.v1;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderControllerP1Impl implements OrderControllerP1 {

    private final OrderServiceP1 orderService;

    @Override
    public String request(String itemId) {
        orderService.orderItem(itemId);
        return "ok";
    }

    @Override
    public String noLog() {
        return "ok";
    }
}
