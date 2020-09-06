package com.elasticbackend.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
	FileStorageProperties.class
})
public class ElasticBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElasticBackendApplication.class, args);
	}

}
