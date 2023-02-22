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
public class PageInfoVO {
	private String pg;
	private String option;
	private String cate1;
	private String cate2;
	//view
	private int no;
}
