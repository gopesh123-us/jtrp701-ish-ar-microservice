package live.learnjava.applicationregistrationservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ApplicationConfig {

	@Bean(name = "template")
	RestTemplate createRestTemplate() {
		return new RestTemplate();
	}

	@Bean(name = "webclient")
	WebClient createWebClient() {
		return WebClient.create();
	}
}
