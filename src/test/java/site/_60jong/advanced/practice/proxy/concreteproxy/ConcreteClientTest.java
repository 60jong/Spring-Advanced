package site._60jong.advanced.practice.proxy.concreteproxy;

import org.junit.jupiter.api.Test;
import site._60jong.advanced.practice.proxy.concreteproxy.code.ConcreteClient;
import site._60jong.advanced.practice.proxy.concreteproxy.code.ConcreteLogic;
import site._60jong.advanced.practice.proxy.concreteproxy.code.TimeProxyLogic;

public class ConcreteClientTest {
    @Test
    void noProxy() {
        ConcreteLogic logic = new ConcreteLogic();
        ConcreteClient client = new ConcreteClient(logic);

        client.execute();
    }

    @Test
    void withProxy() {
        ConcreteLogic target = new ConcreteLogic();
        ConcreteLogic proxy = new TimeProxyLogic(target);

        ConcreteClient client = new ConcreteClient(proxy);

        client.execute();
    }
}
