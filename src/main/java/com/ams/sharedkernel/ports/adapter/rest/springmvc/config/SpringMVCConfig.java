package com.ams.sharedkernel.ports.adapter.rest.springmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.ams.*.ports.adapter.rest.springmvc.controller")
public class SpringMVCConfig
{
	@Bean
	public static InternalResourceViewResolver viewResolver()
	{
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/view/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Bean
	public static MultipartResolver multipartResolver()
	{
		CommonsMultipartResolver msr = new CommonsMultipartResolver();
		msr.setMaxUploadSize(10000000);
		return msr;

	}
}
