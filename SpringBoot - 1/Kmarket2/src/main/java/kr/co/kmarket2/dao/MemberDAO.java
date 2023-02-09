package kr.co.kmarket2.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.kmarket2.vo.MemberVO;
import kr.co.kmarket2.vo.TermsVO;

@Mapper
@Repository
public class MemberDAO {
	
	public int insertMember(MemberVO vo);
	public TermsVO selectTerms();
	public void selectMember();
	public void selectMembers();
	public void updateMember();
	public void deleteMember();

}
