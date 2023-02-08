//package kr.co.kmarket2.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {			// 메인 index 비밀번호 없이 화면 띄우기 위해
//	// SecurityConfig > MyUserDetails > SecurityUserService > MyUserDetails > SecurityConfig
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		
//		// 인가(접근권한) 설정 = grade 레벨을 올려줘야 읽기, 쓰기, 수정 가능
//		http.authorizeRequests().antMatchers("/").permitAll();
//		http.authorizeRequests().antMatchers("/list").hasAnyRole("2", "3", "4", "5");
//		http.authorizeRequests().antMatchers("/write").hasAnyRole("3", "4", "5");
//		http.authorizeRequests().antMatchers("/view").hasAnyRole("3", "4", "5");
//		http.authorizeRequests().antMatchers("/modify").hasAnyRole("3", "4", "5");
//		
//		
//		// 사이트 위조 방지 설정
//		http.csrf().disable();
//		
//		// 로그인 설정
//		http.formLogin()
//		.loginPage("/user/login")
//		// 로그인 성공 후 경로
//		.defaultSuccessUrl("/")
//		.failureUrl("/user/login?success=100")
//		.usernameParameter("uid")
//		.passwordParameter("pass");
//		
//		// 로그아웃 설정
//		http.logout()
//		.invalidateHttpSession(true)
//		.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
//		.logoutSuccessUrl("/user/login?success=200");
//	}
//	
//	@Autowired
//	private SecurityUserService userService;
//	
//	@Override
//	protected void configur e(AuthenticationManagerBuilder auth) throws Exception {
//		// Security 사용자에 대한 테스트 권한 설정
//		//auth.inMemoryAuthentication().withUser("admin").password("{noop}1234").roles("ADMIN");
//		//auth.inMemoryAuthentication().withUser("manager").password("{noop}1234").roles("MANAGER");
//		//auth.inMemoryAuthentication().withUser("member").password("{noop}1234").roles("MEMBER");
//		
//		// 로그인 인증 처리 서비스, 암호화 방식 설정
//		auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
//	}
//	
//	@Bean
//    public PasswordEncoder encoder() {
//		return new BCryptPasswordEncoder();
//    }
//	
//	
//}