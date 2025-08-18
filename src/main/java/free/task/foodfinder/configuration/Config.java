package free.task.foodfinder.configuration;

import java.time.Duration;

import com.github.benmanes.caffeine.cache.Caffeine;

import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

	private static final int INITIAL_CACHE_SIZE = 100;

	private static final long MAXIMUM_CACHE_SIZE = 500;

	private static final Duration CACHE_EXPIRATION = Duration.ofMinutes(15);

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean("placesCustomKeyGenerator")
	public KeyGenerator keyGenerator() {
		return (target, method, params) -> params[0] + "_" + params[1];
	}

	@Profile("!test")
	@Bean("inMemoryCacheManager")
	public CacheManager cacheManager() {
		CaffeineCacheManager cacheManager = new CaffeineCacheManager("places");
		cacheManager.setCaffeine(caffeineCacheBuilder());
		return cacheManager;
	}

	Caffeine<Object, Object> caffeineCacheBuilder() {
		return Caffeine.newBuilder()
				.initialCapacity(INITIAL_CACHE_SIZE)
				.maximumSize(MAXIMUM_CACHE_SIZE)
				.expireAfterWrite(CACHE_EXPIRATION);
	}

}
