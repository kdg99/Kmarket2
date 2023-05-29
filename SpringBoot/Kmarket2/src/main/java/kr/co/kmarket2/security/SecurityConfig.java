
package kr.co.kmarket2.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
	
	@Autowired
	AccessDeniedHandlerImpl accessDeniedHandler;
	@Autowired
	AuthenticationEntryPointImpl authenticationEntryPoint;
	
	@Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // 정적 자원에 대해서 Security를 적용하지 않음으로 설정
        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
	
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		//사이트 위조 방지 설정 -> 배포시 제거
		http.cors().and().csrf().disable();
		
		//인가(접근권한) 설정
		http.authorizeHttpRequests()
			.requestMatchers("/product/cart", "/my/**", "/admin/**").authenticated()	// 로그인 해야만 접근가능
			.requestMatchers("/", "/file/**", "/member/**", "/product/**", "/cs/**", "/company/**", "/termsPolicy/**").permitAll();
		//http.authorizeHttpRequests().requestMatchers("/board/write").hasAnyRole("3", "4", "5");

		
		//로그인 alert
		http.exceptionHandling()
			.accessDeniedHandler(accessDeniedHandler)
			.authenticationEntryPoint(authenticationEntryPoint);
		
		//로그인 설정
		http.formLogin()
		.loginPage("/member/login")
		.defaultSuccessUrl("/")
		.failureUrl("/member/login?success=100")
		.usernameParameter("uid")
		.passwordParameter("pass");

		//로그아웃 설정
		http.logout()
		.invalidateHttpSession(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
		.logoutSuccessUrl("/member/login?success=200");
		
		
		return http.build();
	}
	
	
	
	@Bean
    public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
    
	}
	
}
