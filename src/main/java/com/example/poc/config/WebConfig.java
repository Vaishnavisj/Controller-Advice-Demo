package com.example.poc.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
@SuppressWarnings("deprecation")
public class WebConfig extends WebMvcConfigurerAdapter{
	
	/*
	 * @Bean(name = "messageSource") public MessageSource messageSource() {
	 * ReloadableResourceBundleMessageSource bean = new
	 * ReloadableResourceBundleMessageSource();
	 * bean.setBasename("classpath:ValidationMessages");
	 * bean.setDefaultEncoding("UTF-8"); return bean; }
	 * 
	 * @Bean(name = "validator") public LocalValidatorFactoryBean validator() {
	 * LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
	 * bean.setValidationMessageSource(messageSource()); return bean; }
	 * 
	 * @Override public Validator getValidator() { return validator(); }
	 */

}
