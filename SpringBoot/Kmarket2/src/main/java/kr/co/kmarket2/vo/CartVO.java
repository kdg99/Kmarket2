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
public class CartVO {
	private int 	cartNo;
	private String 	uid;
	private int 	prodNo;
	private int 	count;
	private int 	price;
	private int 	discount;
	private int 	point;
	private int 	delivery;
	private int 	total;
	private String 	rdate;
}
