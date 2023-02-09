package kr.co.farmstory.security;

import kr.co.farmstory.entity.UserEntity;
import kr.co.farmstory.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class SecurityUserService implements UserDetailsService {

	@Autowired
	private UserRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		//해당하는 사용자를 찾아보고 없으면
		UserEntity user = repo.findById(username).get();
		if(user == null) {
			//시큐리티에 예외 발생시키기
			throw new UsernameNotFoundException(username);
		}
		//세션에 저장될 사용자 프로필 생성 user -> id, pw, role 밖에 저장 불가능
		//따라서 커스텀 유저디테일이 필요함 -> MyUserDetails 사용
		UserDetails userDts = MyUserDetails.builder()
											.user(user)
											.build();
								
		return userDts;
	}

}
