package kr.co.kmarket2.vo;

import java.io.Serializable;

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
public class OrderItemVO implements Serializable{
	private static final long serialVersionUID = -8072161892260310394L;
	private int 	ordNo;
	private int 	prodNo;
	private int 	count;
	private int 	price;
	private int 	discount;
	private int 	point;
	private int 	delivery;
	private int 	total;
	
	//추가필드
	private String prodName;
	private String descript;
	private String thumb1;	
}
