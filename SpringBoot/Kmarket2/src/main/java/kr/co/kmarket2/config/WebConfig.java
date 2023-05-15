/*
날짜 : 2023/02/14
이름 : 김동근
내용 : Kmarket2 SpringBoot External path
*/
package kr.co.kmarket2.config;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
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
	
	@Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**") // CORS를 적용할 URL패턴 정의
                .allowedOrigins("http://kdg99.link:8101", "http://www.kdg99.link:8101") // 자원 공유를 허락할 Origin 지정
                .allowedMethods(HttpMethod.GET.name()) // 허용할 HTTP method 지정
                .maxAge(3000); // 설정 시간만큼 pre-flight 리퀘스트 캐싱
    }
	
}
