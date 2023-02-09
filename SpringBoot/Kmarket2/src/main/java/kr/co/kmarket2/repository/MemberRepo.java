package kr.co.kmarket2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.kmarket2.entity.MemberEntity;


public interface MemberRepo extends JpaRepository<MemberEntity, String> {

	// 아이디 중복체크
	public int countByUid(String uid);
	
}