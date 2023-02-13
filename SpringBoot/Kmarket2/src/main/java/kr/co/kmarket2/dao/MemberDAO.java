package kr.co.kmarket2.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.kmarket2.vo.MemberVO;
import kr.co.kmarket2.vo.TermsVO;


@Mapper
@Repository
public interface MemberDAO {
	public int insertMember(MemberVO vo);
	public MemberVO selectMember(String uid);
	public List<MemberVO> selectMembers();
	public void updateMember(MemberVO vo);
	public void deleteMember(String uid);
	public TermsVO selectTerms();
	public TermsVO selectTermsSeller();
}
