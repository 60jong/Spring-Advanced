package site._60jong.advanced.practice.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

@Slf4j
public class BeanPostProcessorTest {
    @Test
    void basicConfig() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AToBConfig.class);

        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            log.info(name);
        }
        B b = context.getBean("beanA", B.class);
        b.helloA();

        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> context.getBean("beanA", A.class));
    }


    static class AToBConfig {
        @Bean
        public A beanA() {
            return new A();
        }

        @Bean
        public BeanPostProcessor beanPostProcessor() {
            return new AToBBeanPostProcessor();
        }
    }

    @Slf4j
    static class A {



        public A() {
            log.info("A 객체 생성");
        }
        @PostConstruct
        private void init() {
            log.info("beanA 생성");
        }

        public void helloA() {
            log.info("Hello A");
        }

        public void helloB() {
            log.info("Hello B");
        }
    }

    @Slf4j
    static class B {
        public void helloA() {
            log.info("Hello A");
        }

        public void helloB() {
            log.info("Hello B");
        }
    }

    @Slf4j
    static class AToBBeanPostProcessor implements BeanPostProcessor {

        // beanA 이름으로 B 빈이 등록됨.
        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            log.info("beanName = {} bean = {}", beanName, bean);
            if (bean instanceof A) {
                return new B();
            }
            return bean;
        }
    }
}
