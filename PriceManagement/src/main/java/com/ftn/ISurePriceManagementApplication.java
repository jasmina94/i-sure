package com.ftn;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@EnableAutoConfiguration
@SpringBootApplication
public class ISurePriceManagementApplication {

	private static Logger log = LoggerFactory.getLogger(ISurePriceManagementApplication.class);

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(ISurePriceManagementApplication.class, args);

		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);

		StringBuilder sb = new StringBuilder("Application beans:\n");
		for (String beanName : beanNames) {
			sb.append(beanName + "\n");
		}
		log.info(sb.toString());
	}

	/**
	 * By defining the {@link KieContainer} as a bean here, we ensure that
	 * Drools will hunt out the kmodule.xml and rules on application startup.
	 * Those can be found in <code>src/main/resources</code>.
	 *
	 * @return The {@link KieContainer}.
	 */
	@Bean
	public KieContainer kieContainer() {
		return KieServices.Factory.get().getKieClasspathContainer();
	}
}
