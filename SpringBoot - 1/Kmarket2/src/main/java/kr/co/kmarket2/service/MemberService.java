package kr.co.kmarket2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.kmarket2.vo.TermsVO;
import kr.co.kmarket2.vo.MemberVO;
import kr.co.kmarket2.dao.MemberDAO;
import kr.co.kmarket2.repository.MemberRepo;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO dao;
	
	@Autowired
	private MemberRepo repo;
	
	// 약관
	public TermsVO selectTerms() {
		return dao.selectTerms();
	}
	
	// 회원가입
	public int insertMember(MemberVO vo) {
		vo.setPass(PasswordEncoder.encode(vo.getPass2()));
		
		int result = dao.insertMember(vo);
		
		return result;
	}
	
	public int countMember(String uid) {
		return repo.countByUid(uid);
	}

}
