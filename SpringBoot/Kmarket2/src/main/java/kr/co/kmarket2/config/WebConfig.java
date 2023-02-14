package kr.co.kmarket2.config;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Value("${custom.file.path}")
	private String externalPath;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/file/**")
				.addResourceLocations(externalPath)
				
				// 접근 파일 캐싱 시간 
				.setCacheControl(CacheControl.maxAge(1, TimeUnit.MINUTES));
		
	}
	
}
