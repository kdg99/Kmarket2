package kr.co.kmarket2.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import kr.co.kmarket2.entity.MemberEntity;
import lombok.Data;

@Data
@Entity
@Table(name = "km_member")
public class MemberEntity {
	@Id
	private String uid;
	private String pass1;
	private String pass2;
	private String pass;	
	private String name;
	private String nick;
	private String email;
	private String hp;
	private int grade;
	private String zip;
	private String addr1;
	private String addr2;
	private String regip;
	private String rdate;	
}