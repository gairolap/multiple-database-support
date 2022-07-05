package com.org.jpmorgan.connect.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
@PropertySource("classpath:mysqldb.properties")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class MySQLConfig {

	String driverClassName;

	String url;

	String username;

	String password;

	@Bean
	public DataSource datasource() {
		return DataSourceBuilder.create().driverClassName(driverClassName).url(url).username(username)
				.password(password).build();
	}

}