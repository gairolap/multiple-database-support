/**
 * Main class for DELETE operation.
 */
package com.org.jpmorgan.connect.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@RefreshScope
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.org.jpmorgan" })
public class DeleteApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeleteApplication.class, args);
	}
}