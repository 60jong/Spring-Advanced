package site._60jong.advanced.practice.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
public class BasicTest {
    @Test
    void basicConfig() {
        ApplicationContext context = new AnnotationConfigApplicationContext(BasicConfig.class);


        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            log.info(name);
        }
        A beanA = context.getBean("beanA", A.class);
        beanA.helloA();

        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> context.getBean("beanB", B.class));
    }


    static class BasicConfig {
        @Bean
        public A beanA() {
            return new A();
        }
    }

    @Slf4j
    static class A {
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
}
