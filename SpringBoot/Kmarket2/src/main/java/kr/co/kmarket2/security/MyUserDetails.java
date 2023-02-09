//package kr.co.kmarket2.security;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import kr.co.kmarket2.entity.MemberEntity;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.Singular;
//
//@Getter
//@Setter
//@Builder
//public class MyUserDetails implements UserDetails {
//	private static final long serialVersionUID = 1L;
//	
//	private MemberEntity member;
//	
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		// 계정이 갖는 권한 목록 리턴
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority("ROLE_"+member.getLevel()));
//        
//        return authorities;
//	}
//	
//	@Override
//	public String getPassword() {
//		// 계정이 갖는 비밀번호
//		return member.getPass();
//	}
//
//	@Override
//	public String getUsername() {
//		// 계정이 갖는 아이디
//		return member.getUid();
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		// 계정이 만료 여부(true: 만료안됨, false:만료)
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		// 계정이 잠긴 여부(true: 잠김안됨, false:잠긴)
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		// 계정 비밀번호 만료 여부(true: 만료안됨, false:만료)
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		// 계정 활성화 여부(true: 활성화, false:비활성화)
//		return true;
//	}
//}