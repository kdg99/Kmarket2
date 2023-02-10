package kr.co.kmarket2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.kmarket2.dao.MemberDAO;
import kr.co.kmarket2.repository.MemberRepo;
import kr.co.kmarket2.vo.MemberVO;
import kr.co.kmarket2.vo.TermsVO;

@Service
public class MemberService {

	@Autowired
	private MemberDAO dao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MemberRepo repo;
	
//	public void insertMember(MemberVO vo) {
//		dao.insertMember(vo);
//	}
	public MemberVO selectMember(String uid) {
		return dao.selectMember(uid);
	}
	public List<MemberVO> selectMembers(){
		return dao.selectMembers();
	}
	public void updateMember(MemberVO vo) {
		dao.updateMember(vo);
	}
	public void deleteMember(String uid) {
		dao.deleteMember(uid);
	}
  
	// 약관
	public TermsVO selectTerms() {
		return dao.selectTerms();
	}
	
	
	// 회원가입
	public int insertMember(MemberVO vo) {
		vo.setPass(passwordEncoder.encode(vo.getPass2()));
		
		int result = dao.insertMember(vo);
		
		return result;
	}
	
	public int countMember(String uid) {
		return repo.countByUid(uid);
	}

}
