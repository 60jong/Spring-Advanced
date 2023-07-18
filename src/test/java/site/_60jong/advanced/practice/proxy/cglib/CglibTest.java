package site._60jong.advanced.practice.proxy.cglib;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;
import site._60jong.advanced.practice.proxy.cglib.code.TimeMethodInterceptor;
import site._60jong.advanced.practice.proxy.common.service.ConcreteService;
import site._60jong.advanced.practice.proxy.common.service.ServiceImpl;
import site._60jong.advanced.practice.proxy.common.service.ServiceInterface;

@Slf4j
public class CglibTest {
    @Test
    void cglib_concrete() {
        ConcreteService concreteService = new ConcreteService();
        TimeMethodInterceptor interceptor = new TimeMethodInterceptor(concreteService);

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ConcreteService.class);
        enhancer.setCallback(interceptor);
        ConcreteService proxy = (ConcreteService) enhancer.create();

        log.info("target class : {}", concreteService.getClass());
        log.info("proxy class : {}", proxy.getClass());
        proxy.save();
    }

    @Test
    void cglib_interface() {
        ServiceInterface service = new ServiceImpl();

        Enhancer enhancer = new Enhancer();
        enhancer.setInterfaces(new Class[]{ServiceInterface.class});
        enhancer.setCallback(new TimeMethodInterceptor(service));

        ServiceInterface proxy = (ServiceInterface) enhancer.create();
        proxy.save();
    }
}
