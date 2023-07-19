package site._60jong.advanced.practice.proxy.pureproxy;

import org.junit.jupiter.api.Test;
import site._60jong.advanced.practice.proxy.pureproxy.code.CacheProxy;
import site._60jong.advanced.practice.proxy.pureproxy.code.ProxyPatternClient;
import site._60jong.advanced.practice.proxy.pureproxy.code.RealSubject;

public class ProxyPatternClientTest {

    @Test
    void noProxyTest() {
        RealSubject subject = new RealSubject();
        ProxyPatternClient client = new ProxyPatternClient(subject);
        client.execute();
        client.execute();
        client.execute();
    }

    @Test
    void proxyTest() {
        RealSubject subject = new RealSubject();
        CacheProxy proxy = new CacheProxy(subject);
        ProxyPatternClient client = new ProxyPatternClient(proxy);
        client.execute();
        client.execute();
        client.execute();
    }
}
