package com.ryanyi.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;

/*
 * The @EnableEntityLinks annotation configures Spring to include support for the EntityLinks class in our system
 * (allowing us to inject the EntityLinks object).
 * Likewise, the @EnableHypermediaSupport annotation instructs Spring to include support for HATEOAS,
 * using the Hypermedia Application Language (HAL) when producing links.
 */

@EnableAutoConfiguration
@EnableEntityLinks
@EnableHypermediaSupport(type = HypermediaType.HAL)
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
