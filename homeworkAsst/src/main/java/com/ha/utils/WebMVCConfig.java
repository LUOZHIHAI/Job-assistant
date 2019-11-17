package com.ha.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMVCConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/FILE/**").addResourceLocations("file:I:/file/");
	}
	/*
	@Override //注册拦截器
	public void addInterceptors(InterceptorRegistry registry) {
		
		List<String> path = new ArrayList<>();
		path.add("/doLogin");
		path.add("/doRegister");
		path.add("/css/**");
		path.add("/images/**");
		path.add("/js/**");
		registry.addInterceptor(new CheckUserInterceptor()).addPathPatterns("/**").excludePathPatterns(path);
	}*/
	
}
