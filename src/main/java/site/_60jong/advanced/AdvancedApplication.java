package site._60jong.advanced;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import site._60jong.advanced.practice.proxy.config.v1_proxy.ProxyConcreteConfig;

@Import(ProxyConcreteConfig.class)
@SpringBootApplication(scanBasePackages = "site._60jong.advanced.practice.proxy.app")
public class AdvancedApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvancedApplication.class, args);
	}

}
