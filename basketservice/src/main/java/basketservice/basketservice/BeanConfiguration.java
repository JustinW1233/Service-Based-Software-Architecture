package basketservice.basketservice;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableConfigurationProperties(RedisProperties.class)
@EnableRedisRepositories
public class BeanConfiguration {

	@Bean
	public RedisTemplate<String, Basket> redisTemplate(RedisConnectionFactory connectionFactory) {
		RedisTemplate<String, Basket> template = new RedisTemplate<>();
		template.setConnectionFactory(connectionFactory);
		// Add some specific configuration here. Key serializers, etc.
		//System.out.println("i am here");
		return template;
	}
}
