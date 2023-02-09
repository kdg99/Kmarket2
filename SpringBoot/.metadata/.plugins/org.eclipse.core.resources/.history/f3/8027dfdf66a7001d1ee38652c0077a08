package kr.co.farmstory.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//인가(접근권한) 설정
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/board/write").hasAnyRole("3", "4", "5");
		http.authorizeRequests().antMatchers("/board/modify").hasAnyRole("3", "4", "5");
		http.authorizeRequests().antMatchers("/board/addComment").hasAnyRole("3", "4", "5");
		http.authorizeRequests().antMatchers("/board/deleteComment").hasAnyRole("3", "4", "5");

		
		//사이트 위조 방지 설정 -> 배포시 제거
		http.csrf().disable();
		
		
		//로그인 설정
		http.formLogin()
		.loginPage("/user/login")
		.defaultSuccessUrl("/index")
		.failureUrl("/user/login?success=100")
		.usernameParameter("uid")
		.passwordParameter("pass");
		
		//로그아웃 설정
		http.logout()
		.invalidateHttpSession(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
		.logoutSuccessUrl("/user/login?success=200");
		
	}
	
	@Autowired
	private SecurityUserService userService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//사용자 권한 설정
		//auth.inMemoryAuthentication().withUser("admin").password("{noop}1234").roles("ADMIN");
		
		//로그인 인증 처리 서비스, 암호화 방식 설정
		auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	//다른 곳에서 주입받기 위해 사용
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

}
