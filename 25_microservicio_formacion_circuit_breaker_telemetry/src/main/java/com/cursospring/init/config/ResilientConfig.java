package com.cursospring.init.config;

import java.time.Duration;

import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType;

@Configuration
public class ResilientConfig {
	CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
			  .failureRateThreshold(50)			  
			  .waitDurationInOpenState(Duration.ofMillis(30000)) //una vez que el circuito se abre, tiempo que espera antes de volver a intentar
			  .slidingWindowSize(6)	
			  .slidingWindowType(SlidingWindowType.COUNT_BASED)
			  .build();
	
	@Bean
	public Customizer<Resilience4JCircuitBreakerFactory> globalCustomConfiguration() {
      
      // configuración global
      return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
        .circuitBreakerConfig(circuitBreakerConfig)
        .build());
      //configuración específica para un determinado circuit breaker
      /*return factory -> factory.configure(builder -> builder
        		  .circuitBreakerConfig(circuitBreakerConfig)
      	      .build(), "circuit1");*/
    } 
}
