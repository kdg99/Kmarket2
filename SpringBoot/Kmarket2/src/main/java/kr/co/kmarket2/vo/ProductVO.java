/*
날짜 : 2023/02/08
이름 : 김동근
내용 : Kmarket2 SpringBoot productVO
*/
package kr.co.kmarket2.vo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

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
public class ProductVO {
	private int prodNo;
	private int cate1;
	private int cate2;
	private String prodName;
	private String descript;
	private String company;
	private String seller;
	private int price;
	private int discount;
	private int point;
	private int stock;
	private int sold;
	private int delivery;
	private int hit;
	private int score;
	private int review;
	private String thumb1;
	private String thumb2;
	private String thumb3;
	private String detail;
	private String status;
	private String duty;
	private String receipt;
	private String bizType;
	private String origin;
	private String ip;
	private String rdate;
	//추가필드
	private int level;
    private MultipartFile[] file; // 관리자 상품 등록 MultipartFile 추가 필드
	
	//할인가격 조회
	public int getDisPrice() {
		return (int)(this.price * (1 - (this.discount / 100.0)));
	}
	
	//배송날짜 조회
	public String getDeliDate() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 3);
		SimpleDateFormat result = new SimpleDateFormat("MM/dd (E)");
		return result.format(cal.getTime());
	}
}
