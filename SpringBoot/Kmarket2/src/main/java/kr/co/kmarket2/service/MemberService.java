package kr.co.kmarket2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kmarket2.dao.MemberDAO;
import kr.co.kmarket2.vo.MemberVO;

@Service
public class MemberService {

	@Autowired
	private MemberDAO dao;
	
	public void insertMember(MemberVO vo) {
		dao.insertMember(vo);
	}
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
	
}
