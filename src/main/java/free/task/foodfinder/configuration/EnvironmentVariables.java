package free.task.foodfinder.configuration;

import lombok.RequiredArgsConstructor;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EnvironmentVariables {

	private final Environment env;

	public String getGeoapifyApiKey() {
		return env.getRequiredProperty("geoapify.api.key");
	}

	public String getGeoapifyBaseUrl() {
		return env.getRequiredProperty("geoapify.base.url");
	}

}
