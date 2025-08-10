package free.task.foodfinder.util;

import org.springframework.cache.CacheManager;
import org.springframework.cache.support.NoOpCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class TestConfig {

	@Profile("test")
	@Bean("inMemoryCacheManager")
	public CacheManager inMemoryCacheManager() {
		return new NoOpCacheManager();
	}

}
