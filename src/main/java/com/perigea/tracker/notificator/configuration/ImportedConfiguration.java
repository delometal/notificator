package com.perigea.tracker.notificator.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration("commonsConfiguration")
@ComponentScan(basePackages = {"com.perigea.tracker.commons"})
public class ImportedConfiguration {

}
