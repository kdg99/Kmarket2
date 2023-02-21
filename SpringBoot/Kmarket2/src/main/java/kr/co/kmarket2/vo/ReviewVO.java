package kr.co.kmarket2.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReviewVO {
	private int revNo;
	private int prodNo;
	private String content;
	private String uid;
	private int rating;
	private String regip;
	private String rdate;
	
	//추가필드
	private int cnt;
	
	public String getRdate() {
        return rdate.substring(2,10);
    }
}
