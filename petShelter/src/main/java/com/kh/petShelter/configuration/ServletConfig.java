package com.kh.petShelter.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.ServletContext;

@Configuration
public class ServletConfig implements WebMvcConfigurer {
	
    //web root가 아닌 외부 경로에 있는 리소스를 url로 불러올 수 있도록 설정
    //현재 localhost:8888/petShelter/summernoteImage/???.jpg
    //로 접속하면 file:///.../resources/uploadFiles/???.jpg 파일을 불러온다.
	
	@Autowired
    private ServletContext servletContext;
	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	String uploadPath = servletContext.getRealPath("/resources/uploadFiles/");
    	
        registry.addResourceHandler("/summernoteImage/**").addResourceLocations("file:///"+ uploadPath);
        
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
