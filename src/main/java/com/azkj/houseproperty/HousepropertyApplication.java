package com.azkj.houseproperty;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.azkj.houseproperty.common.swagger.FastJsonHttpMessageConverterEx;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableTransactionManagement
public class HousepropertyApplication {

	public static void main(String[] args) {
		SpringApplication.run(HousepropertyApplication.class, args);
	}

	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters() {
		FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverterEx();
		List<MediaType> fastMediaTypes = new ArrayList<MediaType>();
		fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);
		HttpMessageConverter<?> converter = fastJsonHttpMessageConverter;
		return new HttpMessageConverters(converter);
	}

}
