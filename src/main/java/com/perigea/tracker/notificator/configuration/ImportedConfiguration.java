package com.perigea.tracker.notificator.configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration("commonsConfiguration")
@ComponentScan(basePackages = { "com.perigea.tracker.commons" })
public class ImportedConfiguration {

	@Bean
	public ThreadPoolExecutor threadExecutor() {
		return (ThreadPoolExecutor) Executors.newFixedThreadPool(20);

	}
}
