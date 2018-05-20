package com.rank.ccms.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages=("com.rank.ccms.dao"))
@ComponentScan(basePackages=("com.rank.ccms.dao.Impl"))
@ComponentScan(basePackages=("com.rank.ccms.service"))
@ComponentScan(basePackages=("com.rank.ccms.serviceImpl"))
@ComponentScan(basePackages=("com.rank.ccms.controller"))
public class SpringJpaPostgreSqlAngularJsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaPostgreSqlAngularJsApplication.class, args);
	}
}
