package kr.co.farmstory.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import kr.co.farmstory.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MyUserDetails implements UserDetails{
	private static final long serialVersionUID = 1L;
	//세션에 저장될 커스텀 유저 정보의 필드들
	@Autowired
	private UserEntity user;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 계정이 갖는 권한 목록 리턴
		//권한 생성 규칙 ROLE_ + @
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getGrade()));
		
		return authorities;
	}
	@Override
	public String getPassword() {
		//계정이 갖는 비밀번호
		return user.getPass();
	}
	@Override
	public String getUsername() {
		//계정이 갖는 ID
		return user.getUid();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		//계정만료 여부 확인 true 만료X, false 만료
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		//계정잠김 여부 확인 true 잠김X, false 잠김
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		//계정 비밀번호 만료 여부 (true: 만료안됨, false:만료)
		return true;
	}
	@Override
	public boolean isEnabled() {
		//계정 활성화 여부 (true: 활성화, false:비활성화)
		return true;
	}
	
}
