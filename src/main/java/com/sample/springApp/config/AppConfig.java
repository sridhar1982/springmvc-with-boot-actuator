package com.sample.springApp.config;

import org.springframework.boot.actuate.autoconfigure.EndpointAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.HealthIndicatorAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.MetricRepositoryAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.PublicMetricsAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
//@EnableAutoConfiguration
@Import({ EndpointAutoConfiguration.class, HealthIndicatorAutoConfiguration.class, PublicMetricsAutoConfiguration.class, MetricRepositoryAutoConfiguration.class})
@ComponentScan("com.sample.springApp")
@EnableWebMvc
public class AppConfig {

}
