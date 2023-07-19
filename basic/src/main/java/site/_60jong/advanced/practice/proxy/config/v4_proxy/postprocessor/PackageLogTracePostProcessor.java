package site._60jong.advanced.practice.proxy.config.v4_proxy.postprocessor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

@Slf4j
@RequiredArgsConstructor
public class PackageLogTracePostProcessor implements BeanPostProcessor {

    private final String basePackage;
    private final Advisor advisor;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info("beanName = {}, bean = {}", beanName, bean);

        // 프록시 적용 대상 여부 체크
        if (!bean.getClass().getPackageName().startsWith(basePackage)) {
            return bean;
        }

        // 프록시 적용 대상 아니면 원본 리턴
        ProxyFactory proxyFactory = new ProxyFactory(bean);
        proxyFactory.addAdvisor(advisor);

        Object proxy = proxyFactory.getProxy();
        log.info("proxy class = {}", proxy.getClass());
        return proxy;
    }
}
