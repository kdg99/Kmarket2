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
public class OrderVO {
	private int 	ordNo;
	private String 	uid;
	private int 	ordCount;
	private int 	ordPrice;
	private int 	ordDiscount;
	private int 	ordDelivery;
	private int 	savePoint;
	private int 	usedPoint;
	private int 	ordTotPrice;
	private String 	recipName;
	private String 	recipHp;
	private String 	recipZip;
	private String 	recipAddr1;
	private String 	recipAddr2;
	private int 	ordPayment;
	private int 	ordComplete;
	private String 	ordDate;
}
