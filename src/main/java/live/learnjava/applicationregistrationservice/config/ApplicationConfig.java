package live.learnjava.applicationregistrationservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {
	
	@Bean(name="template")
	public RestTemplate createRestTemplate() {
		return new RestTemplate();
	}
}
