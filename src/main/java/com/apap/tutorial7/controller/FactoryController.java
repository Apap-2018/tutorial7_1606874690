package com.apap.tutorial7.controller;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.apap.tutorial7.rest.Setting;

@RestController
public class FactoryController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@GetMapping(value = "/model")
	private ResponseEntity<Object> getModel(@RequestParam ("factory") String namaProdusen) throws Exception {
		
		//agar tidak 403 forbidden
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", httpHeaders);
        
		LocalDateTime now = LocalDateTime.now();
		int year = now.getYear();
		
		String path = Setting.factoryUrl + "/?cmd=getModels" + "&make=" + namaProdusen + "&year=" + year;
		System.out.println(path);
		
		return restTemplate.exchange(path, HttpMethod.GET, entity, Object.class);
		
	}

	
}