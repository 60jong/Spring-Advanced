package site._60jong.advanced;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import site._60jong.advanced.practice.proxy.config.v6_aspect.AopConfig;

@Import(AopConfig.class)
//@SpringBootApplication(scanBasePackages = "site._60jong.advanced.practice.proxy.app")
@SpringBootApplication(scanBasePackages = "site._60jong.advanced.kj")
public class AdvancedApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvancedApplication.class, args);
	}

}
