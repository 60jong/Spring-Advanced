package site._60jong.advanced.practice.proxy.decorator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import site._60jong.advanced.practice.proxy.decorator.code.*;

public class DecoratorPatternTest {
    @Test
    @DisplayName("데코레이터 X")
    void noDecorator() {
        Component component = new RealComponent();
        DecoratorPatternClient client = new DecoratorPatternClient(component);

        client.execute();
    }

    @Test
    @DisplayName("데코레이터 O")
    void withDecorator() {
        Component component = new RealComponent();
        Component decorator = new MessageDecorator(component);

        DecoratorPatternClient client = new DecoratorPatternClient(decorator);

        client.execute();
    }

    @Test
    @DisplayName("데코레이터 time -> message")
    void withSeveralDecorator() {
        Component component = new RealComponent();
        Component decorator = new MessageDecorator(component);
        Component timeDecorator = new TimeDecorator(decorator);

        DecoratorPatternClient client = new DecoratorPatternClient(timeDecorator);

        client.execute();
    }
}
