package kr.co.kmarket2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.kmarket2.entity.MemberEntity;

public interface MemberRepo extends JpaRepository<MemberEntity, String>{
	public int countByUid(String uid);
}
