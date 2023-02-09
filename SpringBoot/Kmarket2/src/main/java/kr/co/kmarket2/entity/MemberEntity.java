package kr.co.kmarket2.entity;

// javax에서 jakarta로 변환
/*
 * @Entity : 클래스를 테이블과 매핑한다고 JPA에게 알려준다. 이 어노테이션이 사용되는 클래스를 엔티티 클래스라고 한다. 
 * @Table : 엔티티 클래스에 매핑될 테이블 정보를 알려준다. (name 속성으로 테이블을 설정) 이를 생략하면 클래스이름으로 매핑됨.
 * @Id : 엔티티 클래스의 PK에 매핑한다. 즉, 이 어노테이션이 사용된 필드를 식별자 필드라고 한다. 
 * */

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;




import lombok.Data;

@Data
@Entity
@Table(name = "km_member")
public class MemberEntity {
	@Id
	private String uid;
	private String pass;
	private String name;
	private int gender;
	private String hp;
	private String email;
	private int type;
	private int point;
	private int level;
	private String zip;
	private String addr1;
	private String addr2;
	private String company;
	private String ceo;
	private String bizRegNum;
	private String comRegNum;
	private String tel;
	private String manager;
	private String managerHp;
	private String fax;
	private String regip;
	private String wdate;
	private String rdate;
}